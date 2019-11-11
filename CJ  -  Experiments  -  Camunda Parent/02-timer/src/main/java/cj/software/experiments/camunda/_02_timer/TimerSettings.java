package cj.software.experiments.camunda._02_timer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimerSettings
{
	@Value("#{systemProperties['cronsettings']}")
	private String cronSettings;

	public String getCronSettings()
	{
		return this.cronSettings;
	}
}
