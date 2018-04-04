package zhdd.utils.files;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Screenshot {
	Date date = new Date();

	// 生成图片名称
	public String savePngName(String name) {
		// name自定义图片名称
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String day = sdf.format(date);
		File dir = new File(System.getProperty("user.dir") + "\\result\\" + day + "_image");
		String fileName = "\\" + saveTime() + "_" + name + ".PNG";
		fileName = dir.toString() + fileName;
		// 判断目录是否存在，如果不存在创建目录
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return fileName;
	}

	// 获取当前时间
	public String saveTime() {
		date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		return sdf.format(date);
	}
	/**
	 * 截图方法，可截取android ios web和当前页面
	 * @param driver
	 * @param name
	 */
	public void saveScreenshot(Object driver, String name){
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(savePngName(name)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
