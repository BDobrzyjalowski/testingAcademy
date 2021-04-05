<%@ include file="/netmarkets/jsp/components/beginWizard.jspf"%>
<%@ taglib prefix="wctags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.ptc.com/windchill/taglib/fmt" prefix="fmt"%>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ page import="com.bmw.psmg.sbb.erelease.resource.EReleasesRB"%>
<script>
PTC.editfilter = {};
PTC.editfilter.customconfigspec = {};
PTC.editfilter.customconfigspec.setData = function(selectedDocuments)
{
var csObjectReferenceOverlay = {}
csObjectReferenceOverlay.displayValue =
selectedDocuments.pickedObject[0].number; 
csObjectReferenceOverlay.objectId =
selectedDocuments.pickedObject[0].oid;
opener.PTC.cat.setObjectReference(csObjectReferenceOverlay);
}
window.onunload = function() {
opener.PTC.cat.unlockEditFilterUi();
}</script>

<%
String releaseSearchTitle = WTMessage.getLocalizedMessage("com.bmw.psmg.sbb.erelease.resource.EReleasesRB", EReleasesRB.SEARCH_RELEASE_EFFCTIVITY, null,localeBean.getLocale());
%>
<wctags:itemPicker
id="eReleaseEffectivityPicker"
componentId="eReleaseEffectivityPicker"
objectType="WCTYPE|wt.part.WTPart|com.bmw.psmg.sbb.Release"
showTypePicker="false"
inline="true"
pickedAttributes="number"
pickerTitle="<%= releaseSearchTitle%>"
showWorkingCopy="true"
searchResultsViewId="ERELEASE_PART_SEARCH_VIEW"
pickerCallback="PTC.editfilter.customconfigspec.setData"/>
<%@ include file="/netmarkets/jsp/util/end.jspf"%>

