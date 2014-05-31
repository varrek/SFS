<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>
			<tiles:getAsString name="title" />
		</title>
	</head>
	<body>
		<div style="float:top">
			<tiles:insertAttribute name="header" />
			<tiles:insertAttribute name="navigation" />
		</div>
		<div>
			<tiles:insertAttribute name="content" />
		</div>
		<div style="float:bottom">
			<tiles:insertAttribute name="footer" />
		</div>
	</body>
</html>