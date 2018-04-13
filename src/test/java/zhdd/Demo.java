package zhdd;

import java.util.Map;

import org.testng.annotations.Test;

import com.holmos.webtest.EngineType;
import com.holmos.webtest.element.TextField;
import com.holmos.webtest.utils.HolmosWindow;

public class Demo {
	public static void main(String[] args) throws InterruptedException {
		HolmosWindow.openNewWindow(EngineType.WebDriverChrome, "http://www.baidu.com");
		username.setText("bac");
		Thread.sleep(5000);
	}
	public static TextField username = new TextField("宣传页→管理后台登录页→用户名文本框");{
		
		username.addIDLocator("kw");
	}
	public void de(Map<String, String> map){
		for(String key : map.keySet()){
			System.out.println(key + "\t" + map.get(key));
		}
	}
	
	@Test
	public void assertError() throws InterruptedException{
		HolmosWindow.openNewWindow(EngineType.WebDriverChrome, "http://www.baidu.com");
		username.setText("bac");
		Thread.sleep(5000);
	}
}
