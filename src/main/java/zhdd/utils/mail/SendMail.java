package zhdd.utils.mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import imp.mail.MailConfig;
/**
 * 设置邮件系统，所有数据从接口MailConfig中获取，
 * @author Zhdd
 * @date 2018-4-13
 */
public class SendMail implements MailConfig {

	public MimeMessage mimeMsg; // 要发送的email信息
	private Session session; //
	private Properties props; // 配置数据
	private Multipart mp;// 存放邮件的title内容和附件
	private Logger log = Logger.getLogger(this.getClass());

	public SendMail() {
		// 设置mail服务器数据
		setSmtpHost();
		// 创建mail数据文件
		if (createMimeMessage()) {
			log.info("邮件数据创建成功");
		} else {
			log.error("邮件数据创建失败");
		}
		;
	}

	/**
	 * 创建邮件信息
	 * 
	 * @return 返回是否创建成功
	 */
	private boolean createMimeMessage() {
		try {
			log.info("开始创建 Session");
			session = Session.getInstance(props, null);
		} catch (Exception e) {
			log.error("创建Session.getInstance 失败。 " + e);
			return false;
		}
		log.info("开始创建 MimeMessage ");
		try {
			mimeMsg = new MimeMessage(session);
			mp = new MimeMultipart();
			return true;
		} catch (Exception e) {
			log.error("创建 MimeMessage 失败。 " + e);
			return false;
		}
	}

	/**
	 * 创建mail服务器配置数据
	 * 
	 * @return 是否创建成功
	 */
	private void setSmtpHost() {
		log.info("创建mail服务器配置数据");
		if (props == null) {
			props = System.getProperties();
		}
		props.setProperty("mail.transport.protocol", transport);
		props.setProperty("mail.smtp.host", smtpServer);
		props.setProperty("mail.smtp.auth", smtpAuth);
		// 部分邮箱要求SSL安全认证，如果连接失败日志中有“连接失败，要求SSL。。。”的需要添加以下数据

		/*
		 * props.setProperty("mail.smtp.port", smtpPort);
		 * props.setProperty("mail.smtp.socketFactory.prot", smtpPort);
		 * props.setProperty("mail.smtp.socketFactory.class", socketClass);
		 * props.setProperty("mail.smtp.socketFactory.fallbac", fallback);
		 */
	}

	/**
	 * 设置发件人
	 * 
	 * @param from
	 *            发件人
	 * @return 是否设置成功
	 */
	public boolean setFrom() {
		log.info("设置发件人。");
		try {
			// 发件人地址、名称
			InternetAddress ia = new InternetAddress(sendFrom, "张豆豆", "UTF-8");
			mimeMsg.setFrom(ia);
			log.info("成功设置发件人");
			return true;
		} catch (MessagingException e) {
			log.error("设置发件人失败" + e);
			return false;
		} catch (UnsupportedEncodingException e) {
			log.error("设置发件人失败" + e);
			return false;
		}
	}

	/**
	 * 设置收件人，多个收件人分号隔开
	 * 
	 * @param to
	 *            收件人列表
	 * @return 是否设置成功
	 */
	public boolean setTo() {
		System.out.println("设置收件人");
		try {
			mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendTo));
			log.info("收件人设置成功");
			return true;
		} catch (MessagingException e) {
			log.info("收件人调协失败" + e);
			return false;
		}
	}

	/**
	 * 设置抄送人
	 * 
	 * @return 是否成功设置抄送人
	 */

	public boolean setCopyTo() {
		try {
			mimeMsg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(sendCc));
			log.info("成功设置抄送名单");
			return true;
		} catch (MessagingException e) {
			log.error("设置抄送名单失败");
			return false;
		}
	}

	/**
	 * 设置邮件主题
	 * 
	 * @param title
	 *            邮件主题
	 * @return 是否设置成功
	 */
	public boolean setSubject(String mailSubject) {
		try {
			if (!mailSubject.equals("") && mailSubject != null) {
				mimeMsg.setSubject(mailSubject, "UTF-8");
				log.info("成功设置邮件主题");
			}
			return true;
		} catch (MessagingException e) {
			log.error("set title faild.");
			return false;
		}
	}

	/**
	 * 增加附件
	 * 
	 * @param fileName
	 *            附件名称，多个附件用 分号隔开
	 * @return 是否添加成功 TODO 暂时不能添加成功
	 */
	public boolean addFileAffix(String fileName) {
		log.info("开始增加附件");
		if (fileName.equals("") || fileName == null) {
			log.info("没有设置附件名称");
			return false;
		}
		String file[];
		file = fileName.split(";");
		System.out.println("你有" + file.length + "个附件。");
		try {
			for (int i = 0; i < file.length; i++) {
				BodyPart bp = new MimeBodyPart();
				FileDataSource fileds = new FileDataSource(file[i]);
				bp.setDataHandler(new DataHandler(fileds));
				bp.setFileName(fileds.getName());
				mp.addBodyPart(bp);
			}
			return true;
		} catch (MessagingException e) {
			log.error("增加附件：" + fileName + " 失败。 " + e);
			return false;
		}
	}

	/**
	 * 设置邮件内容 TODO
	 */
	public boolean setBody(String mailBody) {
		try {
			BodyPart bp = new MimeBodyPart();
			bp.setContent("<meta http-equiv=Context-Type context=text/html;charset=gb2312>" + mailBody,
					"text/html;charset=GB2312");
			mp.addBodyPart(bp);
			return true;
		} catch (MessagingException e) {
			log.error("set context faild. " + e);
			return false;
		}
	}

	public boolean setHtml(String htmlPath) {
		try {// TODO 设置邮件内容
			if (!htmlPath.equals("") || htmlPath != null) {
				BodyPart mbp = new MimeBodyPart();
				DataSource ds = new FileDataSource(htmlPath);
				mbp.setDataHandler(new DataHandler(ds));
				mbp.setHeader("Context-ID", "meme");
				mp.addBodyPart(mbp);
			}
			return true;
		} catch (MessagingException e) {
			log.error("set html faild. " + e);
			return false;
		}
	}

	/**
	 * TODO
	 * 
	 * @return 是否发送成功
	 */
	public boolean setOut() {
		try {
			mimeMsg.setContent(mp);
			mimeMsg.saveChanges();
			log.info("正在发送邮件");
			Session mailSession = Session.getInstance(props, null);
			Transport tp = mailSession.getTransport(transport);
			tp.connect((String) props.getProperty("mail.stmp.host"), sendFrom, sendAuth);
			tp.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));
			tp.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.CC));
			tp.close();
			return true;
		} catch (MessagingException e) {
			log.error("发送邮件失败 " + e);
			return false;
		}
	}
}
