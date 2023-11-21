package com.material.chain.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.material.chain.base.exception.ApiException;
import com.material.chain.common.constant.RedisKey;
import com.material.chain.common.utils.JwtUtil;
import com.material.chain.user.components.RedisTemplateService;
import com.material.chain.user.convert.UserConvert;
import com.material.chain.user.domain.dto.LoginDTO;
import com.material.chain.user.domain.po.UserPo;
import com.material.chain.user.domain.vo.LoginResponse;
import com.material.chain.user.domain.vo.UserInfoResponse;
import com.material.chain.user.enums.StatusEnum;
import com.material.chain.user.mapper.UserPoMapper;
import com.material.chain.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserPoMapper, UserPo> implements UserService {

    @Autowired
    private UserPoMapper userPoMapper;

    @Autowired
    private RedisTemplateService redisTemplateService;

    /**
     * 登录
     *
     * @param dto 入参
     * @return LoginResponse
     */
    @Override
    public LoginResponse login(LoginDTO dto) {
        log.info("[登录]：入参：{}", JSON.toJSONString(dto));
        LoginResponse loginResponse = new LoginResponse();
        String loginPassword = UserConvert.decryptionByPassword(dto.getPassword());
        LambdaQueryWrapper<UserPo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserPo::getAccount, dto.getAccount())
                .eq(UserPo::getStatus, StatusEnum.NORMAL.getCode());
        UserPo userPo = this.getOne(wrapper);
        if (Objects.isNull(userPo)) {
            throw new ApiException("用户不存在");
        }
        String passwordByDB = UserConvert.decryptionByPassword(userPo.getPassword());
        if (!Objects.equals(loginPassword, passwordByDB)) {
            throw new ApiException("账号或密码错误");
        }
        UserInfoResponse response = new UserInfoResponse();
        response.setUserId(userPo.getId());
        response.setUserName(userPo.getUserName());
        //token有效期2小时
        String token = JwtUtil.buildJWT(JSON.toJSONString(response), String.valueOf(userPo.getId()), 3600 * 24);
        response.setToken(token);
        loginResponse.setToken(token);
        redisTemplateService.set(String.format(RedisKey.ADMIN_USER_KEY, userPo.getId()), JSONObject.toJSONString(response), 3600 * 24L, TimeUnit.SECONDS);

        return loginResponse;
    }
}
