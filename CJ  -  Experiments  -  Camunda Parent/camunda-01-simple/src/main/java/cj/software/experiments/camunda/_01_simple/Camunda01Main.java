package cj.software.experiments.camunda._01_simple;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableProcessApplication
public class Camunda01Main
{

	@Autowired
	private RuntimeService runtimeService;

	public static void main(String[] pArgs)
	{
		SpringApplication.run(Camunda01Main.class, pArgs);
	}

	@EventListener
	private void postDeploy(PostDeployEvent pEvent)
	{
		this.runtimeService.startProcessInstanceByKey("LoadRequestProcess");
	}
}
