package com.jensjansson.bedrock;

import javax.servlet.annotation.WebServlet;

import com.jensjansson.bedrock.view.AboutView;
import com.jensjansson.bedrock.view.AnotherView;
import com.jensjansson.bedrock.view.ErrorView;
import com.jensjansson.bedrock.view.HomeView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

@Theme("bedrock")
@SuppressWarnings("serial")
public class AppUI extends UI implements ViewDisplay {

	private VerticalLayout layout;
	private HorizontalLayout buttonContainer;
	private Component currentView;

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = AppUI.class, widgetset = "com.jensjansson.bedrock.AppWidgetSet")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		layout = createMainLayout();
		setContent(layout);

		Navigator navigator = new Navigator(this, (ViewDisplay) this);
		addView("Home", "", HomeView.class);
		addView("Another", "another", AnotherView.class);
		addView("About", "about", AboutView.class);
		navigator.setErrorView(ErrorView.class);
		setNavigator(navigator);
	}

	private VerticalLayout createMainLayout() {
		VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		HorizontalLayout topbar = new HorizontalLayout();
		topbar.setWidth("100%");
		buttonContainer = new HorizontalLayout();
		buttonContainer.setWidth(null);
		topbar.addComponent(buttonContainer);
		topbar.setComponentAlignment(buttonContainer, Alignment.MIDDLE_CENTER);
		layout.addComponent(topbar);
		return layout;
	}

	public void addView(final String name, final String viewName,
			Class<? extends View> view) {
		getNavigator().addView(viewName, view);
		Button menubutton = new Button(name, new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(viewName);
			}
		});
		buttonContainer.addComponent(menubutton);
	}

	@Override
	public void showView(View view) {
		Component viewComponent = (Component) view;
		if (currentView == null) {
			layout.addComponent(viewComponent);
		} else {
			layout.replaceComponent(currentView, viewComponent);
		}
		currentView = viewComponent;
	}

}
