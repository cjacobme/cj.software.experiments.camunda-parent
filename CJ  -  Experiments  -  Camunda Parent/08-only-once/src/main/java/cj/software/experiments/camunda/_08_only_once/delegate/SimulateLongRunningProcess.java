package cj.software.experiments.camunda._08_only_once.delegate;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SimulateLongRunningProcess
		implements
		JavaDelegate
{
	private Logger logger = LoggerFactory.getLogger(SimulateLongRunningProcess.class);

	private Random random = new Random();

	private static int counter = 0;

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		String lCorrelationId = pExecution.getId();
		long lDuration;
		counter++;
		if ((counter % 3) == 0)
		{
			lDuration = 60 + this.random.nextInt(10);
		}
		else
		{
			lDuration = 3;
		}
		this.logger.info("{}: now start long run for {} seconds", lCorrelationId, lDuration);
		TimeUnit.SECONDS.sleep(lDuration);
		this.logger.info("{}: long run finished", lCorrelationId);
	}

}
