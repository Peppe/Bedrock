package com.jensjansson.bedrock.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class AnotherView extends VerticalLayout implements View {

	public AnotherView() {
		Label about = new Label("Woof Woof!");
		addComponent(about);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
