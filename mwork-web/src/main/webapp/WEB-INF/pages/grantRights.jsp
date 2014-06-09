<%@taglib prefix="struts" uri="/struts-tags"%>
<%@ page import="java.util.*" %>
<%@ page import="org.varrek.mwork.*" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<struts:form action="grantRights">
        <struts:textfield name="code" label="Code"/>
	<struts:hidden name="users" value="%{#parameters['users'][0]}"/>
        <struts:hidden name="name" value="%{#parameters['name'][0]}"/>
	<struts:submit/>
</struts:form>