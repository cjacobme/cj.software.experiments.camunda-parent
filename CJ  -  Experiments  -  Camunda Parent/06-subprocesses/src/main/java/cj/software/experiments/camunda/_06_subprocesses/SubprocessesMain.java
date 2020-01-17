package cj.software.experiments.camunda._06_subprocesses;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class SubprocessesMain
{
	public static void main(String[] pArgs)
	{
		SpringApplication.run(SubprocessesMain.class, pArgs);
	}
}
