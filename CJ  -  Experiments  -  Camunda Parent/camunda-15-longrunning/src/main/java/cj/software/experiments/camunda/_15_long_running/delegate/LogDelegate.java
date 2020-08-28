package cj.software.experiments.camunda._15_long_running.delegate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Component
public class LogDelegate
		implements
		JavaDelegate
{
	private Logger logger = LogManager.getFormatterLogger();

	@Override
	public void execute(DelegateExecution execution) throws Exception
	{
		String oldCorrelationId = MDC.get(VariableNames.CORRELATION_ID);
		try
		{
			String procInstId = execution.getProcessInstanceId();
			MDC.put(VariableNames.CORRELATION_ID, procInstId);
			String message = (String) execution.getVariable("log_message");
			this.logger.info(message);
		}
		finally
		{
			MDC.put(VariableNames.CORRELATION_ID, oldCorrelationId);
		}
	}

}
