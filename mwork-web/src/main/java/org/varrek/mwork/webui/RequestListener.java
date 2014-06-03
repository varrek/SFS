package org.varrek.mwork.webui;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import org.varrek.mwork.repo.Repo;
import org.varrek.mwork.user.User;
import org.varrek.mwork.user.UserController;

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
        if (currentUser != null) {
            String tempPath = "";
            for (Repo curr : currentUser.getRepos()) {
                tempPath += "D:\\Documents\\Varrek\\Programs\\magwork\\Repos\\" + curr.getName() + ";";
            }
            sre.getServletRequest().setAttribute("tempPath", tempPath);
        }
    }
}
