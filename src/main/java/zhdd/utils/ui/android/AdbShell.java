package zhdd.utils.ui.android;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class AdbShell {
	/**
	 * adb shell 命令
	 */
	public static Process adbShell(String command) throws Exception {
		return adbCmd("shell " + command);
		
	}
	/**
	 * adb 命令
	 */
	public static Process adbCmd(String command) throws Exception {
		return cmd("adb " + command);
	}

	private static Process cmd(String command) throws Exception {
		return Runtime.getRuntime().exec(command);
	}

	/**
	 * 查找第三方app列表
	 */
	public static ArrayList<String> getThridApps() throws Exception{
		ArrayList<String> thridApps = new ArrayList<String>();
		Process pc = adbShell("pm list packages -3");
		StringBuilder sb = Utils.getOutShell(pc);
		//正则表达式规则
		Pattern pattern = Pattern.compile("[a-z]+:[a-zA-Z0-9.]+");
		ArrayList<String> result = Utils.MatcherString(pattern, sb.toString());
		for(String s : result){
			thridApps.add(s.split(":")[1]);
		}
		return thridApps;
	}
	/**
	 * 查找系统app列表
	 */
	public static ArrayList<String> getSysApps() throws Exception{
		ArrayList<String> sysApps = new ArrayList<String>();
		Process pc = adbShell("pm list packages -s");
		StringBuilder br = Utils.getOutShell(pc);
		Pattern pattern = Pattern.compile("[a-z]+:[a-zA-Z0-9.]+");
		ArrayList<String> result = Utils.MatcherString(pattern, br.toString());
		for(String s : result){
			sysApps.add(s.split(":")[1]);
		}
		return sysApps;
	}
}
