package cj.software.experiments.camunda._10_mail;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableProcessApplication
@EnableConfigurationProperties
public class MailSenderMain
{
	public static void main(String[] pArgs)
	{
		SpringApplication.run(MailSenderMain.class, pArgs);
	}
}
