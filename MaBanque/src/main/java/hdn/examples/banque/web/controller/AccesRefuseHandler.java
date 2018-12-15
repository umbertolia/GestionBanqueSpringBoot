package hdn.examples.banque.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;


/**
 * @author Administrator Auteur HDN Crée le Dec 10, 2018
 *
 *         Cette classe permet de gerer l'acces denied
 * 
 */

@Component
public class AccesRefuseHandler implements AccessDeniedHandler {

	private static Logger logger = LoggerFactory.getLogger(AccesRefuseHandler.class);

	@Override
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AccessDeniedException e) throws IOException, ServletException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			logger.info("L'utilisateur " + auth.getName() + " essaye d'acceder à l'URL protégée : "
					+ httpServletRequest.getRequestURI());
		}

		String nomAction = httpServletRequest.getParameter("nomAction");

		httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/403?nomAction=" + nomAction);

	}
}