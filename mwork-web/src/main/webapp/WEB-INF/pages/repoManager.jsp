<%@taglib prefix="struts" uri="/struts-tags"%>
<%@ page import="java.util.*" %>
<%@ page import="org.varrek.mwork.*" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<jstl:out value="${currentUser.getMessages()}" />
<table>
    <jstl:forEach items="${currentUser.getRepos()}" var="current">
        <tr>
            <td><a href="/Browser.jsp?dir=D:\\Documents\\Varrek\\Programs\\magwork\\Repos\\<jstl:out value='${current.getName()}'/>">
            <jstl:out value="${current.getName()}" /></a><td>
            <td><jstl:out value="${current.getDescr()}" /><td>
             <jstl:choose>
                <jstl:when test="${currentUser.getRepoRight(current).isAdmin() == true}">
                    <td><a href="/addManagers.jsp?name=${current.getName()}">Add managers</a><td>
                </jstl:when>
                <jstl:otherwise></jstl:otherwise>
            </jstl:choose>    
        </tr>
    </jstl:forEach>
</table>

<a href="/createRepo.jsp">Create new Repository</a>