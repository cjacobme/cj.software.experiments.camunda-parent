package cj.software.experiments.camunda_04_messaging;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class Camunda04MessagingMain
{
	public static void main(String[] pArgs)
	{
		SpringApplication.run(Camunda04MessagingMain.class, pArgs);
	}
}
