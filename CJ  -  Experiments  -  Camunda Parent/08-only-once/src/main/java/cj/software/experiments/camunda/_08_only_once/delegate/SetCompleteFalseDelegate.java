package cj.software.experiments.camunda._08_only_once.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SetCompleteFalseDelegate
		implements
		JavaDelegate
{

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		pExecution.setVariable("complete", Boolean.FALSE);
	}

}
