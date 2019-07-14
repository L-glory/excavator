CREATE TABLE `t_sys_user` (
      `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
      `account` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
      `password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
      `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
      `add_time` datetime NOT NULL COMMENT '用户添加时间',
      `update_time` datetime NOT NULL COMMENT '更新时间',
      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统管理-用户表';

CREATE TABLE `t_sys_role` (
      `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
      `key` varchar(32) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色Key',
      `name` varchar(32) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
      `remark` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '备注',
      `add_time` datetime NOT NULL COMMENT '添加时间',
      `update_time` datetime NOT NULL COMMENT '更新时间',
      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统管理-系统角色';

CREATE TABLE `t_sys_permission` (
      `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
      `key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '权限Key',
      `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '权限名称',
      `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '备注',
      `add_time` datetime NOT NULL COMMENT '添加时间',
      `update_time` datetime NOT NULL COMMENT '更新时间',
      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统管理-系统权限';

CREATE TABLE `t_sys_user_role` (
             `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
             `user_id` int(8) NOT NULL COMMENT '用户ID',
             `role_id` int(8) NOT NULL COMMENT '角色ID',
             `add_time` datetime NOT NULL COMMENT '创建时间',
             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统管理-用户&角色关联表';

CREATE TABLE `t_sys_role_permission` (
             `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
             `role_id` int(8) NOT NULL COMMENT '角色ID',
             `permission_id` int(8) NOT NULL COMMENT '权限ID',
             `add_time` datetime NOT NULL COMMENT '创建时间',
             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统管理-角色&权限关联表';



