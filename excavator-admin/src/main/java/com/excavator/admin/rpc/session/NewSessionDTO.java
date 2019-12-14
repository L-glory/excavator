package com.excavator.admin.rpc.session;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 登录参数
 *
 * @author Glory
 * @create 2019-11-30 15:01
 **/
@Data
@NoArgsConstructor
public class NewSessionDTO implements Serializable {

    private static final long serialVersionUID = -5781131452828683739L;

    private String userName;

    private String password;
}
