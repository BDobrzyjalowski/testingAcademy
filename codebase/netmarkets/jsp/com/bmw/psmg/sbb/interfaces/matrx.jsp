<%@ include file="/netmarkets/jsp/util/begin.jspf" %>
<%@ page import="com.bmw.psmg.sbb.interfaces.mat.mat003.MatrXLinkProvider" %>
<%@ page import="wt.util.WTException" %>
<%@ page import="com.bmw.psmg.sbb.interfaces.mat.mat003.exception.NoMaterialException" %>
<%@ page import="com.bmw.psmg.sbb.interfaces.mat.mat003.exception.NoMaterialNumberException" %>
<%@ page import="com.bmw.psmg.sbb.interfaces.mat.mat003.exception.NoPreferenceException" %>
<%@ page import="wt.util.WTMessage" %>

<%
    String infoMessage =  WTMessage.getLocalizedMessage("com.bmw.psmg.sbb.interfaces.interfaceResource", "checkPopupsMessage");
    String errorMessage = "";
    String url = "";
    try {
        url = new MatrXLinkProvider().getLink(commandBean);
    } catch (WTException ex) {
        errorMessage = WTMessage.getLocalizedMessage("com.bmw.psmg.sbb.interfaces.interfaceResource", "failedToQueryMaterialError");
    } catch (NoMaterialException ex) {
        errorMessage = WTMessage.getLocalizedMessage("com.bmw.psmg.sbb.interfaces.interfaceResource", "noMaterialError");
    } catch (NoMaterialNumberException ex) {
        errorMessage = WTMessage.getLocalizedMessage("com.bmw.psmg.sbb.interfaces.interfaceResource", "failedToQueryPartNoError");
    } catch (NoPreferenceException ex) {
        errorMessage = WTMessage.getLocalizedMessage("com.bmw.psmg.sbb.interfaces.interfaceResource", "noMatrxUrlPreferenceError");
    }
%>

<html>
    <p id="infoMessage" style="font-size: 15px;"></p>
    <p id="errorMessage" style="color: red; font-size: 15px;"></p>
    <script type="text/javascript">
        if ('<%=errorMessage%>' === '') {
            var isOpened = window.open('<%=url%>', "_blank");
            if (isOpened !== null) {
                window.close();
            } else {
                document.getElementById("infoMessage").innerHTML = '<%=infoMessage%>';
            }
        } else {
            document.getElementById("errorMessage").innerHTML = '<%=errorMessage%>';
        }
    </script>
</html>

<%@ include file="/netmarkets/jsp/util/end.jspf" %>