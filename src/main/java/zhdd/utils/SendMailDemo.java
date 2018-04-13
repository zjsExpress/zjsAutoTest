package zhdd.utils;

public class SendMailDemo {

	public static void main(String[] args) {
		SendMail sm = new SendMail("smtp.sina.com");// stmp服务器
		sm.setNamePass("zhangdoudou0112@sina.com", "amigo1234");// 发件人账户密码
		sm.setSubject("test test test ");// 主题
		sm.setFrom("zhangdoudou0112@sina.com");// 发件人
		sm.setTo("273982495@qq.com;412305786@qq.com");
		// sm.addFileAffix("‪E:\\OnKeyDetector.log");
		StringBuffer bs = new StringBuffer();
		bs.append("zhang: \n");
		bs.append("test send mail");
		sm.setBody("set body method");
		sm.setNeedAuth(true);
		boolean b = sm.setOut();
		if (b) {
			System.out.println("邮件发送成功");
		} else {
			System.err.println("邮件发送失败");
		}
	}

	/**
	 * 方法描述：发送带附件的邮件
	 * 
	 * @throws UnsupportedEncodingException
	 */
	/*public static boolean sendEmailWithAttachment(Email email) throws UnsupportedEncodingException {
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.sina.com");
		prop.put("mail.smtp.auth", "true");
		Authenticator auth = new MailAuthenticator();
		Session session = Session.getDefaultInstance(prop, auth);
		Message message = new MimeMessage(session);
		boolean flag = false;
		try {
			message.setSubject(email.getEmail_subject());// 设置邮件主题
			message.setHeader("Header:", email.getEmail_header()); // 设置邮件标题
			message.setSentDate(new Date()); // 设置发送时间
			Address addressFrom = new InternetAddress(mailAddress, mailAccount); // 设置发信人地址
			message.setFrom(addressFrom);
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(email.getEmail_content(), "text/html;charset=utf-8");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			MimeBodyPart mimeBodyPart;
			// 多个附件
			for (int i = 0; i < email.getEmail_file().size(); i++) {
				Map<String, String> map = email.getEmail_file().get(i);
				String fileSource = "";
				String fileName = "";
				for (String key : map.keySet()) {
					fileSource = key;
					fileName = map.get(key);
				}
				mimeBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(fileSource);
				mimeBodyPart.setDataHandler(new DataHandler(source));
				mimeBodyPart.setFileName(MimeUtility.encodeText(fileName));
				multipart.addBodyPart(mimeBodyPart);// Put parts in
			}
			message.setContent(multipart);

			// 设置多个收件人地址
			List<String> list = email.getEmail_to();
			String toAddress = SendEmail.getAddress(list);

			InternetAddress[] internetAddressTo = new InternetAddress().parse(toAddress);
			message.setRecipients(Message.RecipientType.TO, internetAddressTo);

			// 设置多个抄送地址
			String ccAddress = SendEmail.getAddress(email.getEmail_cc());
			InternetAddress[] internetAddressCC = new InternetAddress().parse(ccAddress);
			message.setRecipients(Message.RecipientType.CC, internetAddressCC);

			message.saveChanges();
			System.out.println("开始发送邮件……");

			Transport.send(message);
			System.out.println("发送成功！");
			flag = true;
		} catch (MessagingException e) {
			System.out.println("发送失败！");
			flag = false;
			e.printStackTrace();

		}
		return flag;
	}*/
}
