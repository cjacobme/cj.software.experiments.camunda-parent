package cj.software.experiments.camunda._15_long_running;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config
{
	@Bean
	public ExecutorService fixedThreadPool()
	{
		return Executors.newFixedThreadPool(5);
	}
}
