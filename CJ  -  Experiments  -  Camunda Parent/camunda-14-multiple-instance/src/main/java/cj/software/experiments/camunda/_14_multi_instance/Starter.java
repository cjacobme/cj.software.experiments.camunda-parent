package cj.software.experiments.camunda._14_multi_instance;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class Starter
		implements
		JavaDelegate
{
	private Logger logger = LogManager.getFormatterLogger();

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		long count = (Long) pExecution.getVariable("Count");
		List<String> entries = new ArrayList<>();
		for (long counter = 0; counter < count; counter++)
		{
			entries.add(String.format("%02d", counter));
		}
		pExecution.setVariable("entries", entries);
		this.logger.info("added %d entries", count);
	}

}
