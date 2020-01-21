package cj.software.experiments.camunda._07_hierarchical;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class HierarchicalSelectionMain
{
	public static void main(String[] pArgs)
	{
		SpringApplication.run(HierarchicalSelectionMain.class, pArgs);
	}
}
