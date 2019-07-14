package com.excavator.admin.entity;

import lombok.Data;

import java.util.Date;

/**
 * 系统权限
 *
 * @author Glory
 * @create 2019-07-14 20:28
 **/
@Data
public class SysPermission {

    private Integer id;

    private String key;

    private String name;

    private String remark;

    private Date addTime;

    private Date updateTime;
}
