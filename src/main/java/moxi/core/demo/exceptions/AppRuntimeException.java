package moxi.core.demo.exceptions;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * 异常处理
 * @author winter
 *
 * */
public class AppRuntimeException extends RuntimeException implements Serializable {
    private Integer code;
    private String message;

    private String[] exArgs;

    private Map<String, String> extra = new HashMap<>();
    public AppRuntimeException(Integer code){
        this.code = code;
        this.message = code.toString();
    }
    public AppRuntimeException(Integer code, String message){
        this.code = code;
        this.message = message;
    }
    public AppRuntimeException(Integer code, String message, String... exArgs){
        this.code = code;
        this.message = message;
        this.exArgs = exArgs;
    }

    public AppRuntimeException(Integer code, String message, Throwable cause){
        super(cause);
        this.code = code;
        this.message = message;
    }

    public AppRuntimeException(Integer code, String message, Throwable cause, String... exArgs){
        super(cause);
        this.code = code;
        this.message = message;
        this.exArgs = exArgs;
    }

    public Integer getCode(){
        return this.code;
    }
    public String getMessage(){
        return this.message;
    }

    public String[] getExArgs(){
        return this.exArgs;
    }

    public Map<String, String> getExtra() {
        return extra;
    }

    public AppRuntimeException setExtra(Map<String, String> extra) {
        this.extra = extra;
        return this;
    }
}
