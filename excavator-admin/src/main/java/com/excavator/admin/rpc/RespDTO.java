package com.excavator.admin.rpc;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * RPC接口响应基类
 *
 * @author Glory
 * @create 2019-11-30 14:54
 **/
@Data
@NoArgsConstructor
public class RespDTO<T> implements Serializable {

    private static final long serialVersionUID = 6795637096943578811L;

    // 响应状态码
    private int code;

    // 响应描述
    private String msg;

    // 响应数据
    private T data;

    public static <T> RespDTO<T> ok(T data) {
        RespDTO<T> respDTO = new RespDTO<>();
        respDTO.setCode(20000);
        respDTO.setMsg("ok");
        respDTO.setData(data);
        return respDTO;
    }
}
