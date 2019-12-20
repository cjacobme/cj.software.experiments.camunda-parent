package cj.software.experiments.camunda._03_monitor.spring;

import java.net.URL;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties(prefix = "cj.software.camunda.monitoring")
@Validated
public class ConfigurationHolder
{
	private Logger logger = LoggerFactory.getLogger(ConfigurationHolder.class);

	@PostConstruct
	public void listSettings()
	{
		String lFormatString = "%-20s: \"%s\"";
		logEntry(lFormatString, "cronSettings", this.cronSettings);
		logEntry(lFormatString, "download-url", this.downloadUrl);
	}

	private void logEntry(String pFormat, String pName, Object pValue)
	{
		String lFormatted = String.format(pFormat, pName, pValue);
		this.logger.info(lFormatted);
	}

	@NotNull
	@NotEmpty
	private String cronSettings;

	@NotNull
	private URL downloadUrl;

	public String getCronSettings()
	{
		return this.cronSettings;
	}

	public void setCronSettings(String pCronSettings)
	{
		this.cronSettings = pCronSettings;
	}

	public URL getDownloadUrl()
	{
		return this.downloadUrl;
	}

	public void setDownloadUrl(URL pDownloadUrl)
	{
		this.downloadUrl = pDownloadUrl;
	}
}
