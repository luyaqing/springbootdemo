package com.example.springbootdemo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BG362793
 * 数据包装类
 */
public class JsonResults<T> {

    //是否成功
    private boolean success = true;

    //返回信息 错误信息等
    private Map<String, Object> message = new HashMap<String, Object>();

    //返回数据
    private List<T> data = new ArrayList<>();

    //行数 返回的数据的条数
    private Long totalCount;

    public <T> JsonResults(){

    }

    public JsonResults(boolean success, Map<String, Object> message, List<T> data, Long totalCount) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.totalCount = totalCount;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Map<String, Object> getMessage() {
        return message;
    }

    public void setMessage(Map<String, Object> message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public static <T> JsonResults success(Map<String, Object> messageMap, List<T> data, Long totalCount) {
        return new JsonResults(true, messageMap, data, totalCount);
    }

    public static <T> JsonResults fail(Map<String, Object> messageMap,List<T>  data) {
        return new JsonResults(false, messageMap, data,null);
    }

    public static <T> JsonResults fail(Map<String, Object> messageMap) {
        return fail(messageMap, null);
    }

}
