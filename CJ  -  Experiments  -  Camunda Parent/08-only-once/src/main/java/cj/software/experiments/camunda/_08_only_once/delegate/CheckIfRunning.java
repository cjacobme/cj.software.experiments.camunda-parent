package cj.software.experiments.camunda._08_only_once.delegate;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckIfRunning
		implements
		JavaDelegate
{
	private Logger logger = LoggerFactory.getLogger(CheckIfRunning.class);

	@Autowired
	private RuntimeService runtimeService;

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		String lCorrelationId = pExecution.getId();
		long lCount = this.runtimeService.createExecutionQuery()
				.processDefinitionKey("Process_ImportSimulation")
				.count();
		this.logger.info("{}: found {} running process instances", lCorrelationId, lCount);
		boolean lAlreadyRunning = (lCount > 0l);
		this.logger.info("{}: already running = {}", lCorrelationId, lAlreadyRunning);
		pExecution.setVariable("alreadyRunning", lAlreadyRunning);
	}

}
