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
public class MessageSender
		implements
		JavaDelegate
{
	private Logger logger = LoggerFactory.getLogger(MessageSender.class);

	@Autowired
	private RuntimeService runtimeService;

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		this.logger.info("send message now...");
		MessageCorrelationResult lCorrelateWithResult = this.runtimeService
				.createMessageCorrelation("Service Requested")
				.setVariable("request", "help me")
				.correlateWithResult();
		this.logger.info(
				"message was sent: {} {}",
				lCorrelateWithResult.getResultType(),
				lCorrelateWithResult.getProcessInstance());
	}

}
