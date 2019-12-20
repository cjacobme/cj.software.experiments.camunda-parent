package cj.software.experiments.camunda._03_monitor.util;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HtmlDownloader
{
	private Logger logger = LoggerFactory.getLogger(HtmlDownloader.class);

	public boolean download(String pURI) throws URISyntaxException
	{
		RestTemplate lRestTemplate = new RestTemplate();
		URI lURI = new URI(pURI);
		ResponseEntity<String> lResponse = lRestTemplate.getForEntity(lURI, String.class);
		int lStatusCodeValue = lResponse.getStatusCodeValue();
		int lStatusCodeFamily = lStatusCodeValue / 100;
		boolean lResult;
		if (lStatusCodeFamily != 2)
		{
			String lReasonPhrase = lResponse.getStatusCode().getReasonPhrase();
			this.logger.error(
					"download for {} failed: {} {}",
					pURI,
					lStatusCodeValue,
					lReasonPhrase);
			lResult = false;
		}
		else
		{
			lResult = true;
		}
		return lResult;
	}
}
