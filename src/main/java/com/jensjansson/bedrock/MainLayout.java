package com.jensjansson.bedrock;

import com.jensjansson.bedrock.view.AboutView;
import com.jensjansson.bedrock.view.AnotherView;
import com.jensjansson.bedrock.view.ErrorView;
import com.jensjansson.bedrock.view.HomeView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class MainLayout extends VerticalLayout implements ViewDisplay {

	private HorizontalLayout buttonContainer;
	private Component currentView;
	
	public MainLayout(AppUI ui) {
		setMargin(true);
		HorizontalLayout topbar = new HorizontalLayout();
		topbar.setWidth("100%");
		buttonContainer = new HorizontalLayout();
		buttonContainer.setWidth(null);
		topbar.addComponent(buttonContainer);
		topbar.setComponentAlignment(buttonContainer, Alignment.MIDDLE_CENTER);
		addComponent(topbar);
		
		Navigator navigator = new Navigator(ui, (ViewDisplay) this);
		addView(navigator, "Home", "", HomeView.class);
		addView(navigator, "Another", "another", AnotherView.class);
		addView(navigator, "About", "about", AboutView.class);
		navigator.setErrorView(ErrorView.class);
		ui.setNavigator(navigator);
	}
	
	@Override
	public void showView(View view) {
		Component viewComponent = (Component) view;
		if (currentView == null) {
			addComponent(viewComponent);
		} else {
			replaceComponent(currentView, viewComponent);
		}
		currentView = viewComponent;
	}
	

	public void addView(Navigator navigator, final String name, final String viewName,
			Class<? extends View> view) {
		navigator.addView(viewName, view);
		Button menubutton = new Button(name, new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(viewName);
			}
		});
		buttonContainer.addComponent(menubutton);
	}
}
