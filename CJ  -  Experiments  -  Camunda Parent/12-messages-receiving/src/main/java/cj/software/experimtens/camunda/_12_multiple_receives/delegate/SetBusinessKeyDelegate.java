package cj.software.experimtens.camunda._12_multiple_receives.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class SetBusinessKeyDelegate
		implements
		JavaDelegate
{

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		String lProcessKey = (String) pExecution.getVariable("processKey");
		pExecution.getProcessInstance().setProcessBusinessKey(lProcessKey);
	}

}
