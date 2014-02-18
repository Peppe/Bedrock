package com.jensjansson.bedrock.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ErrorView extends VerticalLayout implements View {

	public ErrorView() {
		Label about = new Label("Oops! You tried to access a page that doesn't exist. Use the menu at the top to navigate elsewhere.");
		addComponent(about);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
