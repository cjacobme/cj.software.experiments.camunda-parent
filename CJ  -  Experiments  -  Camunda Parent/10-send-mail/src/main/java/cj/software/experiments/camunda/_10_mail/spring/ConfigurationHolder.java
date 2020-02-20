package cj.software.experiments.camunda._10_mail.spring;

import java.net.URL;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "cj.software.experiments.camunda.10-mail")
@Validated
public class ConfigurationHolder
{
	@NotNull
	private URL addtlUrl;

	public URL getAddtlUrl()
	{
		return this.addtlUrl;
	}

	public void setAddtlUrl(URL pAddtlUrl)
	{
		this.addtlUrl = pAddtlUrl;
	}
}
