package moxi.core.demo.utils;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.List;

/**
 * @author winter
 *
 */
public class JsonUtils {

	public static String toJson(Object object) {
		return toJson(object, null);
	}

	public static String toJson(Object object, JsonAdapter adapter) {
		String jsonStr = JSONObject.toJSONString(object);
		if (adapter != null)
			jsonStr = adapter.toJson(jsonStr);
		return jsonStr;
	}

	public static <T> T toObject(String json, Class<T> clazz) {
		return toObject(json, clazz, null);
	}

	public static <T> T toGenericObject(String json, TypeReference<T> typeRef) {
		return toGenericObject(json, typeRef, null);
	}

	public static <T> T toGenericObject(String json, TypeReference<T> typeRef, JsonAdapter adapter) {
		if (adapter != null)
			json = adapter.toObject(json);
		return JSONObject.parseObject(json, typeRef);

	}

	public static <T> T toObject(String json, Class<T> clazz, JsonAdapter adapter) {
		if (adapter != null)
			json = adapter.toObject(json);
		return JSONObject.parseObject(json, clazz);
	}

	public static <T> List<T> toArray(String json, Class<T> clazz) {

		return JSONObject.parseArray(json, clazz);
	}

}
