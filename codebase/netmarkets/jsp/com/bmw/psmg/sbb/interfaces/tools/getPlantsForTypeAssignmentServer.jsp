<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="/netmarkets/jsp/util/context.jsp"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="wt.fc.PersistenceHelper" %>
<%@ page import="wt.fc.QueryResult" %>
<%@ page import="wt.query.QuerySpec" %>
<%@ page import="wt.query.SearchCondition" %>
<%@ page import="wt.part.WTPart" %>
<%@ page import="wt.part.WTPartMaster" %>
<%@ page import="wt.vc.VersionControlHelper" %>
<%@ page import="wt.vc.config.ConfigHelper" %>
<%@ page import="wt.vc.config.LatestConfigSpec" %>
<%@ page import="wt.vc.config.ViewConfigSpec" %>
<%@ page import="wt.vc.views.View" %>
<%@ page import="wt.vc.views.ViewHelper" %>
<%@ page import="wt.effectivity.WTDatedEffectivity" %>
<%@ page import="wt.identity.IdentityFactory" %>
<%@ page import="com.bmw.psmg.sbb.workflow.VsedWorkflowHelper" %>
<%@ page import="com.bmw.psmg.sbb.utilities.PartUtils" %>
<%@ page import="com.bmw.psmg.sbb.generic.PSMGSBBConstants.Views" %>
<%@ page import="com.ptc.wpcfg.doc.VariantSpec" %>

<%
	final String NEW_LINE_SIGN = "<br>";
	final String BOLD_BEGIN_SIGN = "<b>";
	final String BOLD_END_SIGN = "</b>";
	final String EFFECTIVITY_SEPARATOR = ", ";

	String typeAssignment = request.getParameter("typeAssignment");
	String posVarNumber = request.getParameter("posVarNumber");
	String posVarRevision = request.getParameter("posVarRevision");
	
	StringBuilder resultSB = new StringBuilder();
	Set<String> uniquePlants = new HashSet<>();
	if (typeAssignment != null && !typeAssignment.isEmpty()) {
		String[] splitTypeAssignment = typeAssignment.split(",");
		String separator = "";
		for (String singleTypeAssignment : splitTypeAssignment) {
			resultSB.append(separator).append(separator);
			resultSB.append(BOLD_BEGIN_SIGN).append(singleTypeAssignment).append(BOLD_END_SIGN).append(NEW_LINE_SIGN);
			WTPart part = null;
			QuerySpec querySpec = new QuerySpec(WTPartMaster.class);
        	querySpec.appendWhere(new SearchCondition(WTPartMaster.class, WTPartMaster.NUMBER, SearchCondition.EQUAL, singleTypeAssignment, false), new int[] { 0 });
        	QueryResult queryResult = PersistenceHelper.manager.find(querySpec);
       		if (queryResult.hasMoreElements()) {
            	WTPartMaster partMaster = (WTPartMaster) queryResult.nextElement();
            	part = (WTPart) VersionControlHelper.service.allIterationsOf(partMaster).nextElement();
        	}
        	if (part != null) {
        		List<WTDatedEffectivity> effectivityStatements = VsedWorkflowHelper.getEffectivityStatementFromPart(part);
        		String effectivitySeparator = "";
        		for (WTDatedEffectivity effectivityStatement : effectivityStatements) {
        			String effContext = effectivityStatement.getEffContext().getIdentificationObject().getIdentity();
        			uniquePlants.add(effContext);
        			resultSB.append(effectivitySeparator);
        			resultSB.append(effContext);
        			effectivitySeparator = EFFECTIVITY_SEPARATOR;
        		}
        	}
			separator = NEW_LINE_SIGN;
		}
	} else if (posVarNumber != null && !posVarNumber.isEmpty()) {
		WTPartMaster partMaster = PartUtils.getWTPartMaster("", posVarNumber);
		if (partMaster != null) {
			QueryResult allRevisionsQR = ConfigHelper.service.filteredIterationsOf(partMaster, ViewConfigSpec.newViewConfigSpec(ViewHelper.service.getView(Views.MMG)));
			boolean specificRevision = posVarRevision != null && !posVarRevision.isEmpty();
			List<WTPart> posVars = new ArrayList<>();
			while (allRevisionsQR.hasMoreElements()) {
				WTPart nextPart = (WTPart) allRevisionsQR.nextElement();
				if (VersionControlHelper.isLatestIteration(nextPart)) {
					if (specificRevision) {
						if (nextPart.getVersionIdentifier().getValue().equals(posVarRevision)) {
							posVars.add(nextPart);
							break;
						}
					} else {
							posVars.add(nextPart);					
					}
				}
			}
			String separator = "";
			for (WTPart posVar : posVars) {
				resultSB.append(separator).append(separator);
				resultSB.append(BOLD_BEGIN_SIGN).append(IdentityFactory.getDisplayIdentifier(posVar)).append(BOLD_END_SIGN).append(NEW_LINE_SIGN);
				List<VariantSpec> typeFilters = VsedWorkflowHelper.getTypeFilterFromPart(posVar);
				List<WTPart> wtPartsFromTypeFilters = new ArrayList<>();
				for (VariantSpec typeFilter : typeFilters) {
					wtPartsFromTypeFilters.addAll(VsedWorkflowHelper.getWTPartFromTypeFilterName(typeFilter));
				}
				String effectivitySeparator = "";
				for (WTPart wtPartFromTypeFilter : wtPartsFromTypeFilters) {
					List<WTDatedEffectivity> effectivityStatements = VsedWorkflowHelper.getEffectivityStatementFromPart(wtPartFromTypeFilter);
        			for (WTDatedEffectivity effectivityStatement : effectivityStatements) {
        				String effContext = effectivityStatement.getEffContext().getIdentificationObject().getIdentity();
        				uniquePlants.add(effContext);
        				resultSB.append(effectivitySeparator);
        				resultSB.append(effContext);
        				effectivitySeparator = EFFECTIVITY_SEPARATOR;
        			}
				}
				separator = NEW_LINE_SIGN;
			}
		}
	}
	if (!uniquePlants.isEmpty()) {
		resultSB.append(NEW_LINE_SIGN).append(NEW_LINE_SIGN).append(NEW_LINE_SIGN).append(BOLD_BEGIN_SIGN).append("Unique plants").append(BOLD_END_SIGN).append(NEW_LINE_SIGN);
		String effectivitySeparator = "";
		for (String uniquePlant : uniquePlants) {
			resultSB.append(effectivitySeparator);
			resultSB.append(uniquePlant);
			effectivitySeparator = EFFECTIVITY_SEPARATOR;
		}		
	}
	out.write(resultSB.toString());
%>
