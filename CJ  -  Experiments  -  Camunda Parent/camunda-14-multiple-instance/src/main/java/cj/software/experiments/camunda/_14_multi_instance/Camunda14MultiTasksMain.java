package cj.software.experiments.camunda._14_multi_instance;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class Camunda14MultiTasksMain
{
	public static void main(String[] args)
	{
		SpringApplication.run(Camunda14MultiTasksMain.class, args);
	}
}
