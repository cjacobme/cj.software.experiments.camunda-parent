package cj.software.experiments.camunda._11_dup_business_key;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableProcessApplication
@EnableConfigurationProperties
public class SimpleScenarioMain
{
	public static void main(String[] pArgs)
	{
		SpringApplication.run(SimpleScenarioMain.class, pArgs);
	}
}
