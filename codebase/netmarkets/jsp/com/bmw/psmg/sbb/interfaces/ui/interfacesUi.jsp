<%@ page import="wt.util.jmx.AccessUtil" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="uiConfigBean" scope="request" class="com.bmw.psmg.sbb.interfaces.ui.InterfaceUiConfigBean"/>
<jsp:setProperty name="uiConfigBean" property="locale" value="${localeBean.getLocale()}"/>
<fmt:setBundle basename="com.bmw.psmg.sbb.interfaces.ui.interfaceUiResource" var="interfaceUiResources"/>

<%
    if (!AccessUtil.isSiteAdministrator()) {
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
        return;
    }
%>

<script type="text/javascript">
    var isUploaderScriptLoaded = window.isUploaderScriptLoaded;
    if (isUploaderScriptLoaded == null) {
        PTC.navigation.loadScript('core-ui/2.2/lib/jquery.js');
    }
    else {
        console.log("File uploader scripts already loaded.")
    }

    var config = ${uiConfigBean.getLocalizedConfig()};

    (function populateInterfaces() {
        var interfaces = config.interfaces;
        interfaces.forEach(function (interface, index) {
            $(interfaceSelect).append($("<option></option>").attr("value", index).text(interface.name));
        });
    })();

    function onInterfaceSelectChange() {
        var selectedInterface = $(interfaceSelect).val();
        if (selectedInterface === '-') {
            $(optionSelect).empty();
            $(optionSelect).append($("<option></option>").attr("value", '-').text('-'));
            $(optionSelect).prop('disabled', true);
        } else {
            var interface = config.interfaces[selectedInterface];
            var options = interface.options;
            $(optionSelect).empty();
            options.forEach(function (option, index) {
                $(optionSelect).append($("<option></option>").attr("value", index).text(option.name));
            });
            $(optionSelect).prop('disabled', false);
        }
        handleUploadButton();
    }

    $("#fileInput").change(handleUploadButton);

    function handleUploadButton() {
        var uploadButton = $("#uploadButton");
        if ($(interfaceSelect).val() === '-') {
            uploadButton.prop("disabled", true);
        } else if ($("#fileInput").val() && $("#comment").val() && $(interfaceSelect).val() != '-' ||
            (config.interfaces[parseInt($(interfaceSelect).val())].interfaceInternalName === "IF_MMG_SBB_002" && parseInt($(optionSelect).val()) === 1) ||
            (config.interfaces[parseInt($(interfaceSelect).val())].interfaceInternalName === "IF_PSM_SBB_104" ) ||
            (config.interfaces[parseInt($(interfaceSelect).val())].interfaceInternalName === "IF_PSM_SBB_102" ) ||
            (config.interfaces[parseInt($(interfaceSelect).val())].interfaceInternalName === "IF_PSM_SBB_101" )) {
            uploadButton.prop("disabled", false);
        } else {
            uploadButton.prop("disabled", true);
        }
    }

    $("#uploadButton").click(function () {
        if((config.interfaces[parseInt($(interfaceSelect).val())].interfaceInternalName === "IF_MMG_SBB_002" && parseInt($(optionSelect).val()) === 1) ||
                (config.interfaces[parseInt($(interfaceSelect).val())].interfaceInternalName === "IF_PSM_SBB_104") ||
                (config.interfaces[parseInt($(interfaceSelect).val())].interfaceInternalName === "IF_PSM_SBB_102") ||
                (config.interfaces[parseInt($(interfaceSelect).val())].interfaceInternalName === "IF_PSM_SBB_101")) {
            sendRequest('');
        } else {
            getFileContent(sendRequest);
        }
    });

    function getFileContent(callback) {
        var inputFile = document.getElementById("fileInput").files[0];
        var fileContent;
        if (inputFile) {
            var reader = new FileReader();
            reader.onload = function (e) {
                fileContent = e.target.result;
                callback(fileContent)
            }
            reader.readAsText(inputFile);
        }
    }

    function sendRequest(fileContent) {
        var selectedInterface = parseInt($(interfaceSelect).val());
        var selectedOption = parseInt($(optionSelect).val());
        var option = config.interfaces[selectedInterface].options[selectedOption];
        var data;
        var urlCalled = option.task;
        if (option.optionInternalName === "UPLOAD_XML_OPTION") {
            urlCalled = "servlet/IE/tasks/com/bmw/psmg/sbb/interfaces/ui/callTask.xml";
        }
        var task = option.task;
        var schema = option.schema;
        var groupIn = option.groupIn;
        var xslUrl = option.xslUrl;
        var interfaceName = option.interfaceName;

        var data = {
            xmlFileContent: fileContent,
            taskName: task,
            groupIn: groupIn,
            xslUrl: xslUrl,
            interfaceName: interfaceName,
            comment: $("#comment").val()
        };
        showLoadingGif();
        $.ajax({
            type: "POST",
            url: urlCalled,
            data: data,
            success: function (response) {
                hideLoadingGif();
                var exception = $(response).find("exception").text();
                var invalidMessageException = $(response).find("exception").text().includes("InvalidMessageException");
                if (invalidMessageException === true)
                    PTC.messaging.showInlineMessage(
                        [{
                            MessageType: 'FAILURE',
                            MessageTitle: '<fmt:message key="TASK_FAILED_TITLE" bundle="${interfaceUiResources}" />',
                            Messages: ['<fmt:message key="INVALID_MESSAGE_TEXT" bundle="${interfaceUiResources}" />'],
                        }]);
                else {
                    if (exception != '')
                        PTC.messaging.showInlineMessage(
                            [{
                                MessageType: 'FAILURE',
                                MessageTitle: '<fmt:message key="TASK_FAILED_TITLE" bundle="${interfaceUiResources}" />',
                                Messages: ['<fmt:message key="CONTACT_ADMINISTRATOR_TEXT" bundle="${interfaceUiResources}" />'],
                            }]);
                    else
                        PTC.messaging.showInlineMessage(
                            [{
                                MessageType: 'SUCCESS',
                                MessageTitle: '<fmt:message key="TASK_SUCCESSFUL_TITLE" bundle="${interfaceUiResources}" />',
                                Messages: ['<fmt:message key="TASK_SUCCESSFUL_TEXT" bundle="${interfaceUiResources}" />'],
                            }]);
                    }
            }
        });
    }

    function showLoadingGif() {
        $("#overlay").show();
        $("#loadingContainer").show();
    }

    function hideLoadingGif() {
        $("#overlay").hide();
        $("#loadingContainer").hide();
    }

    function resizable (el, factor) {
      var int = Number(factor) || 7.7;
      function resize() {
        if(el.value.length * int > 0){
        el.style.width = ((el.value.length * 1.1) * int) + 'px'
        }else{
          el.style.width = (30 * int) + 'px'
        }
      }
      var e = 'keyup,keypress,focus,blur,change'.split(',');
      for (var i in e) el.addEventListener(e[i],resize,false);
      resize();
    }
    resizable(document.getElementById('fileInput'),7);


</script>

<html>

<head>
    <title>Integration Testing</title>
</head>

<body style="margin:20px 20px 20px 20px">
    <h3>
        <fmt:message key="INTERFACE_CONNECTION_TESTS_LABEL" bundle="${interfaceUiResources}"/>
    </h3>
    <p style="margin-top:20px;">
        <fmt:message key="CHOOSE_INTERFACE_LABEL" bundle="${interfaceUiResources}" />
    </p>
    <select name="interfaceSelect" id="interfaceSelect" onchange="onInterfaceSelectChange()">
        <option selected="selected">-</option>
    </select>
    <p style="margin-top:5px;">
        <fmt:message key="CHOOSE_OPTION_LABEL" bundle="${interfaceUiResources}" />
    </p>
    <select name="optionSelect" id="optionSelect" disabled onchange="handleUploadButton()">
        <option selected="selected">-</option>
    </select>
    <p style="margin-top:5px;">
        <fmt:message key="COMMENT_LABEL" bundle="${interfaceUiResources}" />
    </p>
    <input type="text" id="comment" name="comment" onchange="handleUploadButton()"/>
    <p style="margin-top:5px;">
        <fmt:message key="FILE_UPLOAD_LABEL" bundle="${interfaceUiResources}" />:
        <input id="fileInput" type="file" name="file" />
    </p>
    <button id="uploadButton" disabled style="margin-top:5px;">
        <fmt:message key="UPLOAD_BUTTON_LABEL" bundle="${interfaceUiResources}" />
    </button>
    <div id="loadingContainer" hidden class="loading" style="background: url(netmarkets/javascript/ext/resources/images/default/shared/blue-loading.gif) no-repeat center;height:50px;"></div>
    <div id="overlay" hidden style="background-color:rgba(0, 0, 0, 0.4);z-index:1001;position:fixed;left:0;top:0;width:100%;height:100%;"></div>
</body>

</html>