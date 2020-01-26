package cj.software.experiments.camunda._08_only_once.delegate;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cj.software.experiments.camunda._08_only_once.spring.ConfigurationHolder;

@Component
public class SimulateLongRunningProcess
		implements
		JavaDelegate
{
	private Logger logger = LoggerFactory.getLogger(SimulateLongRunningProcess.class);

	private Random random = new Random();

	private static int counter = 0;

	@Autowired
	private ConfigurationHolder configurationHolder;

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		String lCorrelationId = pExecution.getId();
		counter++;
		this.logger.info("{}: counter = {}", lCorrelationId, counter);
		pExecution.setVariable("complete", Boolean.FALSE);

		if ((counter % this.configurationHolder.getSimulateExceptionEvery()) == 0)
		{
			throw new Exception("simulated exception");
		}
		else
		{
			long lDuration;
			String lRole;
			if ((counter % this.configurationHolder.getLongRunEvery()) == 0)
			{
				lRole = "long";
				lDuration = this.configurationHolder.getLongRunBias()
						+ this.random.nextInt(this.configurationHolder.getLongRunRandom());
			}
			else
			{
				lRole = "short";
				lDuration = this.configurationHolder.getShortRun();
			}
			this.logger.info(
					"{}: now start {} run for {} seconds",
					lCorrelationId,
					lRole,
					lDuration);
			TimeUnit.SECONDS.sleep(lDuration);
			pExecution.setVariable("complete", Boolean.TRUE);
			this.logger.info("{}: {} run finished", lCorrelationId, lRole);
		}
	}

}
