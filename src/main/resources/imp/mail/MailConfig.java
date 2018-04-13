package imp.mail;

import java.util.Date;

public interface MailConfig {
	//邮件参数设置，
	//mail.transport.protocol
	public final static String transport = "smtp";
	//mail.smtp.host
	public final static String smtpServer = "mail.sina.com";
	//mail.smtp.auth
	public final static String smtpAuth = "true";
	//部分邮箱要求SSL安全认证，如果连接失败日志中有“连接失败，要求SSL。。。”的需要添加以下数据
	//mail.smtp.port 与 mail.smtp.socketFactory.prot
	public final static String smtpPort = "465";
	//mail.smtp.socketFactory.class
	public final static String socketClass = "javax.net.ssl.SSLSocketFactory";
	//mail.smtp.socketFactory.fallback
	public final static String fallback = "false";
	//
	//发件人账号密码
	public final static String sendFrom = "zhangdoudou0112@sina.com";
	public final static String sendAuth = "amigo1234";
	//收件人，多个收件人用逗号隔开
	public final static String sendTo = "273982495@qq.com,412305786@qq.com";
	//抄送人，多个抄送人用逗号隔开
	public final static String sendCc = "zhangdoudou@tian-net.com";
	public final static Date date = new Date();
}
