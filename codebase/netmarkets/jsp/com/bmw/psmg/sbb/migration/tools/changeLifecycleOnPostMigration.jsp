<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="com.bmw.psmg.sbb.tools.transformation.resource.ChangeLifecycleToolResource"
               var="resource"/>


<script type="text/javascript">
    var isUploaderScriptLoaded = window.isUploaderScriptLoaded;
    if (isUploaderScriptLoaded == null) {
        PTC.navigation.loadScript('core-ui/2.2/lib/jquery.js');
    } else {
        console.log("File uploader scripts already loaded.")
    }

    $("#fileInput").change(handleUploadButton);

    function handleUploadButton() {
        var uploadButton = $("#uploadButton");
        if ($("#fileInput").val()) {
            uploadButton.prop("disabled", false);
        } else {
            uploadButton.prop("disabled", true);
        }
    }

    $("#uploadButton").click(function () {
        getFileContent(sendRequest);
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
        var data = {
            fileContent: fileContent
        }

        $.ajax({
            type: "POST",
            url: 'servlet/IE/tasks/com/bmw/psmg/sbb/tools/sendFileToAssemblyKitHeaderLifecycleTool.xml',
            data: data,
            cache: false, // guarantees jsp is always called
            success: function (response) {
                hideLoadingGif();
                var exception = $(response).find("exception").text();
                var invalidFileException = $(response).find("exception").text().includes("Exception");
                if (invalidFileException === true)
                    PTC.messaging.showInlineMessage(
                        [{
                            MessageType: 'FAILURE',
                            MessageTitle: '<fmt:message key="INVALID_FILE_TITLE" bundle="${resource}" />',
                            Messages: ['<fmt:message key="INVALID_FILE_TEXT" bundle="${resource}" />'],
                        }]);
                else {
                    if (exception != '')
                        PTC.messaging.showInlineMessage(
                            [{
                                MessageType: 'FAILURE',
                                MessageTitle: '<fmt:message key="INVALID_FILE_TITLE" bundle="${resource}" />',
                                Messages: ['<fmt:message key="CONTACT_ADMINISTRATOR_TEXT" bundle="${resource}" />'],
                            }]);
                    else
                        PTC.messaging.showInlineMessage(
                            [{
                                MessageType: 'SUCCESS',
                                MessageTitle: '<fmt:message key="TASK_SUCCESSFUL_TITLE" bundle="${resource}" />',
                                Messages: ['<fmt:message key="TASK_SUCCESSFUL_TEXT" bundle="${resource}" />'],
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

    function resizable(el, factor) {
        var int = Number(factor) || 7.7;

        function resize() {
            if (el.value.length * int > 0) {
                el.style.width = ((el.value.length * 1.1) * int) + 'px'
            } else {
                el.style.width = (30 * int) + 'px'
            }
        }

        var e = 'keyup,keypress,focus,blur,change'.split(',');
        for (var i in e) el.addEventListener(e[i], resize, false);
        resize();
    }

    resizable(document.getElementById('fileInput'), 7);


</script>

<html>

<head>
    <title>Post migration tool to set lifecycle states</title>
</head>

<body style="margin:20px 20px 20px 20px">
<h3>
    <fmt:message key="TITLE_LABEL" bundle="${resource}"/>
</h3>
<p style="margin-top:5px;">
    <fmt:message key="LOAD_FILE_LABEL" bundle="${resource}"/>:
    <input id="fileInput" type="file" name="file" accept=".csv"/>
</p>
<button id="uploadButton" disabled style="margin-top:5px;">
    <fmt:message key="RUN_TOOL_LABEL" bundle="${resource}"/>
</button>
<div id="loadingContainer" hidden class="loading"
     style="background: url(netmarkets/javascript/ext/resources/images/default/shared/blue-loading.gif) no-repeat center;height:50px;"></div>
<div id="overlay" hidden
     style="background-color:rgba(0, 0, 0, 0.4);z-index:1001;position:fixed;left:0;top:0;width:100%;height:100%;"></div>
</body>

</html>