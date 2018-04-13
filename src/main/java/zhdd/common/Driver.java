package zhdd.common;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.holmos.webtest.SeleniumDriver;
import com.holmos.webtest.exceptions.HolmosFailedError;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import zhdd.Log4jDemo;

public class Driver {
	public static WebDriver webDriver = null;
	public static AppiumDriver<WebElement> appDriver = null;
	public static SeleniumDriver selDriver = null;
	private static Logger log = Logger.getLogger(Driver.class);
	/**
	 * 
	 * @param userOS  : 传递 app 或 web
	 * @param browser 传递web时此参数传递浏览器名称，传递app时此参数传递android或ios
	 */
	public static void startDriver(String userOS, String browser) {
		//TODO 启动drive，待
		if ("web".contentEquals(userOS.toLowerCase())) {
			switch (browser.toLowerCase()) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\utils\\chromedriver2.37_v64-66.exe");
				webDriver = new ChromeDriver();
				log.info("chrome driver 启动成功");
				break;
			case "ie":
				break;
			case "firefox":
				break;
			default:
				///////////////////////////////
			}
		} else if ("app".contentEquals(userOS.toLowerCase())) {
			switch (browser.toLowerCase()) {
			case "android":
				//TODO 键值对从其他方法获取
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setCapability("app", "D:\\download\\xulei\\baiduyuedu_5340.apk");
				cap.setCapability("deviceName", "hua");
				URL u = null;
				try {
					u = new URL("http://0.0.0.0:4723/wd/hub/");
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				System.out.println("start android server");
				appDriver = new AndroidDriver<>(u, cap);
				break;
			case "ios":
				break;
			default:
				////////////////////////////////
			}
		}else{
			//系统不存在
		}

	}
}
