package com.heyrace.beans;

public class RespBean {
    private int status;
    private String msg;
    private Object data;
    public static RespBean success(Object data) {
        RespBean resp = new RespBean(200,"成功", data);
        return resp;
    }
    public static RespBean success(String msg, Object data) {
        RespBean resp = new RespBean(200,msg, data);
        return resp;
    }
    public static RespBean error(String msg) {
        RespBean resp = new RespBean(500,msg, null);
        return resp;
    }
    public static RespBean error(int status, String msg) {
        RespBean resp = new RespBean(status, msg, null);
        return resp;
    }
    RespBean(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
