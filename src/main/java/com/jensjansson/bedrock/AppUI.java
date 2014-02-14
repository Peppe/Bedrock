package com.jensjansson.bedrock;

import javax.servlet.annotation.WebServlet;

import com.jensjansson.bedrock.layout.MainLayout;
import com.jensjansson.bedrock.view.AboutView;
import com.jensjansson.bedrock.view.HomeView;
import com.jensjansson.bedrock.view.DogeView;
import com.jensjansson.bedrock.view.ErrorView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Theme("bedrock")
@SuppressWarnings("serial")
public class AppUI extends UI {

	private MainLayout layout;

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = AppUI.class, widgetset = "com.jensjansson.bedrock.AppWidgetSet")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		layout = new MainLayout();
		setContent(layout);

		Navigator navigator = new Navigator(this, (ViewDisplay) layout);
		addView("Home", "", HomeView.class);
		addView("Doge", "doge", DogeView.class);
		addView("About", "about", AboutView.class);
		navigator.setErrorView(ErrorView.class);
		setNavigator(navigator);
	}
	
	private void addView(String name, String viewName, Class<? extends View> view){
		getNavigator().addView(viewName, view);
		layout.addView(name, viewName);
	}
}
