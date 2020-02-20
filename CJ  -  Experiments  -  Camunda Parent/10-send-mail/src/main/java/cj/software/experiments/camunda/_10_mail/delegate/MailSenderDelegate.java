package cj.software.experiments.camunda._10_mail.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class MailSenderDelegate
		implements
		JavaDelegate
{

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		// TODO Auto-generated method stub

	}

}
