package zhdd.utils.ui.web;

import org.testng.annotations.Test;

import com.holmos.webtest.EngineType;
import com.holmos.webtest.utils.HolmosWindow;
/**
 * 启用chromedriver,访问指定网址
 * 
 *
 * ZhangHang@author 
 *
 */


public class ChromeBrowse {
	
	
	public void ChromeDriverSetup(){
		//设置测试地址
		String url="http://www.baidu.com";
		
		HolmosWindow.openNewWindow(EngineType.WebDriverChrome, url);
		
		
	}
	public void ChromeDriverClosed(){
		
		HolmosWindow.closeAllWindows();
	}

}
