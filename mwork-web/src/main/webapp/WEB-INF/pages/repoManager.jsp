<%@taglib prefix="struts" uri="/struts-tags"%>
<%@ page import="java.util.*" %>
<%@ page import="org.varrek.mwork.*" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<table>
    <jstl:forEach items="${currentUser.getRepos()}" var="current">
        <tr>
            <td><a href="/Browser.jsp?dir=D:\\Documents\\Varrek\\Programs\\magwork\\Repos\\<jstl:out value='${current.getName()}'/>"><jstl:out value="${current.getName()}" /></a><td>
            <td><jstl:out value="${current.getDescr()}" /><td>
        </tr>
    </jstl:forEach>
</table>

<a href="/createRepo.jsp">Create new Repository</a>