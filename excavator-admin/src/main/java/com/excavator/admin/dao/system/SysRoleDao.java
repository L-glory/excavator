package com.excavator.admin.dao.system;

import com.excavator.admin.entity.SysRole;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * 系统管理-系统角色Dao
 *
 * @author Glory
 * @create 2019-07-14 20:25
 **/
@Repository
public class SysRoleDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public boolean addRoles(final List<SysRole> roles) {
        if (CollectionUtils.isEmpty(roles)) {
            return Boolean.FALSE;
        }
        return roles.size() == jdbcTemplate.batchUpdate("INSERT INTO t_sys_role(`key`, name, remark, add_time, update_time) VALUES(?,?,?,NOW(),NOW())",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, roles.get(i).getKey());
                        ps.setString(2, roles.get(i).getName());
                        ps.setString(3, roles.get(i).getRemark());
                    }

                    @Override
                    public int getBatchSize() {
                        return roles.size();
                    }
                }).length;
    }

}
