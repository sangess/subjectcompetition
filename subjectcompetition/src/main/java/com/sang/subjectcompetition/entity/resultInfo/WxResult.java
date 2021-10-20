package com.sang.subjectcompetition.entity.resultInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WxResult {

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    private String ok;  // 不使用

    public static WxResult build(Integer status, String msg, Object data) {
        return new WxResult(status, msg, data);
    }

    public static WxResult ok(Object data) {
        return new WxResult(data);
    }

    public static WxResult ok() {
        return new WxResult(null);
    }

    public static WxResult errorMsg(String msg) {
        return new WxResult(500, msg, null);
    }

    public static WxResult errorMap(Object data) {
        return new WxResult(501, "error", data);
    }

    public static WxResult errorTokenMsg(String msg) {
        return new WxResult(502, msg, null);
    }

    public static WxResult errorException(String msg) {
        return new WxResult(555, msg, null);
    }

    public WxResult() {

    }

    public WxResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public WxResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

}