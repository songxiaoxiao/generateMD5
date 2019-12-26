package moxi.core.demo.service.test;

import com.baomidou.mybatisplus.service.IService;
import moxi.core.demo.model.password.Password;

/**
 * @author winter
 */
public interface IPassword extends IService<Password> {
    void test();
    void generateStr();
}
