package org.wl.security.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenService {

    public boolean validAuth(String accessTokenValue,String accessRole){
        System.out.println("authTokenService--------------");
        if(StringUtils.isBlank(accessTokenValue)){
            return false;
        }
        String role = "ROLE_ADMIN";//TODO 使用token查询其权限编码
        if(!accessRole.equals(role)){
            return false;
        }
        return true;
    }
}
