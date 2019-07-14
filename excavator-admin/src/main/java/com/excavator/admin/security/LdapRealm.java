package com.excavator.admin.security;

import com.excavator.admin.entity.LdapAuthToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * LDAP认证服务
 *
 * @author Glory
 * @create 2019-07-14 14:53
 **/
public class LdapRealm extends SimpleAccountRealm {

    /**
     * 认证方法
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        LdapAuthToken ldapAuthToken = (LdapAuthToken) token;
        return super.doGetAuthenticationInfo(token);
    }

    /**
     * 授权方法
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return super.doGetAuthorizationInfo(principals);
    }
}
