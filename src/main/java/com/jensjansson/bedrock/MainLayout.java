package com.jensjansson.bedrock;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.jensjansson.bedrock.view.AboutView;
import com.jensjansson.bedrock.view.AnotherView;
import com.jensjansson.bedrock.view.ErrorView;
import com.jensjansson.bedrock.view.HomeView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class MainLayout extends VerticalLayout implements ViewDisplay {

	private HorizontalLayout middle;
	private Component currentView;
	
	public MainLayout(AppUI ui) {
		setMargin(true);
		
		HorizontalLayout topbar = createTopbar();		
		addComponent(topbar);
		
		Navigator navigator = new Navigator(ui, (ViewDisplay) this);
		addView(navigator, "Home", "", HomeView.class);
		addView(navigator, "Another", "another", AnotherView.class);
		addView(navigator, "About", "about", AboutView.class);
		navigator.setErrorView(ErrorView.class);
		ui.setNavigator(navigator);
	}
	
	private HorizontalLayout createTopbar(){
		HorizontalLayout topbar = new HorizontalLayout();
		topbar.setWidth("100%");
		HorizontalLayout leftSide = new HorizontalLayout();
		leftSide.setWidth(null);
		
		middle = new HorizontalLayout();
		middle.setWidth(null);
		
		HorizontalLayout rightSide = new HorizontalLayout();
		rightSide.setWidth(null);
		final Subject currentUser = SecurityUtils.getSubject();
		String user = "unknown";
		if(currentUser.getPrincipal() != null){
			user = currentUser.getPrincipal().toString();
		}
		Button logout = new Button("Logout", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				VaadinSession.getCurrent().getSession().invalidate();
				Page.getCurrent().setLocation(Page.getCurrent().getLocation() + "/logout");
			}
		});
		rightSide.addComponent(new Label("Welcome " + user));
		rightSide.addComponent(logout);
		topbar.addComponent(leftSide);
		topbar.addComponent(middle);
		topbar.addComponent(rightSide);
		topbar.setComponentAlignment(leftSide, Alignment.MIDDLE_LEFT);
		topbar.setComponentAlignment(middle, Alignment.MIDDLE_CENTER);
		topbar.setComponentAlignment(rightSide, Alignment.MIDDLE_RIGHT);
		
		return topbar;
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
		middle.addComponent(menubutton);
	}
}
