package cj.software.experiments.camunda._05_history;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class Camunda05HistoryMain
{
	public static void main(String[] pArgs)
	{
		SpringApplication.run(Camunda05HistoryMain.class, pArgs);
	}

}
