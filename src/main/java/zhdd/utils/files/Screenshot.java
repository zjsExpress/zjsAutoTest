package zhdd.utils.files;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import zhdd.common.Driver;

public class Screenshot {
	static Date date = new Date();
	private static Logger log = Logger.getLogger(Screenshot.class);

	// 生成图片名称
	public static String savePngName(String name) {
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
	private static String saveTime() {
		date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		return sdf.format(date);
	}
	/**
	 * 截图方法，可截取android ios web和当前页面
	 * @param driver
	 * @param name
	 */
	public static void saveScreenshot(String name){
		File srcFile = ((TakesScreenshot) Driver.webDriver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(savePngName(name)));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
