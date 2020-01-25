package cj.software.experiments.camunda._08_only_once;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class OnlyOnceMain
{
	public static void main(String[] pArgs)
	{
		SpringApplication.run(OnlyOnceMain.class, pArgs);
	}
}
