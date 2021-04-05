package test.com.bmw.psmg.sbb.junit.assertion;

import org.apache.log4j.Logger;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import test.com.bmw.psmg.sbb.datamodel.utilities.TypeDefinitionAdapter;

import java.util.Locale;

/**
 * Assertion class simplify type definition tests.
 *
 * @author Karol Poliszkiewicz
 */
public class TypeDefinitionAssertion extends AbstractAssert<TypeDefinitionAssertion, TypeDefinitionAdapter> {

    private static final Logger LOGGER = Logger.getLogger(TypeDefinitionAssertion.class.getName());

    private TypeDefinitionAssertion(TypeDefinitionAdapter actual) {
        super(actual, TypeDefinitionAssertion.class);
    }

    /**
     * Constructor initialize type definition assertion class.
     *
     * @param typeDefinitionAdapter type definition adapter fo testing object's type definition
     * @return assertion object
     */
    public static TypeDefinitionAssertion assertThatTypeDefinition(TypeDefinitionAdapter typeDefinitionAdapter) {
        return new TypeDefinitionAssertion(typeDefinitionAdapter);
    }

    /**
     * Method checks existence of actual type definition.
     *
     * @param errorMessage error message string.
     * @return assertion object
     */
    public TypeDefinitionAssertion hasExists(String errorMessage) {
        LOGGER.debug("Starting checking type definition has exists");
        Assertions.assertThat(actual).overridingErrorMessage(errorMessage).isNotNull();
        return this;
    }

    /**
     * Method checks instantiable of actual type definition.
     *
     * @param expected instantiable expected value
     * @return assertion object
     */
    public TypeDefinitionAssertion isInstantiable(boolean expected) {
        isNotNull();
        LOGGER.debug("Starting checking type definition is Instantiable");
        Assertions.assertThat(actual.isInstantiable()).overridingErrorMessage(String.format("Incorrect Instantiable, expected:<%s>, but was:<%s>", expected, actual.isInstantiable())).isEqualTo(expected);
        return this;
    }

    /**
     * Method checks display name of actual type definition.
     *
     * @param displayName expected display name value
     * @return assertion object
     */
    public TypeDefinitionAssertion hasDisplayName(String displayName) {
        return hasDisplayName(displayName, Locale.getDefault());
    }

    /**
     * Method checks display name of actual type definition.
     *
     * @param displayName expected display name value
     * @param locale      display name localization
     * @return assertion object
     */
    public TypeDefinitionAssertion hasDisplayName(String displayName, Locale locale) {
        isNotNull();
        Assertions.assertThat(actual.getDisplayName(locale)).overridingErrorMessage(String.format("Incorrect Display name, expected:<%s>, but was:<%s>", displayName, actual.getDisplayName(locale))).isEqualTo(displayName);
        return this;
    }

    /**
     * Method checks icon path of actual type definition.
     *
     * @param expected icon path expected value
     * @return assertion object
     */
    public TypeDefinitionAssertion hasIconPath(String expected) {
        isNotNull();
        Assertions.assertThat(actual.getIcon()).overridingErrorMessage(String.format("Incorrect Icon path, expected:<%s>, but was:<%s>", expected, actual.getIcon())).isEqualTo(expected);
        return this;
    }

    /**
     * Method checks desciption of actual type definition
     *
     * @param description expected desciption
     * @param locale      display name location
     * @return assertion object
     */
    public TypeDefinitionAssertion hasDescription(String description, Locale locale) {
        isNotNull();
        Assertions.assertThat(actual.getDescription(locale)).overridingErrorMessage("Incorrect description, expected:<%s>, but was:<%s>", description, actual.getDescription(locale)).isEqualTo(description);
        return this;
    }
}
