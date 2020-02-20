package cj.software.experiments.camunda._10_mail.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MyMailSender
{
	private Logger logger = LogManager.getFormatterLogger();

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMail(String pFrom, String pTo, String pSubject, String pContent)
	{
		SimpleMailMessage lSimpleMailMessage = new SimpleMailMessage();
		lSimpleMailMessage.setFrom(pFrom);
		lSimpleMailMessage.setTo(pTo);
		lSimpleMailMessage.setSubject(pSubject);
		lSimpleMailMessage.setText(pContent);
		this.logger.info("send mail \"%s\" from %s to %s...", pSubject, pFrom, pTo);
		this.javaMailSender.send(lSimpleMailMessage);
		this.logger.info("mail was sent");
	}
}
