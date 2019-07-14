package com.excavator.admin.controller;

import com.excavator.admin.entity.LdapAuthToken;
import com.excavator.admin.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统会话接口
 *
 * @author Glory
 * @create 2019-07-14 15:15
 **/
@RestController
@RequestMapping(value = "/v1/sessions", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SessionController {

    @PostMapping
    public ResponseEntity<String> login(String account, String password) {
        // shro认证
        Subject subject = SecurityUtils.getSubject();
        subject.login(LdapAuthToken.of(account, password));
        return ResponseEntity.ok((String)subject.getSession(true).getId());
    }

    @GetMapping("/token")
    public ResponseEntity<String> token(String token) {
        // shro认证
        Subject subject = SecurityUtils.getSubject();
        subject.login(LdapAuthToken.of(token));

        return ResponseEntity.ok((String)subject.getSession(true).getId());
    }

    @GetMapping("/user")
    @RequiresPermissions("session:user:get")
    public ResponseEntity<SysUser> getUser() {
        return null;
    }
}
