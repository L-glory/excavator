package com.excavator.admin.entity;

import lombok.NonNull;
import org.apache.shiro.authc.AuthenticationToken;

import java.io.Serializable;

/**
 * ldap认证信息
 *
 * @author Glory
 * @create 2019-07-14 15:45
 **/
public class LdapAuthToken implements AuthenticationToken, Serializable {

    private static final long serialVersionUID = -8194797648982763701L;

    // 账号方式登录
    private static final int ACCOUNT_LOGIN_TYPE = 1;
    // token方式登录
    private static final int TOKEN_LOGIN_TYPE   = 2;

    // 账号
    private String account;

    // 密码
    private String password;

    // 认证token
    private String token;

    // 登录方式
    private int loginType;

    private LdapAuthToken(String account, String password) {
        this.account   = account;
        this.password  = password;
        this.token     = "";
        this.loginType = ACCOUNT_LOGIN_TYPE;
    }

    private LdapAuthToken(String token) {
        this.account   = "";
        this.password  = "";
        this.token     = token;
        this.loginType = TOKEN_LOGIN_TYPE;
    }

    public static LdapAuthToken of(@NonNull String account, @NonNull String password) {
        return new LdapAuthToken(account, password);
    }
    public static LdapAuthToken of(@NonNull String token) {
        return new LdapAuthToken(token);
    }

    @Override
    public Object getPrincipal() {
        return account;
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    public String getToken() {
        return token;
    }

    /**
     * 是否为token方式认证
     * @return
     */
    public boolean isTokenAuth() {
        return loginType == TOKEN_LOGIN_TYPE;
    }
}
