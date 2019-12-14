package com.excavator.admin.rpc.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 登录成功，响应token
 *
 * @author Glory
 * @create 2019-11-30 15:02
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO implements Serializable {

    private static final long serialVersionUID = -7492927448037055706L;

    private String token;
}
