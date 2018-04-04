package zhdd.common.httpclient;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.HttpPost;

public class Header {
	/**
	 * 设置消息头
	 */
	public void requestHeader(HttpPost post) {
		//post.setHeader("Accept", "*/*");
		//post.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
		//post.setHeader("Connection", "keep-alive");
		//post.setHeader("Content-Type:", "text/html;charset=utf-8");
		//post.setHeader("User-Agent",
		//		"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.75 Safari/537.36");
		Map<String, String> header = readHeader();
		for(String key : header.keySet()){
			post.setHeader(key, header.get(key));
		}
	}
	public Map<String, String> readHeader(){
		//TODO 从文档中获取Header的key value
		return null;
	}
}
