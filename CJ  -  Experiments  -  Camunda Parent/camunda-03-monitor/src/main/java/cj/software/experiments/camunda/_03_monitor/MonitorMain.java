package cj.software.experiments.camunda._03_monitor;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class MonitorMain
{

	public static void main(String[] pArgs)
	{
		SpringApplication.run(MonitorMain.class, pArgs);
	}

}
