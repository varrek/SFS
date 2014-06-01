<%@taglib prefix="struts" uri="/struts-tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<struts:form action="userRegister">
	<struts:textfield name="login" label="Login"/>
	<struts:password name="password" label="Password"/>
	<struts:password name="repassword" label="Retype Password"/>
	<struts:textfield name="email" label="E-mail"/>
	<struts:textfield name="reemail" label="Retype e-mail"/>
	<struts:textfield name="fullname" label="Fullname"/>
        <struts:textfield name="address" label="Address"/>
	<struts:submit/>
</struts:form>