package cj.software.experiments.camunda._14_multi_instance;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Spawn
		implements
		JavaDelegate
{
	@Autowired
	private RuntimeService runtimeService;

	private Logger logger = LogManager.getFormatterLogger();

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		long anzahl = (Long) pExecution.getVariable("Count");
		for (long counter = 0; counter < anzahl; counter++)
		{
			Map<String, Object> variables = new HashMap<>();
			variables.put("Count", counter);
			String entry = String.format("%02d", anzahl);
			variables.put("entry", entry);
			ProcessInstance lProcessInstance = Spawn.this.runtimeService.startProcessInstanceByKey(
					"Process_Sub",
					variables);
			String procInstId = lProcessInstance.getProcessInstanceId();
			this.logger.info("spawned %d %s", counter, procInstId);
		}
	}
}
