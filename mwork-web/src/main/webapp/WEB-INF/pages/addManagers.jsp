<%@taglib prefix="struts" uri="/struts-tags"%>
<%@ page import="java.util.*" %>
<%@ page import="org.varrek.mwork.*" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<struts:form action="addManager">
	<struts:textarea name="users" label="Users"/>
        <struts:hidden name="name" value="%{#parameters['name']}"/>
	<struts:submit/>
</struts:form>