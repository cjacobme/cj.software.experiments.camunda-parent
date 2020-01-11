package cj.software.experiments.camunda_04_messaging;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private Logger logger = LoggerFactory.getLogger(ReponseSender.class);

	@Autowired
	private RuntimeService runtimeService;

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		String lSender = (String) pExecution.getVariable("sender");
		String lContent = (String) pExecution.getVariable("content");
		String lResponse = (String) pExecution.getVariable("response");
		this.logger.info("sender {}, response {}", lSender, lResponse);
		this.logger.info("send response now...");
		MessageCorrelationResult lCorrelateWithResult = this.runtimeService
				.createMessageCorrelation("Response")
				.setVariable("sender", lSender)
				.setVariable("content", lContent)
				.setVariable("response", lResponse)
				.correlateWithResult();
		this.logger.info("response was sent: {}", lCorrelateWithResult.getResultType());
	}

}
