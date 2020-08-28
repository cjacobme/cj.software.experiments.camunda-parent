package cj.software.experiments.camunda._15_long_running;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableProcessApplication
@EnableAsync
public class Camunda15LongRunningMain
{
	public static void main(String[] args)
	{
		SpringApplication.run(Camunda15LongRunningMain.class, args);
	}
}
