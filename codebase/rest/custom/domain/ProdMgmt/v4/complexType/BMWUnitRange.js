function getProperty(propertyDescriptor, property, entityObject, processorData) {
    return getHelper().getBMWEffRangeProperty(propertyDescriptor, property, entityObject, processorData);
}

function getHelper() {
    var ChangeHelper = Java.type('com.bmw.psmg.sbb.rest.BMWEffectivityHelper');
    return new ChangeHelper();
}
