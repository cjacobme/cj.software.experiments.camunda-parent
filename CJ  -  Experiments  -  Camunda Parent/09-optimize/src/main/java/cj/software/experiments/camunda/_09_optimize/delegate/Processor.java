package cj.software.experiments.camunda._09_optimize.delegate;

import java.util.Objects;
import java.util.Random;

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

	private Random randomizer = new Random();

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		String lCorrelationId = pExecution.getProcessInstanceId();
		String lDepartment = Objects.requireNonNull((String) pExecution.getVariable("department"));
		String lBiasStr = Objects.requireNonNull((String) pExecution.getVariable("bias"));
		int lBias = Integer.parseInt(lBiasStr);
		String lRandomStr = Objects.requireNonNull((String) pExecution.getVariable("random"));
		int lRandom = Integer.parseInt(lRandomStr);
		this.logger.info(
				"%s: department=%s, bias=%d, random=%d",
				lCorrelationId,
				lDepartment,
				lBias,
				lRandom);

		int lNextRandom = this.randomizer.nextInt(lRandom);
		int lCosts = lBias + lNextRandom;
		this.logger.info("%s: department %s had costs=%d", lCorrelationId, lDepartment, lCosts);
		pExecution.setVariable("costs", lCosts);
	}

}
