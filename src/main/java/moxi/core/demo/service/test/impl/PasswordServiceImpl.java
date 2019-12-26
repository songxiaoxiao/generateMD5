package moxi.core.demo.service.test.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import moxi.core.demo.dao.password.PasswordMapper;
import moxi.core.demo.exceptions.AppRuntimeException;
import moxi.core.demo.model.password.Password;
import moxi.core.demo.service.test.IPassword;
import moxi.core.demo.utils.MD5Util;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PasswordServiceImpl extends ServiceImpl<PasswordMapper, Password> implements IPassword {
    @Resource(name = "test")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private static String[] key = {
            "A", "B", "C", "D", "E", "F","J", "H", "I", "G", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
            "a", "b", "c", "d", "e", "f","j", "h", "i", "g", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "!", "@", "#", "$", "%", "^","_", "?","/","-",
            "0","1", "2", "3", "4", "5", "6", "7", "8", "9"
    };
    @Override
    public void test() {
        log.info("===begin===");

//        generateNumber();
        for (int i = 6; i <= 9; i++) {
            final int temp = i;
            threadPoolTaskExecutor.execute(()->{
                generateNumberRecursive("", temp, 1);
            });
        }
        log.info("===end===");
    }
    /**
     * 1数字组装
     * */
    private void generateNumber1(){
        log.info("===begin-generateNumber===");
//        List<String> number = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
//            number.add(String.valueOf(i));
                insert(String.valueOf(i));
        }
        log.info("===end-generateNumber===");
//        return number;
    }
    /**
     * 两位数字组装
     * */
    private void generateNumber2(){
        log.info("===begin-generateNumber2===");
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                insert(i + "" + j);
            }
        }
        log.info("===end-generateNumber2===");
    }
    /**
     * 递归位数字组装
     * */
    private void generateNumberRecursive(String laws, int times, int beginTime){
        log.info("===begin-generateNumber2===");
        for (int i = 0; i <= 9; i++) {
            String tempLaws = laws.concat(String.valueOf(i));
            if (times > beginTime){
                generateNumberRecursive(laws.concat(String.valueOf(i)), times, beginTime+1);
            }else {
                insert(tempLaws);
            }
        }
        log.info("===end-generateNumber2===");
    }
    @Override
    public void generateStr() {
        log.info("===begin===");

//        generateNumber();
        for (int i = 1; i <= 5; i++) {
            generateEnRecursive("", i, 1);
        }
        log.info("===end===");
    }
    /**
     * digui 字母组装
     * */
    private void generateEnRecursive(String laws, int times, int beginTime){
        for (int i = 0; i <= 61; i++) {
            String tempLaws = laws.concat(key[i]);
            if (times > beginTime){
                generateEnRecursive(laws.concat(key[i]), times, beginTime+1);
            }else {
                if (tempLaws.equals("000")){
                    break;
                }
                insert(tempLaws);
            }
        }
    }
    /**
     * 插入
     * */
    private void insert(String laws){
        try{
//            log.info(laws);
            Password password = new Password();
            password.setLaws(laws);
            password.setCipher(MD5Util.stringMD5(laws));
            this.insert(password);
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
    }


    public List<String> getEmailAddress() {
        try{
            return readtFile(new File("./emailAddress.txt"));
        }catch (IOException | ParseException ex){
            log.error("读取文件失败", ex);
            throw new AppRuntimeException(400, "读取文件失败");

        }

    }
    /** 读取数据，存入集合中 */
    public static List<String> readtFile(File file) throws IOException, ParseException {
        InputStreamReader read = null;// 考虑到编码格式
        try {
            read = new InputStreamReader(new FileInputStream(file), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(read);
        String lineTxt = null;
        List<String> content = new ArrayList<>();

        while ((lineTxt = bufferedReader.readLine()) != null) {
            System.out.println(lineTxt);
            lineTxt = lineTxt.trim().toLowerCase();
            if (lineTxt.substring(lineTxt.length()-6).equals("qq.com")){
                content.add(lineTxt);
            }

        }
        read.close();
        return content;
    }
}
