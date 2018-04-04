package zhdd.common.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class TextModify {
	/**
	 * 清除输入框中文字， 参数： 驱动器   元素
	 */
	public void clean(AndroidDriver<AndroidElement> driver, AndroidElement element){
		element.click();
		driver.pressKeyCode(123);//将光标移到最后
		int length = element.getText().length();
		for(int i = 0; i < length; i++){
			driver.pressKeyCode(67);//退格键，删除一个字符
		}
	}
	/**
	 * 清除输入框中文字， 参数： 驱动器   元素
	 */
	public void clean(AppiumDriver<AndroidElement> driver, AndroidElement element){
		element.click();
		((AndroidDriver<AndroidElement>) driver).pressKeyCode(123);//将光标移到最后
		int length = element.getText().length();
		for(int i = 0; i < length; i++){
			((AndroidDriver<AndroidElement>) driver).pressKeyCode(67);//退格键，删除一个字符
		}
	}
}
