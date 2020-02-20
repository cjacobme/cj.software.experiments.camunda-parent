package cj.software.experiments.camunda._10_mail.delegate;

import java.util.Objects;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cj.software.experiments.camunda._10_mail.util.MyMailSender;

@Component
public class MailSenderDelegate
		implements
		JavaDelegate
{
	@Autowired
	private MyMailSender myMailSender;

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		String lFrom = Objects.requireNonNull((String) pExecution.getVariable("From"));
		String lTo = Objects.requireNonNull((String) pExecution.getVariable("To"));
		String lSubject = Objects.requireNonNull((String) pExecution.getVariable("Subject"));
		String lBody = Objects.requireNonNull((String) pExecution.getVariable("Body"));
		this.myMailSender.sendMail(lFrom, lTo, lSubject, lBody);
	}

}
