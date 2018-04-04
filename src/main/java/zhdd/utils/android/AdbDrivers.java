package zhdd.utils.android;

import java.io.BufferedReader;

public class AdbDrivers {
	/**
	 * 安装apk到手机 参数 要安装的apk地址
	 * @throws Exception 
	 */
	public boolean installApk(String path) throws Exception{
		Process pc = AdbShell.adbCmd("install " + path);
		StringBuilder result = Utils.getOutShell(pc);
		if (result.indexOf("Success") > 0){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 卸载指定app ，参数 要卸载的包名
	 */
	public boolean removeApp(String packageName) throws Exception{
		Process pc = AdbShell.adbCmd("uninstall " + packageName);
		BufferedReader result = Utils.outShell(pc);
		if("Success".equals(result.readLine())){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 查找app是否已安装，参数 要查找的app包名
	 */
	public boolean findApp(String packageName) throws Exception{
		if(AdbShell.getSysApps().contains(packageName) ||
				AdbShell.getThridApps().contains(packageName)){
			return true;
		}else{
			return false;
		}
	}
}
