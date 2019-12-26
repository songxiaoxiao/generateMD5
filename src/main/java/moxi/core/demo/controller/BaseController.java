package moxi.core.demo.controller;

import moxi.core.demo.component.I18NMessageSource;
import moxi.core.demo.entity.ResponseData;
import moxi.core.demo.exceptions.AppRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Map;


/**
 * 公共控制器
 * @author winter
 *
 * */
@RestController
public class BaseController {
    private Logger logger = LoggerFactory.getLogger(BaseController.class);

    private static Integer REQUEST_SUCCESS = 101000000;
    private static String REQUEST_SUCCESS_MESSAGE = "SUCCESS";
    private static Integer SYSTEM_ERROR = 101000001;
    private static String SYSTEM_ERROR_MESSAGE = "ERROR";

    @Resource(name = "i18NMessageSource")
    private I18NMessageSource i18NMessageSource;

    @ResponseBody
    @ExceptionHandler(Exception.class)
    protected ResponseData handleException(Exception ex, HttpServletRequest request, HttpSession session){
        if(ex != null){
            logger.error("moxiyun exception:", ex);
//            logger.info(SessionUtil.get("userInfo"));

            if(ex instanceof BindException){

                BindException bex = (BindException) ex;
                if(bex.hasFieldErrors()) {
                    FieldError fieldError = bex.getFieldErrors().get(0);
                    String message = fieldError.getDefaultMessage();
                    return fail(101000002, message,null, fieldError.getField());
                }
            }else if(ex instanceof AppRuntimeException){
                Integer exeErrorCode = ((AppRuntimeException)ex).getCode();
                String exeErrorMessage = ((AppRuntimeException)ex).getMessage();
                String[] exArgs = ((AppRuntimeException)ex).getExArgs();
                Map<String, String> extra = ((AppRuntimeException) ex).getExtra();
                return fail(exeErrorCode, exeErrorMessage, extra, exArgs);
            }else{
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);

                return fail(SYSTEM_ERROR, "SYSTEM_ERROR");
            }
        }

        return success();
    }

    /**
     * 请求成功
     * @return
     */
    protected ResponseData success(){
        int code = REQUEST_SUCCESS;
        String message = i18NMessageSource.getMessage(REQUEST_SUCCESS_MESSAGE);
        return ResponseData.success(code, message);
    }

    /**
     * 请求成功
     * @param data
     * @return
     */
    protected ResponseData success(Object data){
        int code = REQUEST_SUCCESS;
        String message = i18NMessageSource.getMessage(REQUEST_SUCCESS_MESSAGE);
        return ResponseData.success(code, message, data);
    }

    /**
     * 请求成功
     * @param data
     * @param total
     * @return
     */
    protected ResponseData success(Object data, int total){
        int code = REQUEST_SUCCESS;
        String message = i18NMessageSource.getMessage(REQUEST_SUCCESS_MESSAGE);
        return ResponseData.success(code, message, data, total);
    }

    /**
     * 请求失败
     * @param code
     * @param params
     * @return
     */
    protected ResponseData fail(Integer code, String message, Map<String, String> extra, String...params){
        message = i18NMessageSource.getMessage(message, params);
        logger.error("error.code:[{}], error.msg:[{}]", code, message);
        if(extra == null || extra.size() == 0)
            return ResponseData.error(code, message);
        return ResponseData.error(code, message, extra);
    }
    /**
     * 请求失败
     * @param code
     * @return
     */
    protected ResponseData fail(Integer code){
        return fail(code,"NULL");
    }
    /**
     * 请求失败
     * @param code
     * @return
     */
    protected ResponseData fail(Integer code, String message){
        message = i18NMessageSource.getMessage(message);
        logger.error("error.code:[{}], error.msg:[{}]", code, message);
        return ResponseData.error(code, message);
    }
    /**
     * 请求失败
     * @return
     */
    protected ResponseData fail(){
        String message = i18NMessageSource.getMessage(SYSTEM_ERROR_MESSAGE);
        return fail(SYSTEM_ERROR, message);
    }
}
