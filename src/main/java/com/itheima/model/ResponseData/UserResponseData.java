package com.itheima.model.ResponseData;

public class UserResponseData<T> {
    private T payload;        //服务器响应数据
    private boolean success; //请求是否成功
    private String msg;       // 错误信息
    private int code = -1;   // 状态码
    private long timestamp; //服务器响应时间

    public UserResponseData() {
        this.timestamp = System.currentTimeMillis() / 1000;
    }

    public UserResponseData(boolean success) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
    }

    public UserResponseData(boolean success, T payload) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.payload = payload;
    }

    public UserResponseData(boolean success, T payload, int code) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.payload = payload;
        this.code = code;
    }

    public UserResponseData(boolean success, String msg) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.msg = msg;
    }

    public UserResponseData(boolean success, String msg, int code) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.msg = msg;
        this.code = code;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static UserResponseData ok() {
        return new UserResponseData(true);
    }

    public static <T> UserResponseData ok(T payload) {
        return new UserResponseData(true, payload);
    }

    public static <T> UserResponseData ok(int code) {
        return new UserResponseData(true, null, code);
    }

    public static <T> UserResponseData ok(T payload, int code) {
        return new UserResponseData(true, payload, code);
    }

    public static UserResponseData fail() {
        return new UserResponseData(false);
    }

    public static UserResponseData fail(String msg) {
        return new UserResponseData(false, msg);
    }

    public static UserResponseData fail(int code) {
        return new UserResponseData(false, null, code);
    }

    public static UserResponseData fail(int code, String msg) {
        return new UserResponseData(false, msg, code);
    }

}
