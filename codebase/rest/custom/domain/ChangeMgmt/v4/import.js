function action_UpdateApprovalStatus(data, params) {
    // call method in ApprovalStatusUpdateProcessor
    var ApprovalStatusUpdateProcessor = Java.type("com.bmw.psmg.sbb.rest.processors.ApprovalStatusUpdateProcessor");
    return ApprovalStatusUpdateProcessor.updateApprovalStatus(data, params);
}

function action_SearchPositionVariants(data, params) {
    var VorfReleasedPositionVariantSearchProcessor = Java.type("com.bmw.psmg.sbb.rest.processors.VorfReleasedPositionVariantSearchProcessor");
    return VorfReleasedPositionVariantSearchProcessor.searchVorfReleasedPositionVariants(data, params);
}

function action_CreateProfReleasedApprovalPacket(data, params) {
    var ProfReleasedApprovalPacketCreationProcessor = Java.type("com.bmw.psmg.sbb.rest.processors.ProfReleasedApprovalPacketCreationProcessor");
    return ProfReleasedApprovalPacketCreationProcessor.createProfReleasedApprovalPacket(data, params);
}
