package moxi.core.demo.service.test;

import com.baomidou.mybatisplus.service.IService;
import moxi.core.demo.model.password.Password;

import java.io.IOException;
import java.text.ParseException;

/**
 * @author winter
 */
public interface IPassword extends IService<Password> {
    void test();
    void generateStr();
    void generateFile() ;
}
