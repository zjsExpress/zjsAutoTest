package zhdd.utils.files;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonAnalysis {
	/**
	 * 获取JSON指定key的值
	 * @param json
	 * @param key
	 * @return value
	 */
	public String getJsonValue(String json, String key){
		JsonParser parser = new JsonParser();
		JsonObject object = (JsonObject) parser.parse(json);
		return object.get(key).getAsString();
	}
}
