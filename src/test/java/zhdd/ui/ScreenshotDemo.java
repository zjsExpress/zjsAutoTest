package zhdd.ui;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import zhdd.common.Driver;
import zhdd.utils.files.Screenshot;
import zhdd.utils.ui.HighLightElement;

public class ScreenshotDemo {
	private static Logger log = Logger.getLogger(ScreenshotDemo.class);

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.bin", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		Driver.startDriver("web", "chrome");
		;
		Driver.webDriver.get("http://www.baidu.com");
		log.info("打开百度页面");
		Thread.sleep(3000);
		WebElement element = Driver.webDriver.findElement(By.id("kw"));
		HighLightElement.setElementStyle("web", element);
		Thread.sleep(2000);
		Screenshot.saveScreenshot("webdriver_method");
		try {
			Assert.assertEquals("abc", "gcd");
			log.info("测试成功");
		} catch (AssertionError E) {
			log.error(E);
		}
		Thread.sleep(1000);
		Driver.webDriver.close();

	}

}
