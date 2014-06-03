<%@taglib prefix="struts" uri="/struts-tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<h3>111${verResult}</h3>
 <jstl:out value="${verResult}"/>
 <tr>name="<%= request.getAttribute("verResult") %>"</tr> 
 <script type="text/javascript">