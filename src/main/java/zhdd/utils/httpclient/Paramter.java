package zhdd.utils.httpclient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Reporter;

public class Paramter {
	/**
	 * 将map改成List
	 * 
	 * @param params
	 * @return param
	 */
	public static List<NameValuePair> parameterList(Map<String, Object> params) {
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				param.add(new BasicNameValuePair(key, params.get(key).toString()));
			}
		} else {
			Reporter.log("未读取到参数数据！");
			return null;
		}
		return param;
	}
}
