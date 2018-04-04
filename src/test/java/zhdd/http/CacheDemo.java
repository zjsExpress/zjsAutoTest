package zhdd.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import zhdd.common.httpclient.HttpRequests;

public class CacheDemo {
	static CookieStore cookieStore = null;
	static HttpClientContext context = null;
	static String domain = "admin.tsp.yogomo.org";
	String loginUrl = "http://admin.tsp.yogomo.org/login";
	String listUrl = "http://admin.tsp.yogomo.org/carInfo/queryPage";

	// 登录
	@Test
	public void testLogin() throws Exception {
		System.out.println("-------testLogin--------");
		// 创建client
		// CloseableHttpClient client = HttpClientBuilder.create().build();
		// 创建client
		//CloseableHttpClient client = HttpExecute.getHttpClient();
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		// 登录参数
		parameterMap.put("ajax", "true");
		parameterMap.put("uname", "admin");
		parameterMap.put("pword", "123456");
		// 创建Post对象
		// HttpPost httpPost = new HttpPost(loginUrl);
		HttpPost httpPost = HttpDemo.postUrl(loginUrl, parameterMap);
		CloseableHttpResponse response = HttpDemo.excetePost(httpPost);
		String requests = HttpRequests.readRequests(response);
		System.out.println(requests);

		// 设置参数
		parameterMap.clear();
		parameterMap.put("ajax", "true");
		parameterMap.put("pageNum", "1");
		parameterMap.put("pageSize", "10");
		parameterMap.put("sortName", "id");
		parameterMap.put("sortOrder", "desc");

		System.err.println("----------GET------");
		listUrl = HttpDemo.getUrl(listUrl, parameterMap);
		CloseableHttpResponse http = HttpDemo.excuteGet(listUrl);
		requests = HttpRequests.readRequests(http);
		System.out.println(requests);

		/*
		 * System.err.println("--------------POST----------");
		 * UrlEncodedFormEntity postEntity2 = new
		 * UrlEncodedFormEntity(getParam(parameterMap), "utf-8"); HttpPost
		 * httpPost2 = new HttpPost(listUrl); httpPost2.setEntity(postEntity2);
		 * System.out.println("request line:" + httpPost2.getRequestLine() +
		 * parameterMap); HttpResponse httpResponse2 =
		 * client.execute(httpPost2); printResponse(httpResponse2);
		 */

		// setCookieStore(httpResponse);
		// setContext();

	}

	// 输出响应消息体
	public static void printResponse(HttpResponse httpResponse) throws ParseException, IOException {
		// 获取响应消息实体
		HttpEntity entity = httpResponse.getEntity();
		// 响应状态
		System.out.println("status" + httpResponse.getStatusLine());
		System.out.println("headers:");
		HeaderIterator iterator = httpResponse.headerIterator();
		while (iterator.hasNext()) {
			System.out.println("\t" + iterator.next());
		}

		// 判断响应实体是否为空
		if (entity != null) {
			String responseString = EntityUtils.toString(entity, "utf-8");
			System.out.println("response length: " + responseString.length());
			System.out.println("response content: " + responseString.replace("\r\n", ""));
		}
	}

	// 获取JSESSIONID设置cookie
	public static void setCookieStore(HttpResponse httpResponse) {
		System.out.println("-----------set Cookie Store----------");
		cookieStore = new BasicCookieStore();
		// Jsessionid
		String setCookie = httpResponse.getFirstHeader("Set-Cookie").getValue();
		String JSESSIONID = setCookie.substring("JSESSIONID=".length(), setCookie.indexOf(";"));
		// 新建一个Cookie
		BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", JSESSIONID);
		cookie.setVersion(0);
		cookie.setDomain(domain);
		cookie.setPath("/");
		cookieStore.addCookie(cookie);
	}

	// 设置添加cookie连接
	public static void setContext() {
		System.out.println("----------set Context--------");
		context = HttpClientContext.create();
		Registry<CookieSpecProvider> registry = RegistryBuilder.<CookieSpecProvider>create()
				.register(CookieSpecs.BEST_MATCH, new BestMatchSpecFactory())
				.register(CookieSpecs.BROWSER_COMPATIBILITY, new BrowserCompatSpecFactory()).build();
		context.setCookieSpecRegistry(registry);
		context.setCookieStore(cookieStore);
	}

	// 把Map转换成List
	public static List<NameValuePair> getParam(Map<String, Object> parameterMap) {
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		Iterator<Entry<String, Object>> iterator = parameterMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> paramEntry = (Entry<String, Object>) iterator.next();
			param.add(new BasicNameValuePair((String) paramEntry.getKey(), (String) paramEntry.getValue()));
		}
		return param;
	}
}
