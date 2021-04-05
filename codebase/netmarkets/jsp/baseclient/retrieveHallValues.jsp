<%@page import="com.ptc.windchill.mpml.resource.MPMPlant"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.bmw.psmg.sbb.generic.mpmresource.MPMResourceHelper"%>
<%@page import="com.bmw.psmg.sbb.generic.PSMGSBBConstants"%>

<%
String SEPARATOR_UNDERSCORE = "_";
String PLANT_NUMBER_PREFIX  = "_PLANT_0";
String plantName=request.getParameter("plant");

			StringBuilder workCenterList = new StringBuilder();

			if(plantName != null && !plantName.isEmpty()) {
			   MPMPlant mpmPlant = MPMResourceHelper.getMPMPlant(plantName+PLANT_NUMBER_PREFIX);
            if(mpmPlant != null) {			
             List<Map<String, Object>> lineDetails = MPMResourceHelper.getLineDetails(mpmPlant);
             String plantNumber = mpmPlant.getNumber(); // for example: A120_PLANT_0
			 String plantNumberTrim = plantNumber.substring(0,plantNumber.indexOf(SEPARATOR_UNDERSCORE));
            lineDetails.forEach(line -> {
                String lineName = String.valueOf(line.get(PSMGSBBConstants.Attributes.NAME));
                String enumEntryName = plantNumberTrim + SEPARATOR_UNDERSCORE + line.get(PSMGSBBConstants.Attributes.NUMBER);
				workCenterList.append(enumEntryName+"|"+lineName+",");

            });
			}
          }		
		out.println(workCenterList);

 %>