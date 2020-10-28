package cj.software.experiments.camunda._14_multi_instance;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class SetupList
		implements
		JavaDelegate
{

	@Override
	public void execute(DelegateExecution execution) throws Exception
	{
		long anzahl = (long) execution.getVariable("anzahl");
		List<Integer> items = new ArrayList<>();
		for (int i = 1; i <= anzahl; i++)
		{
			items.add(i);
		}
		execution.setVariable("items", items);
	}

}
