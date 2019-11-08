package cj.software.experiments.camunda._02_timer;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableProcessApplication
public class CamundaTimerMain
{

	@Autowired
	private RuntimeService runtimeService;

	public static void main(String[] pArgs)
	{
		SpringApplication.run(CamundaTimerMain.class, pArgs);
	}

	@EventListener
	private void postDeploy(PostDeployEvent pEvent)
	{
		this.runtimeService.startProcessInstanceByKey("Process_Timer");
	}
}
