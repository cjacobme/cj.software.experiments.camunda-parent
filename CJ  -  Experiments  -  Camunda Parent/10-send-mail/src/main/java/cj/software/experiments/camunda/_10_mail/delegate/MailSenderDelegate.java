package cj.software.experiments.camunda._10_mail.delegate;

import java.net.URL;
import java.util.Objects;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cj.software.experiments.camunda._10_mail.spring.ConfigurationHolder;
import cj.software.experiments.camunda._10_mail.util.MyMailSender;

@Component
public class MailSenderDelegate
		implements
		JavaDelegate
{
	@Autowired
	private MyMailSender myMailSender;

	@Autowired
	private ConfigurationHolder configurationHolder;

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		String lFrom = Objects.requireNonNull((String) pExecution.getVariable("From"));
		String lTo = Objects.requireNonNull((String) pExecution.getVariable("To"));
		String lSubject = Objects.requireNonNull((String) pExecution.getVariable("Subject"));
		String lBody = Objects.requireNonNull((String) pExecution.getVariable("Body"));
		lBody = this.extendBody(lBody);
		this.myMailSender.sendMail(lFrom, lTo, lSubject, lBody);
	}

	private String extendBody(String pSource)
	{
		StringBuilder lSB = new StringBuilder(pSource).append("\n\n\n");
		lSB.append("please visit the following URL: ");
		lSB.append(this.constructPath());
		return lSB.toString();
	}

	private String constructPath()
	{
		URL lBaseUrl = this.configurationHolder.getAddtlUrl();
		String lResult = lBaseUrl
				+ "app/tasklist/default/#/"
				+ "?sorting=%5B%7B%22sortBy%22:%22priority%22,%22sortOrder%22:%22desc%22%7D%5D";
		return lResult;
	}
}
