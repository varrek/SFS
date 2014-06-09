<%@taglib prefix="struts" uri="/struts-tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<jstl:if test="${result == 'WrongPassword'}">
	Wrong login/password!<br/>
</jstl:if>
<struts:form action="askRepoAccess">
	<struts:textfield name="name" label="Repositiry Name"/>
         <struts:hidden name="users" value="%{#parameters['users']}"/>
	<struts:submit/>
</struts:form>