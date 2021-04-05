<%@ page import="wt.util.HTMLEncoder"%>
<%@ page import="wt.util.WTProperties"%>
<%
	final String baseURL = WTProperties.getLocalProperties().getProperty("wt.server.codebase", null);
	final String targetActionURL = "/servlet/com/bmw/psmg/sbb/interfaces/getMessage?";
	final String encodedQueryString = HTMLEncoder.encodeForJavascript(request.getQueryString());
	final String popupURL = baseURL + targetActionURL + encodedQueryString;
%>
<script type="text/javascript">
	window.open('<%=popupURL%>', '');
	window.history.back();
</script>