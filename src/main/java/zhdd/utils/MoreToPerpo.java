package zhdd.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MoreToPerpo {
	public static String myEmailAccount = "zhangdoudou0112@sina.com";
	public static String myEmailPassword = "amigo1234";
	public static String myEmailSMTPHost = "smtp.sina.com";
	public static String sendTo = "273982495@qq.com,412305786@qq.com";
	public static String sendCC = "zhangdoudou@tian-net.com";
	public static void main(String[] args) throws Exception {
		//参数配置
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.host", myEmailSMTPHost);
		props.setProperty("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props);//根据配置创建对象，用于和邮件服务器交互
		session.setDebug(true);//开启debug模式
		MimeMessage message = createMimeMessage(session, myEmailAccount, sendTo, sendCC);
		Transport transport = session.getTransport();
		transport.connect(myEmailAccount, myEmailPassword);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}
	public static MimeMessage createMimeMessage(Session session, String sendMail, String to, String cc) throws Exception{
		//创建一封邮件
		MimeMessage message = new MimeMessage(session);
		//from
		InternetAddress ia = new InternetAddress(sendMail, "test test test", "utf-8");
		message.setFrom(ia);
		//to
		InternetAddress[] sendTo = InternetAddress.parse(to);
		message.setRecipients(MimeMessage.RecipientType.TO, sendTo);
		InternetAddress[] sentCC = InternetAddress.parse(cc);
		message.setRecipients(MimeMessage.RecipientType.CC, sentCC);
		//主题
		message.setSubject("test mail", "utf-8");
		//正文
		message.setContent("this is a test mail", "text/html;charset=utf-8");
		//发送时间
		message.setSentDate(new Date());
		//保存设置
		message.saveChanges();
		return message;
	}
}
