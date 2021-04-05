/**
 * Gets a set of related Entities for a given entity using navigation criteria.
 * @param navigationData
 * @returns Entity Collection of related (linked to) Entities
 */
function getRelatedEntityCollection(navigationData) {
    var navigationHelper = Java.type('com.ptc.odata.windchill.variantSpecification.entity.service.VariantSpecificationNavigationHelper');
    return navigationHelper.getRelatedEntityCollection(navigationData);
}
