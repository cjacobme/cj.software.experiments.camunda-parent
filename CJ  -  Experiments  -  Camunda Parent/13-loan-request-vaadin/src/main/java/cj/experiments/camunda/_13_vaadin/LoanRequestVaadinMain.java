package cj.experiments.camunda._13_vaadin;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class LoanRequestVaadinMain
{
	public static void main(String[] pArgs)
	{
		SpringApplication.run(LoanRequestVaadinMain.class, pArgs);
	}
}
