package com.material.chain.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.material.chain.user.domain.dto.LoginDTO;
import com.material.chain.user.domain.po.UserPo;
import com.material.chain.user.domain.vo.UserInfoResponseVo;

public interface UserService extends IService<UserPo> {

    UserInfoResponseVo login(LoginDTO dto);

    /**
     * 退出登录
     * @return Boolean
     */
    Boolean logout();
}
