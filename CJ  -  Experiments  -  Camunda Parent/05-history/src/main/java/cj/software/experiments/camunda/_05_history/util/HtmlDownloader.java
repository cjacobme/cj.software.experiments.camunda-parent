package cj.software.experiments.camunda._05_history.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HtmlDownloader
{
	private Logger logger = LoggerFactory.getLogger(HtmlDownloader.class);

	public String download(URL pURL) throws URISyntaxException
	{
		RestTemplate lRestTemplate = new RestTemplate();
		URI lURI = pURL.toURI();
		ResponseEntity<String> lResponse = lRestTemplate.getForEntity(lURI, String.class);
		int lStatusCodeValue = lResponse.getStatusCodeValue();
		int lStatusCodeFamily = lStatusCodeValue / 100;
		String lResult;
		if (lStatusCodeFamily != 2)
		{
			String lReasonPhrase = lResponse.getStatusCode().getReasonPhrase();
			this.logger.error(
					"download for {} failed: {} {}",
					pURL,
					lStatusCodeValue,
					lReasonPhrase);
			throw new IllegalArgumentException(
					"download failed for " + pURL + " due to " + lReasonPhrase);
		}
		else
		{
			lResult = lResponse.getBody();
		}
		return lResult;
	}
}
