package cj.software.experiments.camunda_04_messaging;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CheckerDelegate
		implements
		JavaDelegate
{

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		Boolean lThrow = (Boolean) pExecution.getVariable("throw");
		if (lThrow == null)
		{
			throw new NullPointerException("variable \"throw\" not set");
		}
		if (lThrow.booleanValue())
		{
			throw new RuntimeException("planned exception");
		}
	}

}
