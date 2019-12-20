package cj.software.experiments.camunda._03_monitor.delegate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerDelegate
		implements
		JavaDelegate
{
	private Logger logger = LoggerFactory.getLogger(LoggerDelegate.class);

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		this.logger.info(
				"process-definition-id={}\n, execution-id={}",
				pExecution.getProcessDefinitionId(),
				pExecution.getId());
		Set<String> lVariableNamesSet = pExecution.getVariableNames();
		List<String> lVariableNames = new ArrayList<>(lVariableNamesSet);
		Collections.sort(lVariableNames);
		for (String bName : lVariableNames)
		{
			Object lVariable = pExecution.getVariable(bName);
			this.logger.info("     {} = {}", bName, lVariable);
		}
	}
}
