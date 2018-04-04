package zhdd.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import zhdd.utils.httpclient.Paramter;

public class HttpDemo {
	static CloseableHttpClient httpClient = HttpClientBuilder.create().build();

	/**
	 * 设置http post请求链接
	 * 
	 * @param url
	 * @param params
	 * @return HttpPost
	 */
	public static HttpPost postUrl(String url, Map<String, Object> params) {
		HttpPost post = new HttpPost(url);
		List<NameValuePair> param = null;
		// 设置Post请求参数
		if (!params.isEmpty())
			param = Paramter.parameterList(params);

		// 设置Post传递参数
		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(param);
			post.setEntity(entity);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return post;
	}

	/**
	 * 执行Http Post 请求
	 * 
	 * @param post
	 * @return CloseableHttpResponse
	 */
	public static CloseableHttpResponse excetePost(HttpPost post) {
		// 创建HttpClient对象
		RequestConfig config = RequestConfig.custom().setConnectTimeout(3000).setSocketTimeout(3000)
				.setConnectionRequestTimeout(5000).build();
		httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
		CloseableHttpResponse response = null;
		// 执行请求
		try {
			response = httpClient.execute(post);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	// 拼接get url
	public static String getUrl(String url, Map<String, Object> params) {
		List<NameValuePair> param = null;
		if (!params.isEmpty())
			param = Paramter.parameterList(params);
		try {
			url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(param), "utf-8");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return url;
	}

	/**
	 * 执行get 请求
	 * @param url
	 * @return
	 */
	public static CloseableHttpResponse excuteGet(String url) {
		CloseableHttpResponse response = null;
		HttpGet httpget = new HttpGet(url);

		try {
			response = httpClient.execute(httpget);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
}
