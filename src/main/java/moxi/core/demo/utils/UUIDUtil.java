package moxi.core.demo.utils;


import java.util.UUID;

/**
 * 生成随机字符串的工具类
 * 
 * @author 灯芯科技 李远念
 * 
 */
public class UUIDUtil {
	/**
	 * 获取UUID
	 * 
	 * @return 无"-"分隔、长度32的UUID
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}
