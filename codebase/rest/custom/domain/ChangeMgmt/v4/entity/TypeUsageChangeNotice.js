function action_UpdateApprovalPacket(data, params) {
    // call method in TypeUsageChangeNoticeUpdateProcessor
    var TypeUsageChangeNoticeUpdateProcessor = Java.type("com.bmw.psmg.sbb.rest.processors.TypeUsageChangeNoticeUpdateProcessor");
    return TypeUsageChangeNoticeUpdateProcessor.updateApprovalPacket(data, params);
}

function action_StartApprovalProcess(data, params) {
    // call method in TypeUsageChangeNoticeApprovalProcessor
    var TypeUsageChangeNoticeApprovalProcessor = Java.type("com.bmw.psmg.sbb.rest.processors.TypeUsageChangeNoticeApprovalProcessor");
    return TypeUsageChangeNoticeApprovalProcessor.startApprovalProcess(data, params);
}

function action_StopApprovalProcess(data, params) {
    // call method in TypeUsageChangeNoticeApprovalProcessor
    var TypeUsageChangeNoticeApprovalProcessor = Java.type("com.bmw.psmg.sbb.rest.processors.TypeUsageChangeNoticeApprovalProcessor");
    return TypeUsageChangeNoticeApprovalProcessor.stopApprovalProcess(data, params);
}

function action_CalculatehPVDeltaData(data, params) {
    // call method in PositionVariantDeltaDataCalculationProcessor
    var PositionVariantDeltaDataCalculationProcessor = Java.type("com.bmw.psmg.sbb.rest.processors.PositionVariantDeltaDataCalculationProcessor");
    return PositionVariantDeltaDataCalculationProcessor.calculatehPVDeltaData(data, params);
}
