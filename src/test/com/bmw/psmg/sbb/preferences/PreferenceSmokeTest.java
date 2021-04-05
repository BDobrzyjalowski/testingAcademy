package test.com.bmw.psmg.sbb.preferences;

import static test.com.bmw.psmg.sbb.junit.assertion.PreferenceAssertion.assertThatPreference;

import com.ptc.test.core.NestedRunWith;
import com.ptc.test.remote.Server;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import test.com.bmw.psmg.sbb.junit.RollbackRule;
import test.com.bmw.psmg.sbb.junit.parameter.ParametersCsvReader;

import java.util.Collection;

/**
 * Class responsible for testing preferences
 *
 * @author Pawel Miron
 */
@RunWith(Server.class)
@NestedRunWith(JUnitParamsRunner.class)
public class PreferenceSmokeTest {

    private static final Logger LOGGER = Logger.getLogger(PreferenceSmokeTest.class.getName());
    private static final String PREFERENCE_PARAMETERS = "preferenceParameters.csv";

    /**
     * Injects JUnit custom rule, which rollback all changes makes after each test.
     */
    @Rule
    public RollbackRule rollbackRule = new RollbackRule();

    /**
     * Method load parameters from {@link #PREFERENCE_PARAMETERS} file.
     *
     * @return Collection of test parameters
     */
    @SuppressWarnings("unused")
    private Collection<Object[]> parametersForCheckingPreference() {
        return new ParametersCsvReader<Object[]>(this.getClass().getResourceAsStream(PREFERENCE_PARAMETERS)).map(r -> new Object[]{r.get(0), r.get(1)}).read();
    }

    /**
     * Method check if expected preference value is equal to value got from Windchill
     *
     * @param internalName internal name of preference
     * @param value        expected value of preference
     */
    @Test
    @Parameters(method = "parametersForCheckingPreference")
    public void testPreference(String internalName, Object value) {
        LOGGER.debug("Testing '" + internalName + "' preference.");
        String wrongValueMsg = "Preference %s has wrong value";
        if (isBoolean(value)) {
            assertThatPreference(internalName).hasValue(String.format(wrongValueMsg, internalName), Boolean.valueOf(value.toString()));
        } else if (isInteger(value)){
            assertThatPreference(internalName).hasValue(String.format(wrongValueMsg, internalName), Integer.parseInt(value.toString()));
        } else {
            assertThatPreference(internalName).hasValue(String.format(wrongValueMsg, internalName), value);
        }
    }

    private boolean isBoolean(Object value) {
        return "true".equalsIgnoreCase(value.toString()) || "false".equalsIgnoreCase(value.toString());
    }


    private boolean isInteger(Object value) {
        try {
            Integer.parseInt(value.toString());
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

}
