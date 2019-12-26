package moxi.core.demo.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class MD5Util {

    protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    protected static MessageDigest messageDigest = null;

    static {
        try {
            // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            // 需要用户提示、需要log输出
            log.error(
                    "系统错误：MD5Util 加密工具初始化失败","系统错误：MD5Util", e);
        }
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {

        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }
    /**
     * 密码字符串的md5加密
     * 老系统平移
     * @param str 输入明文
     *
     * @return String 输出密文
     */
    public static String oldMd5(String str) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            byte[] bytes = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            byte[] updateBytes = messageDigest.digest();
            int len = updateBytes.length;
            char myChar[] = new char[len * 2];
            int k = 0;
            for (int i = 0; i < len; i++) {
                byte byte0 = updateBytes[i];
                myChar[k++] = hexDigits[byte0 >>> 4 & 0x0f];
                myChar[k++] = hexDigits[byte0 & 0x0f];
            }
            return new String(myChar);
        } catch (Exception e) {
            return null;
        }

    }
    /**
     * 字符串的md5加密
     *
     * @param input
     *            输入明文
     * @return String 输出密文
     */
    public static String stringMD5(String input) {
        // 输入的字符串转换成字节数组
        byte[] inputByteArray = input.getBytes();
        // inputByteArray是输入字符串转换得到的字节数组
        messageDigest.update(inputByteArray);
        // 转换并返回结果，也是字节数组，包含16个元素
        byte[] resultByteArray = messageDigest.digest();
        // 字符数组转换成字符串返回
        return bufferToHex(resultByteArray);
    }

    /**
     * 文件的md5加密
     *
     * @param inputFile
     *            输入文件
     * @return String 输出密文
     */
    public static String fileMD5(String inputFile) {
        // 缓冲区大小（这个可以抽出一个参数）
        int bufferSize = 256 * 1024;
        FileInputStream fileInputStream = null;
        // 对于大文件，可以使用DigestInputStream
        DigestInputStream digestInputStream = null;
        try {
            // 使用DigestInputStream
            fileInputStream = new FileInputStream(inputFile);
            digestInputStream = new DigestInputStream(fileInputStream,
                    messageDigest);
            // read的过程中进行MD5处理，直到读完文件
            byte[] buffer = new byte[bufferSize];
            while (digestInputStream.read(buffer) > 0)
                ;
            // 获取最终的MessageDigest
            messageDigest = digestInputStream.getMessageDigest();
            // 拿到结果，也是字节数组，包含16个元素
            byte[] resultByteArray = messageDigest.digest();
            // 同样，把字节数组转换成字符串
            return bufferToHex(resultByteArray);
        } catch (Exception e) {
            // 需要用户提示（异常提示-异常码）、需要log输出
            log.error(
                    "系统错误：MD5Util 文件MD5加密异常", "系统错误：MD5Util",e);
        } finally {
            try {
                digestInputStream.close();
            } catch (Exception e) {
                // 无需用户提示
                log.warn("系统警告：MD5Util IO资源未能正常释放", e);
            }
            try {
                fileInputStream.close();
            } catch (Exception e) {
                // 无需用户提示
                log.warn("系统警告：MD5Util IO资源未能正常释放", e);
            }
        }
        return "";
    }


	/*public static void main(String[] args) {
		// 测试字符串MD5加密
		// 123456: e10adc3949ba59abbe56e057f20f883e
		// eastcom: 6997c46956185a7c4d452646fc9c69e2
		String str = "pl123456";
		System.out.println(str+"-------"+oldMd5(str).length());
		System.out.println(oldMd5(str));
		System.out.println(stringMD5(str));
		//long startTime = System.currentTimeMillis();
		// 测试文件MD5加密
		//String FilePath = "D:/ilink_ide.zip"; // 4227e9fc4bd71ff34887d47867967b29
		//System.out.println(fileMD5(FilePath));

		//long endTime = System.currentTimeMillis();
		//System.out.println((endTime - startTime) / 1000);

	}*/

	/*
	      参考文章： http://www.zhihu.com/question/23702510
	  http://blog.csdn.net/lf_software_studio/article/details/8025497
	  http://my.oschina.net/laigous/blog/106646
	  http://blog.csdn.net/wangqiuyun/article/details/22941433
	 */

}
