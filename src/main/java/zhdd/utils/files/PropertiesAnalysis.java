package zhdd.utils.files;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesAnalysis {
	public static String getValue(String path, String key) {
		Properties pro = new Properties();
		String value = null;
		FileInputStream fis = null;
		InputStream in = null;
		try {
			fis = new FileInputStream(path);
			in = new BufferedInputStream(fis);
			pro.load(in);
			value = pro.getProperty(key);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				in.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return value;
	}
}
