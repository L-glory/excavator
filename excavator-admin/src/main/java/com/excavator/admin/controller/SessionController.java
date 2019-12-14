package com.excavator.admin.controller;

import com.excavator.admin.entity.LdapAuthToken;
import com.excavator.admin.entity.SysUser;
import com.excavator.admin.rpc.RespDTO;
import com.excavator.admin.rpc.session.NewSessionDTO;
import com.excavator.admin.rpc.session.TokenDTO;
import com.excavator.admin.rpc.user.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 系统会话接口
 *
 * @author Glory
 * @create 2019-07-14 15:15
 **/
@RestController
@RequestMapping(value = "/v1/sessions", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class SessionController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public RespDTO<TokenDTO> login(@RequestBody NewSessionDTO newSessionDTO) {
        log.info("[new-session] create session params={}", newSessionDTO);
        // shro认证
        //Subject subject = SecurityUtils.getSubject();
        //subject.login(LdapAuthToken.of(newSessionDTO.getUserName(), newSessionDTO.getPassword()));
        return RespDTO.ok(new TokenDTO(RandomStringUtils.randomAlphanumeric(12)));
    }

    @GetMapping(value = "/{token}")
    public RespDTO<UserDTO> getUser(@PathVariable String token) {
        log.info("[new-session] get session's user, token={}", token);
        return RespDTO.ok(UserDTO.admin());
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public RespDTO<String> getUser(@RequestBody TokenDTO tokenDTO) {
        log.info("[new-session] del session, token={}", tokenDTO);
        return RespDTO.ok("del session success.");
    }

    @GetMapping("/token")
    public ResponseEntity<String> token(String token) {
        log.info("[session] token");
        // shro认证
        Subject subject = SecurityUtils.getSubject();
        subject.login(LdapAuthToken.of(token));
        return ResponseEntity.ok((String)subject.getSession(true).getId());
    }

    @GetMapping("/user")
    @RequiresPermissions({"admin", "session:user:get"})
    public ResponseEntity<SysUser> getUser() {
        return null;
    }
}
