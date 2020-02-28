package cj.software.experiments.camunda._10_mail.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContentBuilder
{
	@Autowired
	private TemplateEngine templateEngine;

	public String build(String pBusinessKey, String pMessage, String pLinkTo)
	{
		Context lCtx = new Context();
		lCtx.setVariable("message", pMessage);
		lCtx.setVariable("linkTo", pLinkTo);
		lCtx.setVariable("businessKey", pBusinessKey);
		String lResult = this.templateEngine.process("mailTemplate", lCtx);
		return lResult;
	}
}
