package com.excavator.admin.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import java.util.Date;
import java.util.List;

/**
 * 系统管理-用户信息
 *
 * @author Glory
 * @create 2019-07-13 23:49
 **/
@Data
@NoArgsConstructor
public class SysUser {

    private Integer id;

    private String account;

    private String password;

    private String name;

    // 用户角色
    private List<Role> roles;

    private Date addTime;

    private Date update;
}

