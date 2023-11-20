package com.material.chain.user.convert;

import com.material.chain.common.utils.RSAEncrypt;
import com.material.chain.user.Exception.GlobalException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserConvert {

    public static String decryptionByPassword(String password) {
        try {
            return RSAEncrypt.decrypt(password);
        }catch (Exception e) {
            log.error("密码解密出错", e);
            throw new GlobalException("登录异常");
        }
    }
}
