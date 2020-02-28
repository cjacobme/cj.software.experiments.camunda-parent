package cj.software.experiments.camunda._10_mail.util;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component("idGenerator")
public class IdGenerator
{
	public String generateId()
	{
		String lResult = UUID.randomUUID().toString();
		return lResult;
	}
}
