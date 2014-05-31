package org.varrek.mwork.webui;

import org.varrek.mwork.user.User;
import org.varrek.mwork.user.UserController;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Web application lifecycle listener.
 *
 * @author tempos
 */
public class RequestListener implements ServletRequestListener {

	
	private UserController controller = new UserController();

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		String sessionId = ((HttpServletRequest) sre.getServletRequest()).getSession().getId();
		User currentUser = controller.getCurrentUser(sessionId);
		sre.getServletRequest().setAttribute("currentUser", currentUser);
	}
}
