/**
 * Gets a set of related Entities for a given entity using navigation criteria
 * @param navigationData
 * @returns Entity Collection of related Objects of Effectivities.
 */
function getRelatedEntityCollection(navigationData) {
    var HashMap = Java.type('java.util.HashMap');
    var targetName = navigationData.getTargetSetName();
    if ("BMWEffectivities".equals(targetName)) {
        return getHelper().getBMWEffectivities(navigationData);
    }
    return new HashMap();
}

function getHelper() {
    var EffectivityHelper = Java.type('com.bmw.psmg.sbb.rest.BMWEffectivityHelper');
    return new EffectivityHelper ();
}
