package cj.software.experiments.camunda._03_monitor.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonitorHttpCall
		implements
		JavaDelegate
{
	private Logger logger = LoggerFactory.getLogger(MonitorHttpCall.class);

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		Object lURL = pExecution.getVariable("URL");
		this.logger.info("call {}", lURL);
		boolean lResult = "http://www.nasa.gov".equals(lURL);
		pExecution.setVariable("nasaOk", lResult);
	}

}
