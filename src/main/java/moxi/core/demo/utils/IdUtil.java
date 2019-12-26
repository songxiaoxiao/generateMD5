package moxi.core.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class IdUtil {

	private static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

	private static final List<String> keys ;
	static {
		 keys = new LinkedList<>();
		 for(int x = 'a';x < 'z';x++) {
			 keys.add(""+(char)x);
		 }
		 for(int x = 'A';x < 'Z';x++) {
			 keys.add(""+(char)x);
		 }
		 for(int x = '0';x < '9';x++) {
			 keys.add(""+(char)x);
		 }
	}
	
	//3位毫秒  4位随机数
	public static final Integer length =  4;

	private static String getIdLength() {
		List<String> number = new LinkedList<>();
		for(int x = '0';x < '9';x++) {
			number.add(""+(char)x);
		}

		StringBuffer sb = new StringBuffer();
		for(int x = 0 ;x < length ;x++) {
			String key = number.get(getRandom(0, number.size()));
			sb.append(key);
		}
		return sb + "";
	}

	public static String getId() {
		String dataTime = format.format(new Date());
		StringBuffer sb = new StringBuffer();
		for(int x = 0 ;x < length ;x++) {
			String key = keys.get(getRandom(0, keys.size()));
			sb.append(key);
		}
		return dataTime  + sb;
	}



	public static String getChanceId() {
		return "SJ" + DateUtils.dateUUID() + getIdLength();
	}

	public static String getCustomerId() {
		return "KH" + DateUtils.dateUUID() + getIdLength();
	}
	public static String getContractId() {
		return "HT" + DateUtils.dateUUID() + getIdLength();
	}
	public static String getTaskId() {
		return "RW" + DateUtils.dateUUID() + getIdLength();
	}
	public static String getExpenditureShowCode(Long time) {
		return "ZC" + DateUtils.formatDateToStringYMS(new Date(time)).substring(2) + getIdLength();
	}
	public static String getTicketShowCode(Long time) {
		return "KP" + DateUtils.formatDateToStringYMS(new Date(time)).substring(2) + getIdLength();
	}



	public static Integer getRandom(Integer min,Integer max) {
		double number = Math.random();
		return (int) (min + number * (max - min));
	}

	
}
