<%@taglib prefix="struts" uri="/struts-tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<jstl:if test="${result == 'WrongPassword'}">
	Wrong login/password!<br/>
</jstl:if>
<struts:form action="createRepo">
	<struts:textfield name="name" label="Name"/>
	<struts:textfield name="descr" label="descr"/>
	<struts:submit/>
</struts:form>
