package cj.software.experiments.camunda._14_multi_instance;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class LogItem
		implements
		JavaDelegate
{
	private Logger logger = LogManager.getFormatterLogger();

	@Override
	public void execute(DelegateExecution execution) throws Exception
	{
		int item = (int) execution.getVariable("item");
		this.logger.info("invoked %d", item);
	}

}
