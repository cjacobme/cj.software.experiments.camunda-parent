package cj.software.experiments.camunda._06_subprocesses.spring;

import java.util.HashMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import cj.software.experiments.camunda._06_subprocesses.entity.CheckedResource;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "to-be-checked")
public class CheckedResourceConfig
{
	private HashMap<String, CheckedResource> entries;

	public void setEntries(HashMap<String, CheckedResource> pEntries)
	{
		this.entries = pEntries;
	}

	public CheckedResource getCheckedResource(String pKey)
	{
		return this.entries.get(pKey);
	}
}
