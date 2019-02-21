package com.mofei.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mofeiske
 * @Description: 通用的返回类
 * @Date: Create in 2019/2/14 18:24
 */
public class Message {

    // 200 成功 404 失败
    private int code;

    //提示信息
    private String msg;

    // 服务器要返回给浏览器的数据
    private Map<String, Object> data = new HashMap<String, Object>();

    public static Message success() {
        Message message = new Message();
        message.setCode(200);
        message.setMsg("处理成功");
        return message;
    }

    public static Message fail() {
        Message message = new Message();
        message.setCode(404);
        message.setMsg("处理失败");
        return message;
    }

    /*链式操作 对象追加*/
    public Message add(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public Message() {
        super();
        // TODO Auto-generated constructor stub
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

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }


}