package cj.software.experimtens.camunda._12_multiple_receives.delegate;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class LogDelegate
		implements
		JavaDelegate
{
	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		String lBusinessKey = pExecution.getBusinessKey();
		String lLoggerName = (String) pExecution.getVariable("logger");
		String lLogLevel = (String) pExecution.getVariable("log_level");
		String lMessage = (String) pExecution.getVariable("log_message");
		Logger lLogger = LogManager.getFormatterLogger(lLoggerName);
		Level lLevel = Level.toLevel(lLogLevel);
		lLogger.log(lLevel, "%s:%s: %s", lBusinessKey, lLogger, lMessage);
	}
}
