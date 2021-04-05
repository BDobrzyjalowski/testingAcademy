package test.com.bmw.psmg.sbb.datamodel.utilities;

import static java.util.Objects.requireNonNull;

import com.ptc.core.lwc.common.view.TypeDefinitionReadView;
import com.ptc.core.lwc.server.TypeDefinitionServiceHelper;
import org.apache.log4j.Logger;
import wt.part.WTPart;
import wt.type.TypedUtility;
import wt.util.WTException;

import java.util.Locale;

/**
 * The class simplify getting TypeDefinition information of WTPart object.
 *
 * @author Karol Poliszkiewicz
 */
public class TypeDefinitionAdapter {

    private static final Logger LOGGER = Logger.getLogger(TypeDefinitionAdapter.class.getName());
    private static final String DISPLAY_NAME_PROPERTY = "displayName";
    private static final String ICON_PROPERTY = "icon";
    private static final String INSTANTIABLE_PROPERTY = "instantiable";
    private static final String DESCRIPTION_PROPERTY = "description";

    private final TypeDefinitionReadView typeDefinitionReadView;

    /**
     * Creates Type Definition Read View of the searched WTPart.
     *
     * @param part identity of searched definition type
     * @throws WTException when definition type not found by part identity
     */
    public TypeDefinitionAdapter(WTPart part) throws WTException {
        requireNonNull(part, "WTPart cannot be null.");
        LOGGER.debug("Getting TypeDefinitionReadView from WTPart: '" + part.getIdentity() + '\'');
        typeDefinitionReadView = TypeDefinitionServiceHelper.service.getTypeDefView(TypedUtility.getTypeIdentifier(part));
    }

    /**
     * Creates Type Definition Read View of the searched Internal Name.
     *
     * @param internalName of searched definition type
     * @throws WTException when definition type not found by internalName
     */
    public TypeDefinitionAdapter(String internalName) throws WTException {
        LOGGER.debug("Getting TypeDefinitionReadView from InternalName: '" + internalName + '\'');
        typeDefinitionReadView = TypeDefinitionServiceHelper.service.getTypeDefView(TypedUtility.getTypeIdentifier(internalName));
    }

    /**
     * @return DisplayName property from Type Definition view.
     */
    public String getDisplayName() {
        return typeDefinitionReadView.getPropertyValueByName(DISPLAY_NAME_PROPERTY).getValueAsString();
    }

    /**
     * Return Display Name for not default locale
     *
     * @param locale using to get DisplayName for specific language
     * @return DisplayName property from Type Definition view.
     */
    public String getDisplayName(Locale locale) {
        return typeDefinitionReadView.getPropertyValueByName(DISPLAY_NAME_PROPERTY).getValueAsString(locale, true);
    }

    /**
     * @return Icon property from Type Definition view.
     */
    public String getIcon() {
        return typeDefinitionReadView.getPropertyValueByName(ICON_PROPERTY).getValueAsString();
    }

    /**
     * @return Instantiable property from Type Definition view.
     */
    public boolean isInstantiable() {
        return (Boolean) typeDefinitionReadView.getPropertyValueByName(INSTANTIABLE_PROPERTY).getValue();
    }

    /**
     * Return Descriptionfor not default locale
     *
     * @param locale using to get Description for specific language
     * @return Description1 property from Type Definition view
     */
    public String getDescription(Locale locale) {
        return typeDefinitionReadView.getPropertyValueByName(DESCRIPTION_PROPERTY).getValueAsString(locale, true);
    }
}
