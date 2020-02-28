package cj.software.experiments.camunda._10_mail.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class StoreVariableDelegate
		implements
		JavaDelegate
{

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		String lBusinessKey = pExecution.getBusinessKey();
		pExecution.setVariable("VorgangsId", lBusinessKey);
	}

}
