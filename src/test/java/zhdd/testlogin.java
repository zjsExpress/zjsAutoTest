package zhdd;

import org.testng.annotations.Test;

import com.holmos.webtest.element.TextField;

import zhdd.utils.ui.HighLightElement;
import zhdd.utils.ui.web.ChromeBrowse;

public class testlogin {
	@Test
	public void login() throws InterruptedException{
		
		ChromeBrowse setUp = new ChromeBrowse();
		setUp.ChromeDriverSetup();
		//username.setText("ddddd");
		Thread.sleep(5000);
		HighLightElement.setElementStyle("web", username);
		setUp.ChromeDriverClosed();
	}
	
	public static TextField username = new TextField("宣传页→管理后台登录页→用户名文本框");{
		
		username.addIDLocator("kw");
	}

}
