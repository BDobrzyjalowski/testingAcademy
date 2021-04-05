package test.com.bmw.psmg.sbb.junit.assertion;

import static org.junit.Assert.fail;

import org.apache.log4j.Logger;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import wt.preference.PreferenceClient;
import wt.preference.PreferenceHelper;
import wt.util.WTException;

/**
 * Assertion class simplify preference tests.
 *
 * @author Karol Poliszkiewicz
 */
public class PreferenceAssertion extends AbstractAssert<PreferenceAssertion, String> {

    private static final Logger LOGGER = Logger.getLogger(PreferenceAssertion.class.getName());

    private PreferenceAssertion(String actual) {
        super(actual, PreferenceAssertion.class);
    }

    /**
     * Constructor initialize Preference assertion class.
     *
     * @param preferenceKey unique identity key of preference
     * @return assertion object
     */
    public static PreferenceAssertion assertThatPreference(String preferenceKey) {
        return new PreferenceAssertion(preferenceKey);
    }

    /**
     * Method checks value of actual preference.
     *
     * @param errorMessage error message string.
     * @param expected     expected value of actual preference.
     */
    public void hasValue(String errorMessage, Object expected) {
        isNotNull();

        try {
            Object value = PreferenceHelper.service.getValue(actual, PreferenceClient.WINDCHILL_CLIENT_NAME);
            Assertions.assertThat(value).overridingErrorMessage("Expected preference value is <%s>, but was <%s>.", expected, value).isEqualTo(expected);
        } catch (WTException e) {
            LOGGER.error("Cannot found <" + actual + "> preference.", e);
            fail(errorMessage);
        }
    }

}
