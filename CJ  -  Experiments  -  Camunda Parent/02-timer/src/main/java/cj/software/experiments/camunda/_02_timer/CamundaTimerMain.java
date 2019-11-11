package cj.software.experiments.camunda._02_timer;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class CamundaTimerMain
{

	public static void main(String[] pArgs)
	{
		SpringApplication.run(CamundaTimerMain.class, pArgs);
	}
}
