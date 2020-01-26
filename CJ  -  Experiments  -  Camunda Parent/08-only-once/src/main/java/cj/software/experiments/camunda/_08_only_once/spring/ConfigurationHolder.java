package cj.software.experiments.camunda._08_only_once.spring;

import javax.annotation.PostConstruct;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component("configurationHolder")
@ConfigurationProperties(prefix = "cj.software.only-once")
@Validated
public class ConfigurationHolder
{
	private Logger logger = LoggerFactory.getLogger(ConfigurationHolder.class);

	@NotEmpty
	private String cronSettings;

	@Min(2)
	private int simulateExceptionEvery;

	@Min(2)
	private int longRunEvery;

	@Min(0)
	private int longRunBias;

	@Min(0)
	private int longRunRandom;

	@Min(0)
	private int shortRun;

	public String getCronSettings()
	{
		return this.cronSettings;
	}

	public void setCronSettings(String pCronSettings)
	{
		this.cronSettings = pCronSettings;
	}

	@PostConstruct
	public void listSettings()
	{
		String lFormatString = "%-25s: \"%s\"";
		String lIntFormat = "%-25s: %10d";
		logEntry(lFormatString, "cronSettings", this.cronSettings);
		logIntEntry(lIntFormat, "simulateExceptionEvery", this.simulateExceptionEvery);
		logIntEntry(lIntFormat, "longRunEvery", this.longRunEvery);
		logIntEntry(lIntFormat, "longRunBias", this.longRunBias);
		logIntEntry(lIntFormat, "longRunRandom", this.longRunRandom);
		logIntEntry(lIntFormat, "shortRun", this.shortRun);
	}

	private void logIntEntry(String pFormat, String pName, int pValue)
	{
		String lEntry = String.format(pFormat, pName, pValue);
		this.logger.info(lEntry);
	}

	private void logEntry(String pFormat, String pName, Object pValue)
	{
		String lFormatted = String.format(pFormat, pName, pValue);
		this.logger.info(lFormatted);
	}

	public int getSimulateExceptionEvery()
	{
		return this.simulateExceptionEvery;
	}

	public void setSimulateExceptionEvery(int pSimulateExceptionEvery)
	{
		this.simulateExceptionEvery = pSimulateExceptionEvery;
	}

	public int getLongRunEvery()
	{
		return this.longRunEvery;
	}

	public void setLongRunEvery(int pLongRunEvery)
	{
		this.longRunEvery = pLongRunEvery;
	}

	public int getLongRunBias()
	{
		return this.longRunBias;
	}

	public void setLongRunBias(int pLongRunBias)
	{
		this.longRunBias = pLongRunBias;
	}

	public int getLongRunRandom()
	{
		return this.longRunRandom;
	}

	public void setLongRunRandom(int pLongRunRandom)
	{
		this.longRunRandom = pLongRunRandom;
	}

	public int getShortRun()
	{
		return this.shortRun;
	}

	public void setShortRun(int pShortRun)
	{
		this.shortRun = pShortRun;
	}
}
