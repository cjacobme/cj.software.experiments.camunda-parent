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
		String lBusinessKey = pExecution.getBusinessKey();
		String lFrom = Objects.requireNonNull((String) pExecution.getVariable("From"));
		String lTo = Objects.requireNonNull((String) pExecution.getVariable("To"));
		String lSubject = Objects.requireNonNull((String) pExecution.getVariable("Subject"));
		String lBody = Objects.requireNonNull((String) pExecution.getVariable("Body"));
		String lLinkTo = this.constructPath(pExecution);
		this.myMailSender.sendMail(lBusinessKey, lFrom, lTo, lSubject, lBody, lLinkTo);
	}

	private String constructPath(DelegateExecution pExecution)
	{
		URL lBaseUrl = this.configurationHolder.getAddtlUrl();
		String lBusinessKey = pExecution.getBusinessKey();
		String lResult = lBaseUrl
				+ "app/tasklist/default/#/"
				+ "?sorting=%5B%7B%22sortBy%22:%22priority%22,%22sortOrder%22:%22desc%22%7D%5D";
		Task lTask = this.taskService.createTaskQuery()
				.taskDefinitionKey("UserTask_Watchout")
				.processInstanceBusinessKey(lBusinessKey)
				.singleResult();
		if (lTask != null)
		{
			String lTaskId = lTask.getId();
			lResult += "&task=" + lTaskId;
			this.logger.info("found task %s matching to business id %s", lTaskId, lBusinessKey);
		}
		return lResult;
	}
}
