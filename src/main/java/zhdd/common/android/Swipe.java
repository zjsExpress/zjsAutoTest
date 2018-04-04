package zhdd.common.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Swipe {
	AndroidDriver<AndroidElement> driver;
	public Swipe(AndroidDriver<AndroidElement> driver){
		this.driver = driver;
	}
	private int getX(AndroidDriver<AndroidElement> driver){
		return driver.manage().window().getSize().getWidth();
	}
	private int getX(AppiumDriver<AndroidElement> driver){
		return driver.manage().window().getSize().getWidth();
	}
	private int getY(AndroidDriver<AndroidElement> driver){
		return driver.manage().window().getSize().getHeight();
	}
	private int getY(AppiumDriver<AndroidElement> driver){
		return driver.manage().window().getSize().getHeight();
	}
	//TODO 所有滑动方法重写
	/**
	 * 向左滑动 参数 驱动 、 滑动时长
	 */
	public void leftSwip(AndroidDriver<AndroidElement> driver, int duration){
		//driver.swipe(getX(driver) * 9 / 10, getY(driver) / 2, getX(driver) / 10, getY(driver) / 2, duration);
	}
	/**
	 * 向左滑动 参数 驱动 、 滑动时长
	 */
	public void leftSwip(AppiumDriver<AndroidElement> driver, int duration){
		//driver.swipe(getX(driver) * 9 / 10, getY(driver) / 2, getX(driver) / 10, getY(driver) / 2, duration);
	}
	/**
	 * 向右滑动 参数 驱动 、 滑动时长
	 */
	public void rightSwip(AndroidDriver<AndroidElement> driver, int duration){
		//driver.swipe(getX(driver) / 10, getY(driver) / 2, getX(driver) * 9 / 10, getY(driver) / 2, duration);
	}
	/**
	 * 向右滑动 参数 驱动 、 滑动时长
	 */
	public void rightSwip(AppiumDriver<AndroidElement> driver, int duration){
		//driver.swipe(getX(driver) / 10, getY(driver) / 2, getX(driver) * 9 / 10, getY(driver) / 2, duration);
	}
	/**
	 * 向下滑动 参数 驱动 、 滑动时长
	 */
	public void downSwip(AppiumDriver<AndroidElement> driver, int duration){
		//driver.swipe(getX(driver) / 2, getY(driver) / 10, getX(driver) / 2, getY(driver) * 9 / 10, duration);
	}
	/**
	 * 向下滑动 参数 驱动 、 滑动时长
	 */
	public void downSwip(AndroidDriver<AndroidElement> driver, int duration){
		//driver.swipe(getX(driver) / 2, getY(driver) / 10, getX(driver) / 2, getY(driver) * 9 / 10, duration);
	}
	/**
	 * 向上滑动 参数 驱动 、 滑动时长
	 */
	public void upSwip(AppiumDriver<AndroidElement> driver, int duration){
		//driver.swipe(getX(driver) / 2, getY(driver) * 9 / 10, getX(driver) / 2, getY(driver) / 10, duration);
	}
	/**
	 * 向上滑动 参数 驱动 、 滑动时长
	 */
	public void upSwip(AndroidDriver<AndroidElement> driver, int duration){
		//driver.swipe(getX(driver) / 2, getY(driver) * 9 / 10, getX(driver) / 2, getY(driver) / 10, duration);
	}
}
