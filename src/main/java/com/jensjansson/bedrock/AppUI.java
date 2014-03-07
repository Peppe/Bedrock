package com.jensjansson.bedrock;

import javax.servlet.annotation.WebServlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Theme("bedrock")
@SuppressWarnings("serial")
public class AppUI extends UI {

	private static final transient Logger log = LoggerFactory
			.getLogger(AppUI.class);

	@Override
	protected void init(VaadinRequest request) {
		setContent(new MainLayout(this));
		log.warn("UI inited");
	}
}
