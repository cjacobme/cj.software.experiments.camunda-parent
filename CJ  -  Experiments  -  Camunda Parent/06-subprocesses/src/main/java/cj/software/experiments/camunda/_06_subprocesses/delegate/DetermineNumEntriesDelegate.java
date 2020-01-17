package cj.software.experiments.camunda._06_subprocesses.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cj.software.experiments.camunda._06_subprocesses.entity.CheckedResource;
import cj.software.experiments.camunda._06_subprocesses.spring.CheckedResourceConfig;
import cj.software.experiments.camunda._06_subprocesses.util.NumEntriesReader;

@Component
public class DetermineNumEntriesDelegate
		implements
		JavaDelegate
{
	@Autowired
	private CheckedResourceConfig checkedResourceConfig;

	@Autowired
	private NumEntriesReader numEntriesReader;

	private Logger logger = LoggerFactory.getLogger(DetermineNumEntriesDelegate.class);

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		String lCorrelationId = pExecution.getProcessInstanceId();
		String lEnvironment = (String) pExecution.getVariableLocal("environment");
		this.logger.info("{}: environment {}", lCorrelationId, lEnvironment);

		CheckedResource lCheckedResource = this.checkedResourceConfig.getCheckedResource(
				lEnvironment);
		if (lCheckedResource == null)
		{
			throw new IllegalArgumentException("environement \"" + lEnvironment + "\" unknown");
		}

		long lNumEntries = this.numEntriesReader.readNumEntries(lCorrelationId, lCheckedResource);
		long lLimit = lCheckedResource.getLimit();
		this.logger.info("{}: found {} entries, limit is {}", lCorrelationId, lNumEntries, lLimit);
		pExecution.setVariableLocal("numEntries", lNumEntries);
		pExecution.setVariableLocal("limit", lLimit);
	}

}
