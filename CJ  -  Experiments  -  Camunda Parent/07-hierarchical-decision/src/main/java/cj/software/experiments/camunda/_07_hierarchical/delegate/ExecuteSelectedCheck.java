package cj.software.experiments.camunda._07_hierarchical.delegate;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExecuteSelectedCheck
		implements
		JavaDelegate
{
	private Logger logger = LoggerFactory.getLogger(ExecuteSelectedCheck.class);

	@Autowired
	private RuntimeService runtimeService;

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		String lCorrelationId = pExecution.getProcessInstanceId();
		String lSelected = (String) pExecution.getVariable("Selected");
		this.logger.info("{}: start process \"{}\"...", lCorrelationId, lSelected);
		this.runtimeService.startProcessInstanceByKey(lSelected);
	}

}
