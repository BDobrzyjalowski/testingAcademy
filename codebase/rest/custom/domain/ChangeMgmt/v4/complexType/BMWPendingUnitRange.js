function getProperty(propertyDescriptor, property, entityObject, processorData) {
    return getEffectivityHelper().getBMWEffRangeProperty(propertyDescriptor, property, entityObject, processorData);
}

function getEffectivityHelper() {
    var EffectivityHelper= Java.type('com.bmw.psmg.sbb.rest.BMWEffectivityHelper');
    return new EffectivityHelper();
}
