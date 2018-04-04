package zhdd.common.httpclient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Reporter;

public class HttpRequests {
	/**
	 * @param response
	 * @return 请求响应内容
	 */
	public static String readRequests(CloseableHttpResponse response) {
		String httpResults = null;
		try {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				httpResults = EntityUtils.toString(entity, "utf-8");
			} else {
				Reporter.log("请求无应答");
			}
			// 释放资源
			EntityUtils.consume(entity);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return httpResults;
	}
}
