<%@taglib prefix="struts" uri="/struts-tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<jstl:if test="${result == 'WrongPassword'}">
	Wrong login/password!<br/>
</jstl:if>
<struts:form action="userLogin">
	<struts:textfield name="login" label="Login"/>
	<struts:password name="password" label="Password"/>
	<struts:submit/>
</struts:form>
