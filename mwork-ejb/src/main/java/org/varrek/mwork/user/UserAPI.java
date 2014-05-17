package org.varrek.mwork.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.varrek.mwork.HibernateUtil;

/**
 * Servlet implementation class AndhorUser
 */
public class UserAPI extends HttpServlet{
	private User currentUser;
	private static final long serialVersionUID = 1L;

	public User getCurrentUser(){
		return currentUser;
	}
	public void setCurrentUser(final User user){
		this.currentUser = user;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request,final HttpServletResponse response){
		final UserController UsersOnline = HibernateUtil.getUsers();
		final String Operation = request.getParameter("operation");
		getInitParameterNames();
		PrintWriter out;
		try {
			out = response.getWriter();
			if(Operation == null){
				out.print("503");
			}
			else if(Operation!=null && Operation.equals("login")){
				if(UsersOnline.login(request.getParameter("login"),request.getParameter("password"), request.getSession().getId())){
					currentUser = UsersOnline.getCurrentUser(request.getSession().getId());
					}
				else{
					out.print("Wrong login/password!!!");
				}
			}
			else if(Operation!=null && Operation.equals("logout")){
				if(UsersOnline.logout(UsersOnline.getCurrentUser(request.getSession().getId()))){
					out.print("OK");}
				else{
					out.print("ERROR");
				}
			}
			else if(Operation!=null && Operation.equals("register")){
				final RegistrationResult status = UsersOnline.register(request.getParameter("login"), request.getParameter("password"), request.getParameter("passwordconfirm"),
						request.getParameter("email"), request.getParameter("emailconfirm"), request.getParameter("sex"),true);
				if(status.equals(RegistrationResult.RegistrationSuccessfull)){
					out.print("Register successfull");
				}
				else{
					out.print("Error "+ status);
				}
			}
			else{
				try {
					response.sendError(503);
				} catch (IOException e) {
					Logger.getLogger(HibernateUtil.class).log(Priority.ERROR,e);
				}
			}
		} catch (IOException e) {
			Logger.getLogger(this.getClass()).log(Priority.ERROR, e);
		}
	}
} 
