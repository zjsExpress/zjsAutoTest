package zhdd.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import zhdd.utils.files.Screenshot;
import zhdd.utils.ui.HighLightElement;

public class ScreenshotDemo {

	public static void main(String[] args) throws InterruptedException {
		Screenshot s = new Screenshot();
		System.setProperty("webdriver.chrome.bin",
				"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		System.setProperty("webdriver.chrome.driver",
				"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.baidu.com");
		Thread.sleep(3000);
		WebElement element = driver.findElement(By.id("kw"));
		HighLightElement.setElementStyle("web", driver, element);
		Thread.sleep(2000);
		s.saveScreenshot(driver, "webdriver_method");
		Thread.sleep(1000);
		driver.close();
		System.out.println(s.savePngName("abc"));

	}

}
