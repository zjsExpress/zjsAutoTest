package zhdd.http;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.testng.annotations.Test;

import zhdd.common.httpclient.HttpExecute;
import zhdd.common.httpclient.HttpRequests;
import zhdd.utils.database.RedisUtils;

public class RegistTest {
	HttpRequests request = new HttpRequests();
	RedisUtils getRedis = new RedisUtils();
	// 公共参数
	String url = "http://mpi.demo.cas-tian.com/mpi";
	String version = "1.0";
	String appCode = "org.yogomo.lw.app";

	@Test
	public void regist() throws InterruptedException {
		// 获取验证码
		String mobile = "18612984129";
		String vApi = "mpi.kl.validCode";

		// 获取验证码
		Map<String, Object> param = new HashMap<String, Object>();
		// param.put("mobile", mobile);
		String data = "{'mobile':'" + mobile + "','type':'1'}";
		param.put("data", data);
		param.put("api", vApi);
		param.put("version", version);
		param.put("appCode", appCode);
		HttpPost post = HttpExecute.postUrl(url, param);
		CloseableHttpResponse httpResponse = HttpExecute.excetePost(post);
		String vai = HttpRequests.readRequests(httpResponse);
		HttpExecute.close(httpResponse);
		// 注册
		String regApi = "mobile.regist";
		String username = "zhangzhuc";
		String userPwd = "zhangzhuc";
		String validCode = "";
		Thread.sleep(10000);
		validCode = getRedis.getValidCode(mobile);
		Map<String, Object> paramReg = new HashMap<String, Object>();
		String regData = "{'username':'" + username + "','userpword':'" + userPwd + "','mobile':'" + mobile
				+ "','validCode':'" + validCode + "'}";
		paramReg.put("data", regData);
		paramReg.put("version", version);
		paramReg.put("appCode", appCode);
		paramReg.put("api", regApi);
		post = HttpExecute.postUrl(url, paramReg);
		httpResponse = HttpExecute.excetePost(post);
		String reg = HttpRequests.readRequests(httpResponse);
		HttpExecute.close(httpResponse);
	}
}
