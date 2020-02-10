package cj.software.experiments.camunda_04_messaging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * sends the request to the service provider
 */
@Component
public class RequestSender
		implements
		JavaDelegate
{
	private Logger logger = LogManager.getFormatterLogger();

	@Autowired
	private RuntimeService runtimeService;

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		String lCorrelationId = pExecution.getId();
		String lSender = (String) pExecution.getVariable("sender");
		String lContent = (String) pExecution.getVariable("content");
		this.logger.info("%s: sender %s, content %s", lCorrelationId, lSender, lContent);
		this.logger.info("%s: send message now...", lCorrelationId);
		MessageCorrelationResult lCorrelateWithResult = this.runtimeService
				.createMessageCorrelation("Service Requested")
				.setVariable("sender", lSender)
				.setVariable("content", lContent)
				.setVariable("respond-to", lCorrelationId)
				.correlateWithResult();
		this.logger.info(
				"%s: message was sent: %s %s",
				lCorrelationId,
				lCorrelateWithResult.getResultType(),
				lCorrelateWithResult.getProcessInstance());
	}

}
