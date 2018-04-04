package zhdd.http;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import zhdd.common.httpclient.HttpExecute;
import zhdd.common.httpclient.HttpRequests;
import zhdd.utils.files.JsonAnalysis;

public class Test_01_Login implements DefaultParamter {
	JsonAnalysis json = new JsonAnalysis();
	@DataProvider(name = "defaultParam")
	public Object[] paramDefault() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("api", api);
		param.put("version", version);
		param.put("appCode", appCode);
		return new Object[]{param};
	}

	@Test(dataProvider = "defaultParam")
	public void testLogin(Map<String, Object> param) {
		String data = "{'username':'zhangsan','password':'zhangsan','userSource':1}";
		param.put("data", data);
		
		/*CloseableHttpResponse httpResponse = HttpExecute.postExecute(url, param);
		String reJson = HttpRequests.readRequests(httpResponse);*/
		HttpPost post = HttpExecute.postUrl(url, param);
		CloseableHttpResponse httpResponse = HttpExecute.excetePost(post);
		String reJson = HttpRequests.readRequests(httpResponse);
		HttpExecute.close(httpResponse);
		String code = json.getJsonValue(reJson, "code");
		String reData = json.getJsonValue(reJson, "data");
		Reporter.log("use real username and password login, Actual code: " + code +", data: "+ reData + " ,expected code: 0");
		Assert.assertEquals("0", code);
	}
	
	@Test(dataProvider = "defaultParam")
	public void testLogin_UsernameError(Map<String, Object> param) {
		String data = "{'username':'zhangsan1','password':'zhangsan','userSource':1}";
		param.put("data", data);
		HttpPost post = HttpExecute.postUrl(url, param);
		CloseableHttpResponse httpResponse = HttpExecute.excetePost(post);
		String reJson = HttpRequests.readRequests(httpResponse);
		HttpExecute.close(httpResponse);
		String code = json.getJsonValue(reJson, "code");
		String reData = json.getJsonValue(reJson, "msg");
		Reporter.log("use error username login, Actual results: " + code +", message: "+ reData + " ,expected result: 1");
		Assert.assertEquals("1", code);
		Assert.assertEquals(reData, "用户名不存在!");
	}
}
