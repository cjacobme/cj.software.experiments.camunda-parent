package cj.software.experiments.camunda._09_optimize;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class DistributedProcessingMain
{
	public static void main(String[] pArgs)
	{
		SpringApplication.run(DistributedProcessingMain.class, pArgs);
	}
}
