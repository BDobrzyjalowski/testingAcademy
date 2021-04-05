package com.bmw.psmg.sbb.generic.persistable;

import com.google.inject.Provides;

/**
 * Persistable module configuration class.
 *
 * @author Karol Poliszkiewicz
 */
public class PersistableModule {

    /**
     * Attribute service provider.
     *
     * @return instance of attribute service
     */
    @Provides
    public AttributeService getAttributeService() {
        return new AttributeService();
    }
}
