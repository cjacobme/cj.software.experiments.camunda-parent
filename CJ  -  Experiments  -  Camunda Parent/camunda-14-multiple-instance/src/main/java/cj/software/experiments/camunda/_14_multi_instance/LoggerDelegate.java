package cj.software.experiments.camunda._14_multi_instance;

import java.util.Random;
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

	private Random random = new Random();

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		String lProcessInstanceId = pExecution.getProcessInstanceId();
		long anzahl = (Long) pExecution.getVariable("Count");
		String entry = (String) pExecution.getVariable("entry");
		long sleep = this.random.nextLong() % 10 + 10;
		this.logger.info(
				"%s: entry=\"%s\", count=%2d sleep %d",
				lProcessInstanceId,
				entry,
				anzahl,
				sleep);
		TimeUnit.SECONDS.sleep(sleep);
		this.logger.info("%s: entry=\"%s\", count=%2d awake", lProcessInstanceId, entry, anzahl);
	}

}
