package cj.software.experiments.camunda._15_long_running.util;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;

public class BackgroundExecutor
		implements
		Runnable
{
	private RuntimeService runtimeService;

	private String processInstanceId;

	private long sleepSeconds;

	private Logger logger = LogManager.getFormatterLogger();

	public BackgroundExecutor(
			RuntimeService runtimeService,
			String processInstanceId,
			long sleepSeconds)
	{
		this.runtimeService = runtimeService;
		this.processInstanceId = processInstanceId;
		this.sleepSeconds = sleepSeconds;
	}

	@Override
	public void run()
	{
		this.logger.info("now sleep for %d seconds...", this.sleepSeconds);
		try
		{
			TimeUnit.SECONDS.sleep(this.sleepSeconds);
			this.logger.info("woke up again");
			MessageCorrelationResult correlateWithResult = this.runtimeService
					.createMessageCorrelation("Finished")
					.processInstanceId(this.processInstanceId)
					.correlateWithResult();
			this.logger.info(
					"correlateWithResult = %s %s",
					correlateWithResult.toString(),
					correlateWithResult.getResultType());
		}
		catch (InterruptedException exception)
		{
			throw new RuntimeException(exception);
		}
	}
}
