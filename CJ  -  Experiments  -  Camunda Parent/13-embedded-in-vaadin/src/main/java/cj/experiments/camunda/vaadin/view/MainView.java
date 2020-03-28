package cj.experiments.camunda.vaadin.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView
		extends VerticalLayout
{
	private static final long serialVersionUID = 1L;

	public MainView()
	{
		Text lText = new Text("Hello World");
		super.add(lText);
	}
}
