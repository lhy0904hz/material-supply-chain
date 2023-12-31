package com.material.chain.base.utils;

import com.material.chain.base.exception.ApiException;
import com.material.chain.common.enums.GlobalStatusEnum;
import com.material.chain.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class AppContextUtil {

    /**
     * 重上下文获取当前登录的userId
     * @return Long
     */
    public static Long getCurrentUserId() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(requestAttributes)) {
            throw new ApiException(GlobalStatusEnum.TOKEN_EXPIRE.getCode(), GlobalStatusEnum.TOKEN_EXPIRE.getMessage());
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            throw new ApiException(GlobalStatusEnum.TOKEN_EXPIRE.getCode(), GlobalStatusEnum.TOKEN_EXPIRE.getMessage());
        }
        Jws<Claims> claimsJws = JwtUtil.parseJWT(token);
        String userId = claimsJws.getBody().get("jti", String.class);
        if (StringUtils.isBlank(userId)) {
            throw new ApiException(GlobalStatusEnum.TOKEN_EXPIRE.getCode(), GlobalStatusEnum.TOKEN_EXPIRE.getMessage());
        }
        return Long.parseLong(userId);
    }
}
