package com.ender.common.lang;

import lombok.Data;
import java.io.Serializable;

@Data
//序列化？？？
public class Result implements Serializable {
    //200 正常    非200 表示异常
    private int code;
    private String msg;
    private Object data;

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result succ( Object data){
        return new Result(200,"成功",data);
    }
    public static Result fail( String msg){
        return new Result(400,msg,null);
    }


}
