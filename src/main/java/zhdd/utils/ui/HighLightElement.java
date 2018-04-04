package zhdd.utils.ui;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class HighLightElement {
	/**
	 * 浏览器元素高亮
	 * @param driver
	 * @param element
	 */
	public static void setElementStyle(WebDriver driver, WebElement element){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	}
	/**
	 * 安卓元素高亮
	 * @param driver
	 * @param element
	 */
	public void setElementStyle(AndroidDriver<AndroidElement> driver, AndroidElement element){
		//TODO 设置android元素高亮
		
	}
	/**
	 * 安卓元素高亮
	 * @param driver
	 * @param element
	 */
	public void setElementStyle(AppiumDriver<AndroidElement> driver, AndroidElement element){
		//TODO 设置android元素高亮
	}
	/**
	 * IOS元素高亮
	 * @param driver
	 * @param element
	 */
	public void setElementStyle(AppiumDriver<IOSElement> driver, IOSElement element){
		//TODO 设置 ios 元素高亮
	}
	/**
	 * IOS元素高亮
	 * @param driver
	 * @param element
	 */
	public void setElementStyle(IOSDriver<IOSElement> driver, IOSElement element){
		//TODO 设置 ios 元素高亮
	}
}
