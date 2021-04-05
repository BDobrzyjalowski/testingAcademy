function getProperty(propertyDescriptor, property, entityObject, processorData) {
    return getHelper().getPendingEffRangeProperty(propertyDescriptor, property, entityObject, processorData);
}

function getHelper() {
    var ChangeHelper = Java.type('com.bmw.psmg.sbb.rest.PendingEffectivityHelper');
    return new ChangeHelper();
}
