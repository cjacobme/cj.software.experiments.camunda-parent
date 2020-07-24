package cj.software.experiments.camunda._14_multi_instance;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class LoggerDelegate
		implements
		JavaDelegate
{
	private Logger logger = LogManager.getFormatterLogger();

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		String lProcessInstanceId = pExecution.getProcessInstanceId();
		long anzahl = (Long) pExecution.getVariable("Count");
		String entry = (String) pExecution.getVariable("entry");
		this.logger.info("%s: entry=\"%s\", count=%d", lProcessInstanceId, entry, anzahl);
		TimeUnit.SECONDS.sleep(10);
	}

}
