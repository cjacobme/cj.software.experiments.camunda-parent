package cj.software.experiments.camunda._09_optimize.delegate;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class Processor
		implements
		JavaDelegate
{
	private Logger logger = LogManager.getFormatterLogger();

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		String lCorrelationId = pExecution.getProcessInstanceId();
		String lDepartment = (String) pExecution.getVariable("department");
		String lDurationStr = (String) pExecution.getVariable("duration");
		Integer lDuration = Integer.parseInt(lDurationStr);
		this.logger.info(
				"%s: department %s now sleep for %d seconds",
				lCorrelationId,
				lDepartment,
				lDuration);
		TimeUnit.SECONDS.sleep(lDuration);
		this.logger.info("%s: department %s woke up again", lCorrelationId, lDepartment);
	}

}
