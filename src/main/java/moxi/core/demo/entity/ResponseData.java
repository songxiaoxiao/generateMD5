package moxi.core.demo.entity;

import lombok.Data;

/**
 * 返回给前台的通用包装
 *
 * @author stylefeng
 * @Date 2018/1/4 22:37
 */
@Data
public class ResponseData<T> {

    public static final String DEFAULT_SUCCESS_MESSAGE = "操作成功";

    public static final String DEFAULT_ERROR_MESSAGE = "网络异常";

    public static final Integer DEFAULT_SUCCESS_CODE = 200;

    public static final Integer DEFAULT_ERROR_CODE = 500;

    public static final Integer REQUEST_ERROR_CODE = 400;

    /**
     * 请求是否成功
     */
    private Boolean success;

    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应对象
     */
    private T data;
    /**
     * 列表查询时,总条数
     */
    private int total;

    public ResponseData() {
    }

    public ResponseData(Boolean success, Integer code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }
    public ResponseData(Boolean success, Integer code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public ResponseData(Boolean success, Integer code, String message, T data, int total) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
        this.total = total;
    }

    public static ResponseData success() {
        return new ResponseData(true, DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE);
    }
    public static <T> ResponseData<T> success(T object) {
        return new ResponseData(true, DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE, object);
    }
    public static <T> ResponseData<T> success(T object, int total){
        return new ResponseData(true, DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE,object, total);
    }
    public static ResponseData success(Integer code, String message) {
        return new ResponseData(true, code, message);
    }
    public static ResponseData success(Integer code, String message, Object object) {
        return new ResponseData(true, code, message, object);
    }
    public static ResponseData success(Integer code, String message, Object object, int total) {
        return new ResponseData(true, code, message, object, total);
    }

    public static ResponseData error(String message) {
        return new ResponseData(false, DEFAULT_ERROR_CODE, message);
    }

    public static ResponseData error(Integer code, String message) {
        return new ResponseData(false, code, message);
    }

    public static ResponseData error(Integer code, String message, Object object) {
        return new ResponseData(false, code, message, object);
    }
}

