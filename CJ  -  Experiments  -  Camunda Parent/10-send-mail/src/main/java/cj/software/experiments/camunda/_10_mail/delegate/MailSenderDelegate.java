package cj.software.experiments.camunda._10_mail.delegate;

import java.net.URL;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cj.software.experiments.camunda._10_mail.spring.ConfigurationHolder;
import cj.software.experiments.camunda._10_mail.util.MyMailSender;

@Component
public class MailSenderDelegate
		implements
		JavaDelegate
{
	@Autowired
	private MyMailSender myMailSender;

	@Autowired
	private TaskService taskService;

	@Autowired
	private ConfigurationHolder configurationHolder;

	private Logger logger = LogManager.getFormatterLogger();

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		String lFrom = Objects.requireNonNull((String) pExecution.getVariable("From"));
		String lTo = Objects.requireNonNull((String) pExecution.getVariable("To"));
		String lSubject = Objects.requireNonNull((String) pExecution.getVariable("Subject"));
		String lBody = Objects.requireNonNull((String) pExecution.getVariable("Body"));
		lBody = this.extendBody(pExecution, lBody);
		this.myMailSender.sendMail(lFrom, lTo, lSubject, lBody);
	}

	private String extendBody(DelegateExecution pExecution, String pSource)
	{
		StringBuilder lSB = new StringBuilder(pSource).append("\n\n\n");
		lSB.append("please visit the following URL: ");
		lSB.append(this.constructPath(pExecution));
		return lSB.toString();
	}

	private String constructPath(DelegateExecution pExecution)
	{
		URL lBaseUrl = this.configurationHolder.getAddtlUrl();
		String lResult = lBaseUrl
				+ "app/tasklist/default/#/"
				+ "?sorting=%5B%7B%22sortBy%22:%22priority%22,%22sortOrder%22:%22desc%22%7D%5D";
		String lProcessInstanceId = pExecution.getProcessInstanceId();
		Task lTask = this.taskService.createTaskQuery()
				.taskDefinitionKey("UserTask_Watchout")
				.processInstanceId(lProcessInstanceId)
				.singleResult();
		if (lTask != null)
		{
			String lTaskId = lTask.getId();
			lResult += "&task=" + lTaskId;
			this.logger.info(
					"found task %s matching to proc inst id %s",
					lTaskId,
					lProcessInstanceId);
		}
		return lResult;
	}
}
