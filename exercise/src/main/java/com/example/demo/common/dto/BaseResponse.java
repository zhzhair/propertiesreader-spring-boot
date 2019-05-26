package com.example.demo.common.dto;

public class BaseResponse<T> {
    private int code = -4;//0:成功, -1:token未授权, -2:参数错误, -3:其他已知错误, -4:其他未知错误。 其他错误代码依据实际情况去定义
    private String msg = "未知错误";//描述
    private T data;

    public void ok(){
        this.code = 0;
        this.msg = "success";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
