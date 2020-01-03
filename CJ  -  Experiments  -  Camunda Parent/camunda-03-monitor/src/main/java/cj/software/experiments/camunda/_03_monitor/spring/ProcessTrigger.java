package cj.software.experiments.camunda._03_monitor.spring;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ProcessTrigger
{
	@Autowired
	private RuntimeService runtimeService;

	private Logger logger = LoggerFactory.getLogger(ProcessTrigger.class);

	@Scheduled(cron = "${cj.software.camunda.monitoring.cron-settings}")
	public void triggerNextProcessInstance()
	{
		this.logger.info("woke up, will trigger new process instance now...");
		ProcessInstance lProcessInstance = this.runtimeService.startProcessInstanceByKey(
				"MonitorProcess");
		this.logger.info("process instance {} started", lProcessInstance.getProcessInstanceId());
	}
}
