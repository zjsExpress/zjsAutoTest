package zhdd;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import zhdd.utils.ui.android.AdbDrivers;

public class AndroidUIDemo {
	public static void main(String[] args) {
		AdbDrivers ad = new AdbDrivers();
		AndroidDriver<AndroidElement> driver = null;
		try {
			/*//安装apk
			if(ad.installApk(System.getProperty("user.dir") + "/apps/BiliPlayer3.apk")){
				System.out.println("apk安装成功");
			}else{
				System.out.println("apk安装失败");
			}*/
			/*//卸载app
			if(ad.removeApp("tv.danmaku.bili")){
				System.out.println("app卸载成功");
			}else{
				System.out.println("app卸载失败");
			}*/
			/*//查找app
			if(ad.findApp("tv.danmaku.bili")){
				System.out.println("tv.danmaku.bili is installed");
			}else{
				System.out.println("tv.danmaku.bili is uninstalled");
			}*/
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("deviceName", "android");//安卓暂时无用，但必须写，ios有用
			caps.setCapability("appPackage", "com.android.deskclock");
			caps.setCapability("appActivity", ".DeskClockTabActivity");
			driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub/"), caps);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			/*
			 * 红米1s手机闹钟设置页面每次 只滑动一格
			 * appPackage:com.android.deskclock
			 * appActivity:.DeskClockTabActivity
			 */
			int x = driver.manage().window().getSize().getWidth();
			int y = driver.manage().window().getSize().getHeight();
			//打开闹钟列表
			AndroidElement listView = driver.findElement(By.id("android:id/list"));
			int listY = listView.getLocation().getY();
			//listView可以滑动，所以不能用他的高来算开始位置，用屏幕减listView开始y，得到剩余高度，再除以2加开始y，等到开始位置
			int startH = listY + (y - listY) / 2;
			//driver.swipe(x / 2, startH, x / 2, listY, 500); // 已废弃
			Thread.sleep(5000);
			//点击一个闹钟
			AndroidElement time = driver.findElement(By.xpath("//android.widget.ListView[contains" +
					"(@resource-id, 'android:id/list')]/child::android.widget.LinearLayout[1]/android.widget.TextView"));
			time.click();
			//查找分
			AndroidElement se = driver.findElement(By.xpath("//android.widget.LinearLayout[contains(@resource-id, 'miui:id/timePickerLayout')]" +
					"/child::miui.widget.NumberPicker[2]/android.widget.EditText"));
			//查找时
			AndroidElement st = driver.findElement(By.xpath("//android.widget.LinearLayout[contains(@resource-id, 'miui:id/timePickerLayout')]" +
					"/child::miui.widget.NumberPicker[1]/android.widget.EditText"));
			//得到要滑动的距离，每次滑动一格
			int stSwipY = st.getSize().getHeight();
			int stSwipX = st.getSize().getWidth();
			int stx = st.getLocation().getX();
			int sty = st.getLocation().getY();
			while(true){
				//swipe已废弃
				//driver.swipe(stx + stSwipX / 2, sty, stx + stSwipX / 2, sty + stSwipY, 500);
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				if(st.getText().equals("12")){
					break;
				}
			}
			//得到要滑动的距离，每次滑动一格
			int swipY = se.getSize().getHeight();
			int swipX = se.getSize().getWidth();
			int sex = se.getLocation().getX();
			int sey = se.getLocation().getY();
			while(true){
				//swipe已废弃
				//driver.swipe(sex + swipX / 2, sey, sex + swipX / 2, sey + swipY, 500);
				if("50".equals(se.getText())){
					break;
				}
			}
			driver.findElement(By.name("确定")).click();
			Thread.sleep(5000);
			/*
			 *哔哩哔哩页面 
			 *appPackage: tv.danmaku.bili
			 *appActivity: .ui.splash.SplashActivity
			 */
			/*AndroidElement backButton = driver.findElement(By.name("以后再说"));//升级提示
			backButton.click();*/
			/*AndroidElement seacher = driver.findElement(By.name("搜索"));
			seacher.click();
			AndroidElement text = driver.findElement(By.id("tv.danmaku.bili:id/search_src_text"));
			text.sendKeys("1234567");
			driver.pressKeyCode(122);
			Thread.sleep(2000);
			TextModify tm = new TextModify();
			tm.clean(driver, text);
			Thread.sleep(3000);
			driver.tap(1, 100, 100, 500);*/
			//AndroidElement view = driver.findElement(By.id("tv.danmaku.bili:id/recycler"));
//			TouchAction ta = new TouchAction(driver);
//			ta.press(driver.manage().window().getSize().getWidth() * 9 / 10, driver.manage().window().getSize().getHeight() / 2).
//			moveTo(driver.manage().window().getSize().getWidth() / 10, driver.manage().window().getSize().getHeight() / 2).release().perform();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			driver.quit();
		}
	}
}
