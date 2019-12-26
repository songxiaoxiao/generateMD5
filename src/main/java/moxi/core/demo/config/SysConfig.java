package moxi.core.demo.config;

import moxi.core.demo.exceptions.AppRuntimeException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 系统全局配置对象-从配置文件读取
 *
 * @author 灯芯科技 李远念
 */
public class SysConfig {
    private static final String CONFIG_FILE = "/sys.properties";

    //配置文件读取项
    public static boolean SYS_DEBUG = false;
    public static String ClUSTER_ID = "";
    public static Integer TIME_RUN_FLAG = 0;

    public static String GEETEST_ID = "";
    public static String GEETEST_KEY = "";

    public static String WXROBOTD_GATEWAY_ENTRY_URL = "";

    public static boolean SYS_ENABLE_WECHATCP = false;

    // 字典缓存的有效期限： dictionary.redis.valid.seconds
    public static Integer DICTIONARY_REDIS_TIME = 0;

    static {
        String errMsg = "系统错误：SysConfig [%s]文件不存在, 系统错误：SysConfig。[ErrorMsg: %s]";
        //写读配置文件代码
        Properties p = new Properties();
        InputStream inStream = new SysConfig().getClass().getResourceAsStream(CONFIG_FILE);
        if (inStream == null) {
            throw new AppRuntimeException(0,String.format(errMsg, CONFIG_FILE, ""));
        }
        try {
            p.load(inStream);
            String sysDebugStr = p.getProperty("sys.debug");
            SYS_DEBUG = Boolean.parseBoolean(sysDebugStr);
            ClUSTER_ID = p.getProperty("ClusterID");
            TIME_RUN_FLAG = Integer.valueOf(p.getProperty("TimeRunFlag"));

            // 极验证配置信息的取得
            GEETEST_ID = p.getProperty("geetest.id");
            GEETEST_KEY = p.getProperty("geetest.key");

            // 字典缓存的有效期限
            DICTIONARY_REDIS_TIME = Integer.valueOf(p.getProperty("dictionary.redis.valid.seconds"));

            WXROBOTD_GATEWAY_ENTRY_URL = p.getProperty("wxrobotd.gatewayEntryUrl");

            SYS_ENABLE_WECHATCP = Boolean.parseBoolean(p.getProperty("sys.enableWechatCp"));

        } catch (IOException exp) {
            SYS_DEBUG = false;
            ClUSTER_ID = "";
            TIME_RUN_FLAG = 0;
            throw new AppRuntimeException(0, String.format(errMsg, CONFIG_FILE, exp.toString()), "");
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (Exception exp) {
                    throw new AppRuntimeException(0, String.format(errMsg, CONFIG_FILE, exp.toString()), "");
                }
            }
        }
    }
}
