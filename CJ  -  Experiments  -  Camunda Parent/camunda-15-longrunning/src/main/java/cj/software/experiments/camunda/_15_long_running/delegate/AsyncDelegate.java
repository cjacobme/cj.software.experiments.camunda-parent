package cj.software.experiments.camunda._15_long_running.delegate;

import java.util.concurrent.ExecutorService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cj.software.experiments.camunda._15_long_running.util.BackgroundExecutor;

@Component
public class AsyncDelegate
		implements
		JavaDelegate
{
	private Logger logger = LogManager.getFormatterLogger();

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private ExecutorService executorService;

	@Override
	public void execute(DelegateExecution execution) throws Exception
	{
		String oldCorrelationId = MDC.get(VariableNames.CORRELATION_ID);
		try
		{
			String procInstId = execution.getProcessInstanceId();
			MDC.put(VariableNames.CORRELATION_ID, procInstId);
			Long sleeptime = (Long) execution.getVariable(VariableNames.SLEEPTIME);
			this.logger.info("async processing invoked");
			this.executorService.execute(
					new BackgroundExecutor(this.runtimeService, procInstId, sleeptime));
			this.logger.info("async processing launched");
		}
		finally
		{
			MDC.put(VariableNames.CORRELATION_ID, oldCorrelationId);
		}
	}
}
