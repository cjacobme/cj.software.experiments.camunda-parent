package cj.software.experiments.camunda._09_optimize.delegate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class Notification
		implements
		JavaDelegate
{
	private Logger logger = LogManager.getFormatterLogger();

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		String lCorrelationId = pExecution.getProcessInstanceId();
		String lNote = (String) pExecution.getVariable("note");
		this.logger.info("%s: %s", lCorrelationId, lNote);
	}

}
