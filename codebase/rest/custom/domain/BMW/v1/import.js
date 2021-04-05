
function function_AdjustFolderStructure(data, params) {
    var getSynchronizeStructures = Java.type("com.bmw.psmg.sbb.interfaces.demo.sprint7.DemoTaskInvoker");
    return getSynchronizeStructures.synchronizeStructures(params);
}
