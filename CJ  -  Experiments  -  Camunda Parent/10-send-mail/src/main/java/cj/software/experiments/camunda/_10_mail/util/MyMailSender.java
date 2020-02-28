package cj.software.experiments.camunda._10_mail.util;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.time.OffsetDateTime;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MyMailSender
{
	private Logger logger = LogManager.getFormatterLogger();

	@Autowired
	private MailContentBuilder mailContentBuilder;

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMail(
			String pBusinessKey,
			String pFrom,
			String pTo,
			String pSubject,
			String pContent,
			String pLinkTo) throws MessagingException,
			IOException
	{
		MimeMessage lMimeMessage = this.javaMailSender.createMimeMessage();
		MimeMessageHelper lHelper = new MimeMessageHelper(lMimeMessage, true, "UTF-8");
		lHelper.setFrom(pFrom);
		lHelper.setTo(pTo);
		lHelper.setSubject(pSubject);
		String lContentTransformed = this.mailContentBuilder.build(pBusinessKey, pContent, pLinkTo);
		lHelper.setText(pContent, lContentTransformed);

		String lCsvContents = this.createCsvContents();
		ByteArrayResource lBAR = new ByteArrayResource(
				lCsvContents.getBytes(Charset.forName("UTF-8")));
		lHelper.addAttachment("data.csv", lBAR);

		this.logger.info("send mail \"%s\" from %s to %s...", pSubject, pFrom, pTo);
		this.javaMailSender.send(lMimeMessage);
		this.logger.info("mail was sent");

	}

	private String createCsvContents() throws IOException
	{
		try (StringWriter lSW = new StringWriter())
		{
			try (CSVPrinter lCsvPrinter = new CSVPrinter(lSW, CSVFormat.DEFAULT.withDelimiter(';')))
			{
				lCsvPrinter.printRecord("timestamp", OffsetDateTime.now());
				lCsvPrinter.printRecord("value", 42);
				lCsvPrinter.printRecord("remark", "Answer to all questions");
			}
			String lResult = lSW.toString();
			return lResult;
		}
	}
}
