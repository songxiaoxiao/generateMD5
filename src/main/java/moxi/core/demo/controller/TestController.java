package moxi.core.demo.controller;


import lombok.extern.slf4j.Slf4j;
import moxi.core.demo.service.test.IPassword;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author winter
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {
    @Resource
    private IPassword password;
    @GetMapping("/test")
    @ResponseBody
    public void test(){
        password.test();
    }

      @GetMapping("/string")
    @ResponseBody
    public void string(){
        password.generateStr();
    }

    @GetMapping("/file")
    @ResponseBody
    public void file(){
        password.generateFile();
    }


}
