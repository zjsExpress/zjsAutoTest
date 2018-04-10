package zhdd.utils.ui;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import zhdd.common.Driver;
import com.holmos.webtest.utils.HolmosWindow;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class HighLightElement {
	/**
	 * 通用元素高亮
	 * 
	 * @param type
	 *            传递字符串 android or web or ios
	 * @param driver
	 * @param element
	 */
	public static void setElementStyle(String type, Object element) {
		switch (type) {
		case "android":

			break;
		case "web":
			JavascriptExecutor js = (JavascriptExecutor) Driver.webDriver;
			//JavascriptExecutor js = (JavascriptExecutor) (WebDriver)HolmosWindow.getDriver();
			js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
					(WebElement) element);
			break;
		case "ios":

			break;

		default:
			break;
		}

	}
}
