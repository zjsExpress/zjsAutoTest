package zhdd;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;


public class Log4jDemo {
	static String json = "{'username':'zhangsan1','password':'zhangsan','userSource':1}";
	private static Logger logger = Logger.getLogger(Log4jDemo.class);
	public static void main(String[] args) {
		Map<String, Object> params =new HashMap<String, Object>(); 
		String url = "http://mpi.carmall.tian-net.com/mpi";
		  // 记录debug级别的信息  
        logger.debug("This is debug message.");  
        // 记录info级别的信息  
        logger.info("This is info message.");  
        // 记录error级别的信息  
        logger.error("This is error message."); 
	}
}
