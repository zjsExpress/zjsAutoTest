package zhdd;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;

import com.holmos.webtest.element.TextField;
import com.holmos.webtest.utils.HolmosWindow;

import zhdd.common.Driver;
import zhdd.utils.files.PropertiesAnalysis;
import zhdd.utils.ui.HighLightElements;
import zhdd.utils.ui.web.ChromeBrowse;

public class Demo {
	public static void main(String[] args) throws InterruptedException {
		/*Map<String, String> map = new HashMap<String, String>();
		map.put("a", "1");
		map.put("b", "2");
		map.put("c", "3");
		new Demo().de(map);*/
		/*String path = System.getProperty("user.dir") + "\\log4j.properties"; 
		System.out.println(PropertiesAnalysis.getValue(path, "log4j.appender.stdout"));*/
		/*System.out.println("app".equalsIgnoreCase("APP"));
		System.out.println("app".equals("APP"));
		System.out.println("w".toUpperCase());*/
		/*Driver.startDriver("web", "chrome");
		Driver.webDriver.get("http://www.baidu.com");
		Driver.webDriver.findElement(By.id("kw")).sendKeys("abc");
		Thread.sleep(5000);
		Driver.webDriver.close();*/
		/*Driver.startDriver("app", "android");
		Thread.sleep(10000);
		Driver.appDriver.close();*/
		ChromeBrowse setUp = new ChromeBrowse();
		setUp.ChromeDriverSetup();
		//username.setText("ddddd");
		Thread.sleep(5000);
		HighLightElements.setElementStyle("web",username);
		setUp.ChromeDriverClosed();
		
	}
	public static TextField username = new TextField("宣传页→管理后台登录页→用户名文本框");{
		
		username.addIDLocator("kw");
	}
	public void de(Map<String, String> map){
		for(String key : map.keySet()){
			System.out.println(key + "\t" + map.get(key));
		}
	}
}
