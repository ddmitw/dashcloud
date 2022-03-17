package com.ddmit.common.core.domain;

import com.ddmit.common.core.constant.HttpStatusConstants;
import com.ddmit.common.core.constant.SystemConstants;

import java.io.Serializable;

/**
 * 系统相应实体类模型
 *
 * @author Winbert
 * @Date 2022/3/16 15:02
 */
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 成功
     */
    public static final int SUCCESS = SystemConstants.SUCCESS;

    /**
     * 失败
     */
    public static final int FAIL = SystemConstants.FAIL;

    /**
     * 未授权
     */
    public static final int UNAUTHORIZED = HttpStatusConstants.UNAUTHORIZED;

    private int code;

    private String msg;

    private T data;

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

    private static <T> R<T> restResult(T data, int code, String msg) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public static <T> R<T> ok(T data) {
        return restResult(data, SUCCESS, null);
    }

    public static <T> R<T> unAuth(String msg) {
        return restResult(null, UNAUTHORIZED, msg);
    }
}
