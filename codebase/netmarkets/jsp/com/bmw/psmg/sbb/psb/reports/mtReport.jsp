<%@ include file="/netmarkets/jsp/util/beginPopup.jspf"%>
<%@ taglib uri="http://www.ptc.com/windchill/taglib/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.ptc.com/windchill/taglib/mvc" prefix="mvc"%>
<%@ page import="com.bmw.psmg.sbb.reports.resources.psbCustomActionsRB,com.bmw.psmg.sbb.reports.resources.MTStatusReportRB"%>

<%-- Populate variables needed for report generation --%>
<%@ include file="/netmarkets/jsp/part/reports/beginTreeReport.jspf"%>

<%-- Populate labels for header section and report table --%>
<%@ include file="/netmarkets/jsp/part/reports/reportLabels.jspf"%>

<fmt:setBundle basename="com.bmw.psmg.sbb.reports.resources.MTStatusReportRB" /> 
<fmt:message var="inputDateEffectivityLabel" key="<%=MTStatusReportRB.INPUT_DATE_EFFECTIVITY%>" />
<fmt:message var="tableLabel" key="<%=psbCustomActionsRB.PSB_MT_STATUS_LABEL%>" />

<%

 
String ncid=request.getParameter("ncId");
java.sql.Timestamp inputEffectivity =com.bmw.psmg.sbb.reports.helper.MTStatusReportHelper.getInputEffectivityDateFromNCId(commandBean,ncid);
 
if(inputEffectivity==null){
	String noConfigSpecMsg = WTMessage.getLocalizedMessage("com.bmw.psmg.sbb.reports.resources.MTStatusReportRB", MTStatusReportRB.NO_CONFIG_SPEC_SELECTED, null, SessionHelper.getLocale());

	%>
	<script>
		alert('<%=noConfigSpecMsg%>');
		window.close();
	</script>
<%
}else{
HashMap requestMap = commandBean.getRequestData().getParameterMap();
requestMap.put("inputEffectivity",new String[] {""+inputEffectivity});
commandBean.getRequestData().setParameterMap(requestMap);
%>
<input type="hidden" id="inputEffectivity" name="inputEffectivity" value="<%=inputEffectivity%>"%>

<%-- Display header section --%>
<%@ include file="/netmarkets/jsp/com/bmw/psmg/sbb/psb/reports/mtReportHeader.jspf"%>

<%-- Display the tree table--%>
<jsp:include page="${mvc:getComponentURL('com.bmw.psmg.sbb.reports.service.MTStatusReportTableBuilder')}" />

<%}%>
<% escaping = true; %>
<%@ include file="/netmarkets/jsp/util/end.jspf"%>

