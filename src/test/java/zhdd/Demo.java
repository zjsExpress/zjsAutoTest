package zhdd;

import java.util.HashMap;
import java.util.Map;

import zhdd.utils.files.PropertiesAnalysis;

public class Demo {
	public static void main(String[] args) {
		/*Map<String, String> map = new HashMap<String, String>();
		map.put("a", "1");
		map.put("b", "2");
		map.put("c", "3");
		new Demo().de(map);*/
		String path = System.getProperty("user.dir") + "\\log4j.properties"; 
		System.out.println(PropertiesAnalysis.getValue(path, "log4j.appender.stdout"));
	}
	public void de(Map<String, String> map){
		for(String key : map.keySet()){
			System.out.println(key + "\t" + map.get(key));
		}
	}
}
