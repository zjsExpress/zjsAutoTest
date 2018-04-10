package zhdd.common;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.holmos.webtest.exceptions.HolmosFailedError;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Driver {
	public static WebDriver webDriver = null;
	public static AppiumDriver<WebElement> appDriver = null;
	/**
	 * 
	 * @param userOS android, web, ios
	 * @param browser 
	 */
	public static void startDriver(String userOS, String browser) {
		//TODO 启动drive，待
		if ("web".contentEquals(userOS.toLowerCase())) {
			switch (browser.toLowerCase()) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\utils\\chromedriver2.37_v64-66.exe");
				webDriver = new ChromeDriver();
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
