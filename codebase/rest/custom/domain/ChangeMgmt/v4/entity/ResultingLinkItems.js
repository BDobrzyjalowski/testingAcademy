function getRelatedEntityCollection(navigationData) {
    var targetName = navigationData.getTargetSetName();
    if("PendingEffectivities".equals(targetName)) {
        return getChangeHelper().getPendingEffectivities(navigationData);
    } else if("BMWPendingEffectivities".equals(targetName)) {
        return getEffectivityHelper().getBMWPendingEffectivities(navigationData);
    }
}

function getChangeHelper() {
    var ChangeHelper = Java.type('com.bmw.psmg.sbb.rest.PendingEffectivityHelper');
    return new ChangeHelper();
}

function getEffectivityHelper() {
    var getEffectivityHelper = Java.type('com.bmw.psmg.sbb.rest.BMWEffectivityHelper');
    return new getEffectivityHelper();
}
