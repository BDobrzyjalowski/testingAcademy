<%@ page import="wt.util.jmx.AccessUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="com.bmw.psmg.sbb.tools.toolsResource" var="toolsResources"/>
<fmt:setBundle basename="com.bmw.psmg.sbb.interfaces.ui.interfaceUiResource" var="interfaceUiResources"/>

<%
    if (!AccessUtil.isSiteAdministrator()) {
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
        return;
    }
%>

<html>

<head>
    <title>Tools</title>
</head>

<body style="margin:20px 20px 20px 20px">
    <h3>
        <fmt:message key="TOOLS_LABEL" bundle="${toolsResources}"/>
    </h3>
    <p style="margin-top:20px;"/>
    <p style="margin-top:10px;">
		<a href="app/#ptc1/com/bmw/psmg/sbb/interfaces/ui/interfacesUi"><fmt:message key="INTERFACE_CONNECTION_TESTS_LABEL" bundle="${interfaceUiResources}"/></a>
	</p>
	<p style="margin-top:10px;"/>
    	<a href="app/#ptc1/com/bmw/psmg/sbb/interfaces/tools/getPlantsForTypeAssignment"><fmt:message key="GET_PLANTS_FOR_TYPE_ASSIGNMENT_LABEL" bundle="${toolsResources}"/></a>
    </p>
</body>

</html>
