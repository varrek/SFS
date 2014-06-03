<%@taglib prefix="struts" uri="/struts-tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<struts:form action="fileUpload" method="post" enctype="multipart/form-data" >
<struts:file name="file" label="File" />
<struts:file name="file" label="Signature" />
<struts:submit />
</struts:form>        


