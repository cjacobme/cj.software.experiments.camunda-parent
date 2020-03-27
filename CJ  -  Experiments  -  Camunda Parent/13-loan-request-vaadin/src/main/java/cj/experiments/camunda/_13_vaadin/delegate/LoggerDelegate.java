package cj.experiments.camunda._13_vaadin.delegate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class LoggerDelegate
		implements
		JavaDelegate
{
	private Logger logger = LogManager.getFormatterLogger();

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		String lProcInstId = pExecution.getProcessInstanceId();
		String lMessage = (String) pExecution.getVariable("logMessage");
		this.logger.info("%s: %s", lProcInstId, lMessage);
	}

}
