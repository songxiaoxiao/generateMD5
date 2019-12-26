package moxi.core.demo.component;

import moxi.core.demo.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

@Component
public class I18NMessageSource {

    @Resource
    private MessageSource messageSource;

    private Logger logger = LoggerFactory.getLogger(BaseController.class);

    public String getMessage(String code, String... params) {
        String message = "";
        try {
            Locale locale = LocaleContextHolder.getLocale();
            message = messageSource.getMessage(code, params, locale);
        } catch (Exception e) {
            logger.error("parse message error!", e);
        }
        return message;
    }
}
