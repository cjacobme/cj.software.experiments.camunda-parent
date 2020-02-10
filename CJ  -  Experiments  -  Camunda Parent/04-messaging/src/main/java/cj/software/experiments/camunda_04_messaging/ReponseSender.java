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
public class ReponseSender
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
		String lResponse = (String) pExecution.getVariable("response");
		String lResponseTo = (String) pExecution.getVariable("respond-to");
		this.logger.info(
				"%s: sender %s, response %s to %s",
				lCorrelationId,
				lSender,
				lResponse,
				lResponseTo);
		this.logger.info("%s: send response now...", lCorrelationId);
		MessageCorrelationResult lCorrelateWithResult = this.runtimeService
				.createMessageCorrelation("Response")
				.processInstanceId(lResponseTo)
				.setVariable("sender", lSender)
				.setVariable("content", lContent)
				.setVariable("response", lResponse)
				.correlateWithResult();
		this.logger.info(
				"%s: response was sent: %s",
				lCorrelationId,
				lCorrelateWithResult.getResultType());
	}

}
