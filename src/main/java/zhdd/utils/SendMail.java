package zhdd.mail;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {
	public MimeMessage mimeMsg; // 要发送的email信息
	private Session session;
	private Properties props;
	private String userName = null;
	private String passWord = null;
	private Multipart mp;// 存放邮件的title内容和附件

	public SendMail(String stmp) {
		setSmtpHost(stmp);
		createMimeMessage();
	}

	private boolean createMimeMessage() {
		try {
			System.out.println("Session begin-------");
			session = Session.getInstance(props, null);
		} catch (Exception e) {
			System.err.println("Session.getInstance faild. " + e);
			return false;
		}
		System.out.println("MimeMessage begin---------");
		try {
			mimeMsg = new MimeMessage(session);
			mp = new MimeMultipart();
			return true;
		} catch (Exception e) {
			System.err.println("MimeMessage faild. " + e);
			return false;
		}
	}

	private void setSmtpHost(String hostName) {
		System.out.println("mail.stmp.host=" + hostName);
		if (props == null) {
			props = System.getProperties();
		}
		props.put("mail.smtp.host", hostName);
	}

	public void setNeedAuth(boolean need) {
		System.out.println("mail.smtp.auth=" + need);
		if (props == null) {
			props = System.getProperties();
		}
		if (need) {
			props.put("mail.smtp.auth", "true");
		} else {
			props.put("mail.smtp.auth", "false");
		}
	}

	public void setNamePass(String name, String pass) {
		this.userName = name;
		this.passWord = pass;
	}

	public boolean setSubject(String mailSubject) {
		System.out.println("set title begin");
		try {
			if (!mailSubject.equals("") && mailSubject != null) {
				mimeMsg.setSubject(mailSubject);
			}
			return true;
		} catch (Exception e) {
			System.err.println("set title faild.");
			return false;
		}
	}

	/**
	 * 添加附件
	 */
	public boolean addFileAffix(String fileName) {
		System.out.println("增加附件");
		if (fileName.equals("") || fileName == null) {
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
		} catch (Exception e) {
			System.err.println("增加附件：" + fileName + " faild. " + e);
			return false;
		}
	}

	public boolean setFrom(String from) {
		System.out.println("set from.");
		try {
			mimeMsg.setFrom(new InternetAddress(from));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean setTo(String to) {
		System.out.println("set to.");
		if (to == null || to.equals("")) {
			return false;
		}
		try {
			mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean setCopyTo(String copyTo) {
		if (copyTo.equals("") || copyTo == null) {
			return false;
		}
		try {
			String copy[];
			copy = copyTo.split(";");
			for (int i = 0; i < copy.length; i++) {
				mimeMsg.setRecipients(Message.RecipientType.TO, (Address[]) InternetAddress.parse(copy[i]));
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 设置邮件内容
	 */
	public boolean setBody(String mailBody) {
		try {
			BodyPart bp = new MimeBodyPart();
			bp.setContent("<meta http-equiv=Context-Type context=text/html;charset=gb2312>" + mailBody,
					"text/html;charset=GB2312");
			mp.addBodyPart(bp);
			return true;
		} catch (Exception e) {
			System.err.println("set context faild. " + e);
			return false;
		}
	}

	public boolean setHtml(String htmlPath) {
		try {
			if (!htmlPath.equals("") || htmlPath != null) {
				BodyPart mbp = new MimeBodyPart();
				DataSource ds = new FileDataSource(htmlPath);
				mbp.setDataHandler(new DataHandler(ds));
				mbp.setHeader("Context-ID", "meme");
				mp.addBodyPart(mbp);
			}
			return true;
		} catch (Exception e) {
			System.err.println("set html faild. " + e);
			return false;
		}
	}
	
	public boolean setOut(){
		try {
			mimeMsg.setContent(mp);
			mimeMsg.saveChanges();
			System.out.println("正在send mail");
			Session mailSession = Session.getInstance(props, null);
			Transport tp = mailSession.getTransport("smtp");
			tp.connect((String)props.getProperty("mail.stmp.host"), userName, passWord);
			tp.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));
			tp.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.CC));
			tp.close();
			return true;
		} catch (Exception e) {
			System.err.println("set out faild. " + e);
			return false;
		}
	}
}
