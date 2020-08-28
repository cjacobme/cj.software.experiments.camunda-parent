package cj.software.experiments.camunda._15_long_running.delegate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Component
public class SyncDelegate
		implements
		JavaDelegate
{
	private Logger logger = LogManager.getFormatterLogger();

	private static AtomicBoolean running = new AtomicBoolean(false);

	@Override
	public void execute(DelegateExecution execution) throws Exception
	{
		String oldCorrelationId = MDC.get(VariableNames.CORRELATION_ID);
		try
		{
			String procInstId = execution.getProcessInstanceId();
			MDC.put(VariableNames.CORRELATION_ID, procInstId);

			this.logger.info("sync invocation");
			if (running.compareAndSet(false, true))
			{
				try
				{
					Long sleeptime = (Long) execution.getVariable(VariableNames.SLEEPTIME);
					this.logger.info("start to sleep %d minutes...", sleeptime);
					TimeUnit.MINUTES.sleep(sleeptime);
					this.logger.info("woke up again");
				}
				finally
				{
					running.set(false);
				}
			}
			else
			{
				throw new RuntimeException("already running");
			}
		}
		finally
		{
			MDC.put(VariableNames.CORRELATION_ID, oldCorrelationId);
		}
	}

}
