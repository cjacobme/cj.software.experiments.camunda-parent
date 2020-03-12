package cj.software.experiments.camunda._11_dup_business_key.delegate;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.history.HistoricProcessInstanceQuery;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckRunsDelegate
		implements
		JavaDelegate
{
	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private HistoryService historyService;

	private Logger logger = LogManager.getFormatterLogger();

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		ProcessInstanceQuery lQuery = this.runtimeService.createProcessInstanceQuery();
		String lBusinessKey = pExecution.getBusinessKey();
		this.dumpRuntime(lBusinessKey);
		this.dumpHistory(lBusinessKey);
		long lCount = lQuery.processInstanceBusinessKey(lBusinessKey).count();
		if (lCount > 0l)
		{
			throw new IllegalArgumentException(
					String.format(
							"There are %d process instances already running with business key \"%s\"",
							lCount,
							lBusinessKey));
		}
	}

	private void dumpRuntime(String pBusinessKey)
	{
		ProcessInstanceQuery lQuery = this.runtimeService.createProcessInstanceQuery();
		List<ProcessInstance> lProcessInstances = lQuery.processInstanceBusinessKey(pBusinessKey)
				.list();
		this.logger.info("There are %d running process instances", lProcessInstances.size());
		for (ProcessInstance bInst : lProcessInstances)
		{
			this.logger.info("\tbusi-key %s, id %s", bInst.getBusinessKey(), bInst.getId());
		}
	}

	private void dumpHistory(String pBusinessKey)
	{
		HistoricProcessInstanceQuery lQuery = this.historyService
				.createHistoricProcessInstanceQuery();
		List<HistoricProcessInstance> lProcessInstances = lQuery.processInstanceBusinessKey(
				pBusinessKey).list();
		this.logger.info("There are %d historic process instances", lProcessInstances.size());
		for (HistoricProcessInstance bInst : lProcessInstances)
		{
			this.logger.info("\tbusi-key %s, id %s", bInst.getBusinessKey(), bInst.getId());
		}
	}
}
