package cj.software.experiments.camunda._07_hierarchical.delegate;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SimulateCheck
		implements
		JavaDelegate
{
	private Logger logger = LoggerFactory.getLogger(SimulateCheck.class);

	private Random random = new Random();

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		String lCorrelationId = pExecution.getProcessInstanceId();
		String lSystem = (String) pExecution.getVariable("System");
		String lScope = (String) pExecution.getVariable("Scope");
		String lApplication = (String) pExecution.getVariable("Application");
		String lCheck = (String) pExecution.getVariable("Check");

		this.logger.info(
				"{}: called for system {} scope {} application {} check {}",
				lCorrelationId,
				lSystem,
				lScope,
				lApplication,
				lCheck);

		int lSleepSeconds = this.random.nextInt(7);
		TimeUnit.SECONDS.sleep(lSleepSeconds);
	}

}
