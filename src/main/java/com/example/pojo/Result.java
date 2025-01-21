package com.example.pojo;

import lombok.Data;

@Data
public class Result {
    private Integer code; // 编码: 1成功 0失败
    private String msg; // 错误信息
    private Object data; // 数据

    public static Result success() {
        Result result = new Result();
        result.code = 1;
        result.msg = "success";
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.data = data;
        result.code = 1;
        result.msg = "success";
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 0;
        return result;
    }
}
