package com.material.chain.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.material.chain.user.domain.dto.LoginDTO;
import com.material.chain.user.domain.po.UserPo;
import com.material.chain.user.domain.vo.LoginResponse;
import com.material.chain.user.domain.vo.UserInfoResponse;

public interface UserService extends IService<UserPo> {

    UserInfoResponse login(LoginDTO dto);

    /**
     * 退出登录
     * @return Boolean
     */
    Boolean logout();
}
