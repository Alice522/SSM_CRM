package com.fj.crm.commons.domain;

public class ReturnObject {

    private String code;//处理成功或者失败的标志：1---成功，0---失败
    private String message;//提示信息
    private Object returnData;//返回其他数据

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getReturnData() {
        return returnData;
    }

    public void setReturnData(Object returnData) {
        this.returnData = returnData;
    }
}
