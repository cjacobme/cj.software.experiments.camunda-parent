package cj.software.experiments.camunda._02_timer;

import java.util.Random;
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

	private Random random = new Random();

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		double lNextRand = this.random.nextDouble();
		long lSleepTime = (lNextRand > 0.75 ? 13 : 4);
		this.logger.info(
				String.format(
						"Timer was triggered, %5.3f sleeping now for %d seconds...",
						lNextRand,
						lSleepTime));
		TimeUnit.SECONDS.sleep(lSleepTime);
		this.logger.info("woke up again");
	}

}
