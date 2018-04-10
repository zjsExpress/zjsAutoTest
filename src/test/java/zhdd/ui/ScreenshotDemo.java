package zhdd.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import zhdd.common.Driver;
import zhdd.utils.files.Screenshot;
import zhdd.utils.ui.HighLightElement;

public class ScreenshotDemo {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.bin",
				"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		Driver.startDriver("web", "chrome");;
		Driver.webDriver.get("http://www.baidu.com");
		Thread.sleep(3000);
		WebElement element = Driver.webDriver.findElement(By.id("kw"));
		HighLightElement.setElementStyle("web", element);
		Thread.sleep(2000);
		Screenshot.saveScreenshot("webdriver_method");
		Thread.sleep(1000);
		Driver.webDriver.close();

	}

}
