package com.jensjansson.bedrock;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(SecurityUtils.getSubject().getPrincipal() != null){
			System.out.println("already logged in");
			System.out.println(request.getContextPath());
			response.sendRedirect(request.getContextPath() + "/");
			return;
		}
		
		final URL url = getServletContext().getResource("login.html");
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("text/html");
		final OutputStream os = response.getOutputStream();
		writeStream(url.openStream(),os);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getRequestURI());
		System.out.println(request.getContextPath());
//		response.sendRedirect(request.getContextPath());
	}
	
	protected static void writeStream(InputStream is, OutputStream os) throws IOException {
		try {
			byte[] buf = new byte[4*1024];
			int bytesRead;
			while ((bytesRead = is.read(buf)) != -1)
				os.write(buf, 0, bytesRead);
		} finally {
			is.close();
			os.close();
		}
	}
}
