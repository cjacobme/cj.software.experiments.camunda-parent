package cj.software.experiments.camunda._09_optimize.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "cj.software.camunda09")
public class ConfigurationHolder
{
	private long runsAtStartUp;

	public long getRunsAtStartUp()
	{
		return this.runsAtStartUp;
	}

	public void setRunsAtStartUp(long pRunsAtStartUp)
	{
		this.runsAtStartUp = pRunsAtStartUp;
	}
}
