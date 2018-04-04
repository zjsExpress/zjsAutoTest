package zhdd.utils.database;

import redis.clients.jedis.Jedis;

public class RedisUtils {
	private static Jedis jedis ;
	/**
	 * 获取短信验证码
	 * @param mobile
	 * @return valid code
	 */
	public String getValidCode(String mobile) {
		connectRedis();
		String validCode = jedis.get("smsCode" + mobile);
		closeRedis();
		return validCode;
	}
	/**
	 * 根据key值获取value
	 * @param key
	 * @return value
	 */
	public String getValue(String key){
		connectRedis();
		String value = jedis.get(key);
		closeRedis();
		return value;
	}
	
	// 连接Redis数据库
	private void connectRedis(){
		jedis = new Jedis("r-2ze0c74e98e0b0e4.redis.rds.aliyuncs.com", 6379);
		jedis.auth("Summeriscomming2");
	}
	private void connectRedis(String hostName, String password, int port){
		//TODO 将主机名、密码、端口号写到配置文件
		jedis = new Jedis(hostName, port);
		jedis.auth(password);
	}
	// 关闭连接
	private void closeRedis(){
		jedis.close();
	}
}
