package com.material.chain.user.convert;

import com.material.chain.base.exception.ApiException;
import com.material.chain.common.utils.RSAEncrypt;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserConvert {

    public static String decryptionByPassword(String password) {
        try {
            return RSAEncrypt.decrypt(password);
        }catch (Exception e) {
            log.error("密码解密出错", e);
            throw new ApiException("登录异常");
        }
    }
}
