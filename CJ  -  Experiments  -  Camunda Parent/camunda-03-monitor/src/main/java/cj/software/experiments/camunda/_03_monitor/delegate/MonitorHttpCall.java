package cj.software.experiments.camunda._03_monitor.delegate;

import java.net.URL;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cj.software.experiments.camunda._03_monitor.util.HtmlDownloader;

@Component
public class MonitorHttpCall
		implements
		JavaDelegate
{
	@Autowired
	private HtmlDownloader downloader;

	private Logger logger = LoggerFactory.getLogger(MonitorHttpCall.class);

	@Override
	public void execute(DelegateExecution pExecution) throws Exception
	{
		URL lURL = (URL) pExecution.getVariable("URL");
		this.logger.info("call {}", lURL);
		boolean lResult = this.downloader.download(lURL);
		this.logger.info("success is {}", lResult);
		pExecution.setVariable("httpCallOk", lResult);
	}

}
