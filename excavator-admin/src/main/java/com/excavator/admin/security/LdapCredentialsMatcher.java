package com.excavator.admin.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

/**
 * 密码认证
 *
 * @author Glory
 * @create 2019-07-14 17:08
 **/
public class LdapCredentialsMatcher implements CredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        // 本地系统不认证密码
        return true;
    }
}
