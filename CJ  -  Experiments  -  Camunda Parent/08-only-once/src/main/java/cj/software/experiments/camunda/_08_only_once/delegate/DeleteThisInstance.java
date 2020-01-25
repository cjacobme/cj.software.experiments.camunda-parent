package cj.software.experiments.camunda._08_only_once.delegate;

import java.util.Arrays;
import java.util.List;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteThisInstance
		implements
		JavaDelegate
{
	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private HistoryService historyService;

	private Logger logger = LoggerFactory.getLogger(DeleteThisInstance.class);

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		String lCorrelationId = pExecution.getId();
		String lProcessInstanceId = pExecution.getProcessInstanceId();
		this.logger.info(
				"{}: try to delete process instance with id {}...",
				lCorrelationId,
				lProcessInstanceId);
		this.runtimeService.deleteProcessInstance(lProcessInstanceId, "already running");
		this.logger.info(
				"{}: successfully deleted process instance {} from runtime service",
				lCorrelationId,
				lProcessInstanceId);
		List<String> lAsList = Arrays.asList(lProcessInstanceId);
		this.historyService.deleteHistoricProcessInstancesAsync(lAsList, "already running");
		this.logger.info(
				"{}: asyonchronously will delete process instance {} from history service",
				lCorrelationId,
				lProcessInstanceId);
	}

}
