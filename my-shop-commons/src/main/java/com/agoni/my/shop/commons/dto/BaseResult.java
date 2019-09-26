package com.agoni.my.shop.commons.dto;

import com.agoni.my.shop.commons.constant.ConstantUtils;

import java.io.Serializable;

/**
 * 自定义返回结果
 */
public class BaseResult implements Serializable {
    private int status;
    private String message;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static BaseResult success(){
        return BaseResult.createResult(ConstantUtils.STATUS_SUCCESS,"成功",null);
    }

    public static BaseResult success(String message){
        return BaseResult.createResult(ConstantUtils.STATUS_SUCCESS,message,null);
    }

    public static BaseResult success(String message,Object data) {
        return BaseResult.createResult(ConstantUtils.STATUS_SUCCESS,message,data);
    }

    public static BaseResult fail(){
        return BaseResult.createResult(ConstantUtils.STATUS_FAIL,"失败",null);
    }

    public static BaseResult fail(String message){
        return BaseResult.createResult(ConstantUtils.STATUS_FAIL,message,null);
    }

    public static BaseResult fail(int status,String message){
        return BaseResult.createResult(status,message,null);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private static BaseResult createResult(int status,String message,Object data){
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(status);
        baseResult.setMessage(message);
        baseResult.setData(data);
        return baseResult;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
