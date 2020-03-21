package cj.software.experimtens.camunda._12_multiple_receives.delegate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendDelegate
		implements
		JavaDelegate
{
	private Logger logger = LogManager.getFormatterLogger();

	@Autowired
	private RuntimeService runtimeService;

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		String lProcessKey = (String) pExecution.getVariable("processKey");
		String lMessage = (String) pExecution.getVariable("message");
		this.logger.info("%s: shall send %s...", lProcessKey, lMessage);
		MessageCorrelationResult lCorrelateResult = this.runtimeService.createMessageCorrelation(
				"LAP_Response")
				.processInstanceBusinessKey(lProcessKey)
				.setVariable("message", lMessage)
				.correlateWithResult();
		this.logger.info("was delivered to %s", lCorrelateResult.getResultType());
	}

}
