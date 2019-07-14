package com.excavator.admin.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 系统管理-用户信息
 *
 * @author Glory
 * @create 2019-07-13 23:49
 **/
@Data
@NoArgsConstructor
public class SysUser {

    private Long id;

    private String account;

    private String password;

    private String name;

    private Date addTime;

    private Date update;
}

