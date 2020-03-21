package cj.software.experimtens.camunda._12_multiple_receives;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class MultiReceivesMain
{
	public static void main(String[] pArgs)
	{
		SpringApplication.run(MultiReceivesMain.class, pArgs);
	}
}
