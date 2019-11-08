package cj.software.experiments.camunda._02_timer;

import java.util.concurrent.TimeUnit;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimerLogger
		implements
		JavaDelegate
{
	private Logger logger = LoggerFactory.getLogger(TimerLogger.class);

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		this.logger.info("Timer was triggered, sleeping now...");
		TimeUnit.SECONDS.sleep(13);
		this.logger.info("woke up again");
	}

}
