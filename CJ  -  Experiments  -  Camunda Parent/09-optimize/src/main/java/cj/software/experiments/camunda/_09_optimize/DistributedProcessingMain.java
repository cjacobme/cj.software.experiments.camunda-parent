package cj.software.experiments.camunda._09_optimize;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.event.EventListener;

import cj.software.experiments.camunda._09_optimize.spring.ConfigurationHolder;

@SpringBootApplication
@EnableProcessApplication
@EnableConfigurationProperties
public class DistributedProcessingMain
{
	private Logger logger = LogManager.getFormatterLogger();

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private ConfigurationHolder configurationHolder;

	public static void main(String[] pArgs)
	{
		SpringApplication.run(DistributedProcessingMain.class, pArgs);
	}

	@EventListener
	private void postDeploy(PostDeployEvent pEvent)
	{
		long lRunsAtStart = this.configurationHolder.getRunsAtStartUp();
		this.logger.info("create %d instances at startup...", lRunsAtStart);
		for (int bCounter = 0; bCounter < lRunsAtStart; bCounter++)
		{
			this.runtimeService.startProcessInstanceByKey("Process_Order");
		}
	}

}
