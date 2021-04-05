package com.bmw.psmg.sbb.generic.persistable;

import com.ptc.core.lwc.server.PersistableAdapter;
import com.ptc.core.meta.common.UpdateOperationIdentifier;
import wt.fc.Persistable;
import wt.fc.WTObject;
import wt.session.SessionHelper;
import wt.util.WTException;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

/**
 * Attribute Service to retrieve attributes from persistable using PersistableAdapter
 *
 * @author K. Poliszkiewicz
 * @author P Lewandowski
 */
public class AttributeService {

    /**
     * Gets value of a given object single attribute
     *
     * @param object            object
     * @param attributeInternal internal name of attribute
     * @return value of attribute or empty optional
     * @throws WTException thrown when a descriptor of the given object's type cannot be obtained.
     */
    public Optional<Object> getValue(WTObject object, String attributeInternal) throws WTException {
        PersistableAdapter persistableAdapter = new PersistableAdapter(object, null, SessionHelper.getLocale(), null);
        persistableAdapter.load(attributeInternal);
        return Optional.ofNullable(persistableAdapter.get(attributeInternal));
    }

    /**
     * Gets value of a given object single attribute
     *
     * @param object            object
     * @param attributeInternal internal name of attribute
     * @param defaultValue      default value when received value is null
     * @return received value of attribute or default value
     * @throws WTException thrown when a descriptor of the given object's type cannot be obtained.
     */
    public Object getOrDefault(WTObject object, String attributeInternal, Object defaultValue) throws WTException {
        return getValue(object, attributeInternal).orElse(defaultValue);
    }

    /**
     * Gets values of a given object multiply attributes
     *
     * @param object             object
     * @param attributeInternals internal names of attributes
     * @return list of attributes' values
     * @throws WTException thrown when a descriptor of the given object's type cannot be obtained.
     */
    public List<Object> getValues(WTObject object, String... attributeInternals) throws WTException {
        PersistableAdapter persistableAdapter = new PersistableAdapter(object, null, SessionHelper.getLocale(), null);
        persistableAdapter.load(attributeInternals);
        List<Object> values = new LinkedList<>();
        for (String attributeInternal : attributeInternals) {
            values.add(persistableAdapter.get(attributeInternal));
        }
        return values;
    }

    /**
     * Get map values of a given object
     *
     * @param object             object
     * @param attributeInternals internal names of attributes
     * @return map of attributes' internal with values
     * @throws WTException thrown when a descriptor of the given object's type cannot be obtained.
     */
    public Map<String, Object> getMapValues(WTObject object, Collection<String> attributeInternals) throws WTException {
        PersistableAdapter persistableAdapter = new PersistableAdapter(object, null, SessionHelper.getLocale(), null);
        persistableAdapter.load(attributeInternals);
        Map<String, Object> values = new HashMap<>();
        for (String attributeInternal : attributeInternals) {
            values.put(attributeInternal, persistableAdapter.get(attributeInternal));
        }
        return values;
    }

    /**
     * Gets values of a given object multiply attributes
     *
     * @param objects           list of WTObjects
     * @param attributeInternal internal name of attribute
     * @return list of attributes' values
     * @throws WTException thrown when a descriptor of the given object's type cannot be obtained.
     */
    public List<Object> getValues(List<WTObject> objects, String attributeInternal) throws WTException {
        List<Object> values = new LinkedList<>();
        Locale locale = SessionHelper.getLocale();
        for (WTObject object : objects) {
            PersistableAdapter persistableAdapter = new PersistableAdapter(object, null, locale, null);
            persistableAdapter.load(attributeInternal);
            values.add(persistableAdapter.get(attributeInternal));
        }
        return values;
    }

    /**
     * Sets attribute value for given persistable
     *
     * @param persistable    Persistable whose attribute should be updated
     * @param attributeName  name of attribute
     * @param attributeValue value of attribute
     * @throws WTException throw WTException when cannot set attribute value
     */
    public void setValue(Persistable persistable, String attributeName, Object attributeValue) throws WTException {
        PersistableAdapter persistableAdapter = new PersistableAdapter(persistable, null, SessionHelper.getLocale(), new UpdateOperationIdentifier());
        persistableAdapter.load(attributeName);
        persistableAdapter.set(attributeName, attributeValue);
        persistableAdapter.apply();
    }

    /**
     * Sets attribute value for given persistable
     *
     * @param persistable          Persistable whose attribute should be updated
     * @param attributesWithValues map with attributes with values
     * @throws WTException throw WTException when cannot set attribute value
     */
    public void setValues(Persistable persistable, Map<String, Object> attributesWithValues) throws WTException {
        PersistableAdapter persistableAdapter = new PersistableAdapter(persistable, null, SessionHelper.getLocale(), new UpdateOperationIdentifier());
        persistableAdapter.load(attributesWithValues.keySet());
        for (Entry<String, Object> entry : attributesWithValues.entrySet()) {
            persistableAdapter.set(entry.getKey(), entry.getValue());
        }
        persistableAdapter.apply();
    }
}
