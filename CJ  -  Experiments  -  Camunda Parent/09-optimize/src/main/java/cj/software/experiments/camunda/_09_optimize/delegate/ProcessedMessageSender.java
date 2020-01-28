package cj.software.experiments.camunda._09_optimize.delegate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessedMessageSender
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
		String lReplyId = (String) pExecution.getVariable("replyTo");
		String lMessageName = "Processed";
		this.logger.info(
				"%s: send %s with reply-id %s ...",
				lCorrelationId,
				lMessageName,
				lReplyId);
		this.runtimeService.createMessageCorrelation(lMessageName)
				.processInstanceId(lReplyId)
				.correlateWithResult();
		this.logger.info("%s: sent", lCorrelationId);
	}

}
