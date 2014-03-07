package com.jensjansson.bedrock;

import javax.servlet.ServletException;

import com.vaadin.server.DeploymentConfiguration;
import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServletService;

public class ForwardServlet extends VaadinServlet implements SessionInitListener {

	@Override
	protected VaadinServletService createServletService(
			DeploymentConfiguration deploymentConfiguration)
			throws ServiceException {
		return new VaadinServletService(this, deploymentConfiguration) {
			@Override
			public String getStaticFileLocation(VaadinRequest request) {
				// TODO Auto-generated method stub
				return request.getContextPath();
			}
		};
	}
	
	@Override
	public void sessionInit(SessionInitEvent event) {
		try {
			super.servletInitialized();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		getService().addSessionInitListener(this);
	}
}
