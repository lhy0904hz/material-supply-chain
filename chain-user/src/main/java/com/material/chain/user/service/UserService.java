package com.material.chain.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.material.chain.user.domain.dto.LoginDTO;
import com.material.chain.user.domain.po.UserPo;
import com.material.chain.user.domain.vo.LoginResponse;

public interface UserService extends IService<UserPo> {

    LoginResponse login(LoginDTO dto);
}
