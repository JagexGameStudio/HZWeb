package com.haozi.hzweb.bean.auth.entity.sys;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ReturnMessage extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public ReturnMessage() {
        put("code", 0);
        put("msg", "操作成功");
    }

    public static ReturnMessage error() {
        return error(1, "操作失败");
    }

    public static ReturnMessage error(String msg) {
        return error(500, msg);
    }

    public static ReturnMessage error(int code, String msg) {
        ReturnMessage r = new ReturnMessage();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static ReturnMessage ok(String msg) {
        ReturnMessage r = new ReturnMessage();
        r.put("msg", msg);
        return r;
    }

    public static ReturnMessage ok(Map<String, Object> map) {
        ReturnMessage r = new ReturnMessage();
        r.putAll(map);
        return r;
    }

    public static ReturnMessage ok() {
        return new ReturnMessage();
    }

    public ReturnMessage put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
