<%@ page import="wt.util.jmx.AccessUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="com.bmw.psmg.sbb.tools.toolsResource" var="toolsResources"/>

<%
    if (!AccessUtil.isSiteAdministrator()) {
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
        return;
    }
%>

<html>

<script type="text/javascript">

function fetchPlants() {
	var typeAssignment = document.getElementById("typeAssignmentInput").value;
	var posVarNumber = document.getElementById("posVarNumberInput").value;
	var posVarRevision = document.getElementById("posVarRevisionInput").value;
	var options = {
		asynchronous: false,
		parameters: 'typeAssignment=' + typeAssignment
					+ '&posVarNumber=' + posVarNumber
					+ '&posVarRevision=' + posVarRevision
	};
	var response = requestHandler.doRequest('netmarkets/jsp/com/bmw/psmg/sbb/interfaces/tools/getPlantsForTypeAssignmentServer.jsp', options);
	var responseText = response.responseText.trim().split("|").join("<br>");
	document.getElementById("plantsResultText").innerHTML = responseText;
}
</script>

<head>
    <title><fmt:message key="GET_PLANTS_FOR_TYPE_ASSIGNMENT_LABEL" bundle="${toolsResources}"/></title>
</head>

<body style="margin:20px 20px 20px 20px">
    <h3>
        <fmt:message key="GET_PLANTS_FOR_TYPE_ASSIGNMENT_LABEL" bundle="${toolsResources}"/>
    </h3>
    <p style="margin-top:20px;">
        <i><small>
        Hints:<br>
        You can type multiple type assignments, separated with commas <br>
        If you provide Position Variant Number only, but no revision, all revisions will be fetched.
        </small></i>
    </p>
    <p style="margin-top:20px;">
        <fmt:message key="TYPE_ASSIGNMENT_LABEL" bundle="${toolsResources}" />:
        <input id="typeAssignmentInput" type="text" name="typeAssignmentInput" />
    </p>
    <hr style="margin-top:10px; width:10%; margin-left:0px; text-align:left;"/>
    <p style="margin-top:10px;">
        <fmt:message key="POS_VAR_NUMBER_LABEL" bundle="${toolsResources}" />:
        <input id="posVarNumberInput" type="text" name="typeAssignmentInput" />
    </p>
    <p style="margin-top:10px;">
        <fmt:message key="POS_VAR_REVISION_LABEL" bundle="${toolsResources}" />:
        <input id="posVarRevisionInput" type="text" name="typeAssignmentInput" />
    </p>
    <button id="getPlantsButton" style="margin-top:15px;" onClick="fetchPlants();">
        <fmt:message key="GET_PLANTS_BUTTON_LABEL" bundle="${toolsResources}" />
    </button>
    <hr style="margin-top:20px; width:75%; margin-left:0px; text-align:left;"/>
    <div id="plantsResult">
    	<p id="plantsResultText" style="margin-top:10px;"/>
	</div>
</body>

</html>
