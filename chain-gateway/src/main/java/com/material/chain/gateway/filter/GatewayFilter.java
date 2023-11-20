package com.material.chain.gateway.filter;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.material.chain.common.constant.RedisKey;
import com.material.chain.common.enums.GlobalStatusEnum;
import com.material.chain.common.utils.JwtUtil;
import com.material.chain.gateway.components.RedisTemplateService;
import com.material.chain.gateway.domain.vo.UserInfoVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class GatewayFilter implements GlobalFilter, Ordered {

    @Autowired
    private RedisTemplateService redisTemplateService;
    public static final String AUTH_TOKEN = "token";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders httpHeaders = Optional.ofNullable(exchange).map(ServerWebExchange::getRequest).map(ServerHttpRequest::getHeaders).orElse(null);
        ServerHttpResponse response = Optional.ofNullable(exchange).map(ServerWebExchange::getResponse).orElse(null);
        if (Objects.isNull(httpHeaders)) {
            return Mono.error(new Exception());
        }
        String path = Optional.of(exchange).map(ServerWebExchange::getRequest).map(ServerHttpRequest::getURI).map(URI::getPath).orElse(null);
        if (StringUtils.isBlank(path)) {
            return Mono.error(new Exception());
        }
        //指定url不拦截
        if (path.contains("/login")) {
            return chain.filter(exchange);
        }
        List<String> tokenList = Optional.ofNullable(httpHeaders.get(AUTH_TOKEN)).orElse(new ArrayList<>());
        if (CollectionUtil.isEmpty(tokenList)) {
            return tokenIsExpire(response);
        }
        log.info("token：{}", tokenList);
        //判断token是否失效
        String token = tokenList.get(0);
        if (StringUtils.isBlank(token) || !JwtUtil.checkJWT(token, path)) {
            return tokenIsExpire(response);
        }
        Jws<Claims> claimsJws = JwtUtil.parseJWT(token);
        String userId = claimsJws.getBody().get("jti", String.class);
        if (StringUtils.isBlank(userId)) {
            return tokenIsExpire(response);
        }
        //判断是否需要刷新token
        return Objects.isNull(refreshToken(response, userId, token)) ? chain.filter(exchange) : refreshToken(response, userId, token);
    }

    @Override
    public int getOrder() {
        return 0;
    }


    /**
     * token过期
     */
    private Mono<Void> tokenIsExpire(ServerHttpResponse response) {
        JSONObject json = new JSONObject();
        json.put("success", false);
        json.put("code", GlobalStatusEnum.TOKEN_EXPIRE.getCode());
        json.put("message", GlobalStatusEnum.TOKEN_EXPIRE.getMessage());
        DataBuffer wrap = response.bufferFactory().wrap(json.toString().getBytes());
        return response.writeWith(Mono.just(wrap));
    }

    /**
     * 刷新 token以及缓存
     */
    private Mono<Void> refreshToken(ServerHttpResponse response, String userId, String oldToken) {
        //判断过期时间
        String tokenKey = String.format(RedisKey.ADMIN_USER_KEY, userId);
        long expireTime = redisTemplateService.getTime(tokenKey);
        if (expireTime < 2000L) {
            Object user = redisTemplateService.get(tokenKey);
            UserInfoVo userInfoVo = JSONObject.parseObject(String.valueOf(user), UserInfoVo.class);
            if (Objects.isNull(userInfoVo)) {
                return tokenIsExpire(response);
            }
            //判断页面传过来的token是否跟redis一致
            if (!StringUtils.equals(oldToken, userInfoVo.getToken())) {
                return tokenIsExpire(response);
            }
            //token有效期2小时
            userInfoVo.setUserId(Long.parseLong(userId));
            String newToken = JwtUtil.buildJWT(JSON.toJSONString(userInfoVo), userId, 3600 * 24);
            userInfoVo.setToken(newToken);
            redisTemplateService.set(tokenKey, JSONObject.toJSONString(userInfoVo), 3600 * 24L, TimeUnit.SECONDS);
            return refreshBack(response, newToken);
        }
        return null;
    }

    /**
     * token刷新回调
     */
    private Mono<Void> refreshBack(ServerHttpResponse response, String newToken) {
        JSONObject json = new JSONObject();
        json.put("status", GlobalStatusEnum.TOKEN_REFRESH.getCode());
        json.put("message", GlobalStatusEnum.TOKEN_REFRESH.getMessage());
        json.put("data", newToken);
        DataBuffer wrap = response.bufferFactory().wrap(json.toString().getBytes());
        return response.writeWith(Mono.just(wrap));
    }
}
