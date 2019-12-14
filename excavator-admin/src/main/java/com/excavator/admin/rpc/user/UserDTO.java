package com.excavator.admin.rpc.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户信息DTO
 *
 * @author Glory
 * @create 2019-11-30 15:25
 **/
@Data
@NoArgsConstructor
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1884129432564931546L;

    private String name;

    private String introduction;

    private String avatar = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";

    private String[] roles = new String[]{"admin"};

    public static UserDTO admin() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("刘荣耀");
        userDTO.setIntroduction("我是一个sb哦");
        return userDTO;
    }
}
