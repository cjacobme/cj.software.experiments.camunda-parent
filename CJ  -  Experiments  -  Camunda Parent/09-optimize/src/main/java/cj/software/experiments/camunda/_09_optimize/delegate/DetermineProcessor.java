package cj.software.experiments.camunda._09_optimize.delegate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class DetermineProcessor
		implements
		JavaDelegate
{
	private Logger logger = LogManager.getFormatterLogger();

	private static int counter;

	private static String[] departments =
	{ "A", "B", "C"
	};

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		String lCorrelationId = pExecution.getProcessInstanceId();
		String lDepartment = nextDepartment();
		this.logger.info("%s: use department \"%s\"", lCorrelationId, lDepartment);
		pExecution.setVariable("department", lDepartment);
	}

	private String nextDepartment()
	{
		String lResult = departments[counter];
		counter++;
		if (counter >= departments.length)
		{
			counter = 0;
		}
		return lResult;
	}

}
