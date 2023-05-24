package com.itheima.model.ResponseData;

public class CommentResponseData<T> {
    private T payload;        //服务器响应数据
    private boolean success; //请求是否成功
    private String msg;       // 错误信息
    private int code = -1;   // 状态码
    private long timestamp; //服务器响应时间

    public CommentResponseData() {
        this.timestamp = System.currentTimeMillis() / 1000;
    }

    public CommentResponseData(boolean success) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
    }

    public CommentResponseData(boolean success, T payload) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.payload = payload;
    }

    public CommentResponseData(boolean success, T payload, int code) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.payload = payload;
        this.code = code;
    }

    public CommentResponseData(boolean success, String msg) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.msg = msg;
    }

    public CommentResponseData(boolean success, String msg, int code) {
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

    public static CommentResponseData ok() {
        return new CommentResponseData(true);
    }

    public static <T> CommentResponseData ok(T payload) {
        return new CommentResponseData(true, payload);
    }

    public static <T> CommentResponseData ok(int code) {
        return new CommentResponseData(true, null, code);
    }

    public static <T> CommentResponseData ok(T payload, int code) {
        return new CommentResponseData(true, payload, code);
    }

    public static CommentResponseData fail() {
        return new CommentResponseData(false);
    }

    public static CommentResponseData fail(String msg) {
        return new CommentResponseData(false, msg);
    }

    public static CommentResponseData fail(int code) {
        return new CommentResponseData(false, null, code);
    }

    public static CommentResponseData fail(int code, String msg) {
        return new CommentResponseData(false, msg, code);
    }


}
