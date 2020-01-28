package cj.software.experiments.camunda._09_optimize.delegate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartMessageSender
		implements
		JavaDelegate
{

	@Autowired
	private RuntimeService runtimeService;

	private Logger logger = LogManager.getFormatterLogger();

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		String lCorrelationId = pExecution.getProcessInstanceId();
		String lMessageName = "Process";
		String lDepartment = (String) pExecution.getVariable("department");
		this.logger.info(
				"%s: now send start msg to \"%s\": Department %s...",
				lCorrelationId,
				lMessageName,
				lDepartment);
		this.runtimeService.createMessageCorrelation(lMessageName)
				.setVariable("department", lDepartment)
				.setVariable("replyTo", lCorrelationId)
				.processInstanceId(lCorrelationId)
				.correlate();
	}

}
