package zhdd.utils.android;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	/**
	 * 返回进程名列表
	 */
	public static StringBuilder getOutShell(Process pc) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader result = outShell(pc);
		String line;
		while((line = result.readLine()) != null){
			sb.append(line + System.getProperty("line.separator"));
		}
		result.close();
		return sb;
	}
	/**
	 * 返回读取进程数据流结果
	 */
	public static BufferedReader outShell(Process pc) {
		InputStream is = pc.getInputStream();
		BufferedInputStream bis = new BufferedInputStream(is);
		InputStreamReader isr = new InputStreamReader(bis);
		BufferedReader br = new BufferedReader(isr);
		return br;
	}
	/**
	 * 正则表达式查找package
	 */
	public static ArrayList<String> MatcherString(Pattern pattern, String br) {
		Matcher macher = pattern.matcher(br);
		ArrayList<String> result = new ArrayList<String>();
		while(macher.find()){
			result.add(macher.group());
		}
		return result;
	}
}
