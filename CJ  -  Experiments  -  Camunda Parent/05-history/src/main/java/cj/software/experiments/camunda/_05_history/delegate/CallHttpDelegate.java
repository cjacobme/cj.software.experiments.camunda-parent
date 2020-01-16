package cj.software.experiments.camunda._05_history.delegate;

import java.net.URL;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cj.software.experiments.camunda._05_history.util.HtmlDownloader;

@Component
public class CallHttpDelegate
		implements
		JavaDelegate
{
	private Logger logger = LoggerFactory.getLogger(CallHttpDelegate.class);

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		String lUrlString = (String) pExecution.getVariable("URL");
		URL lURL = new URL(lUrlString);
		this.logger.info("call {}", lURL);
		HtmlDownloader lDownloader = new HtmlDownloader();
		String lDownloaded = lDownloader.download(lURL);
		this.logger.info("success has length {}", lDownloaded.length());
	}

}
