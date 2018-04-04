package zhdd;

import java.util.HashMap;
import java.util.Map;

public class Demo {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "1");
		map.put("b", "2");
		map.put("c", "3");
		new Demo().de(map);
	}
	public void de(Map<String, String> map){
		for(String key : map.keySet()){
			System.out.println(key + "\t" + map.get(key));
		}
	}
}
