package com.excavator.admin.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 系统角色
 *
 * @author Glory
 * @create 2019-07-14 20:27
 **/
@Data
public class SysRole {

    private Integer id;

    private String key;

    private String name;

    private String remark;

    // 角色权限
    private List<SysPermission> permissions;

    private Date addTime;

    private Date updateTime;
}
