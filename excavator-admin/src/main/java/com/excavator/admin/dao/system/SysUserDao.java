package com.excavator.admin.dao.system;

import com.excavator.admin.entity.SysUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.ResultSet;

/**
 * 系统管理-用户dao
 *
 * @author Glory
 * @create 2019-07-13 23:36
 **/
@Repository
public class SysUserDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public boolean add(SysUser user) {
        return 1 == jdbcTemplate.update("INSERT INFO t_sys_user(account, password, name, add_time, update_time) VALUES(?, ?, ?, NOW(), NOW())",
                user.getAccount(), user.getPassword(), user.getName());
    }

    @Transactional
    public boolean del(Long userId) {
        return 1 == jdbcTemplate.update("DELETE FROM t_sys_user WHERE id = ?", userId);
    }

    @Transactional
    public boolean update(SysUser user) {
        return 1 == jdbcTemplate.update("UPDATE t_sys_user SET password = ?, name = ?, update_time = NOW() WHERE id = ?", user.getPassword(), user.getName(), user.getId());
    }

    public SysUser get(String account) {
        return jdbcTemplate.queryForObject("SELECT * FROM t_sys_user WHERE account = ?", (ResultSet resultSet, int index) -> {
            SysUser user = new SysUser();
            user.setId(resultSet.getInt("id"));
            user.setAccount(resultSet.getString("account"));
            user.setPassword(resultSet.getString("password"));
            user.setName(resultSet.getString("name"));
            user.setAddTime(resultSet.getTimestamp("add_time"));
            user.setUpdate(resultSet.getTimestamp("update_time"));
            return user;
        } , account);
    }
}
