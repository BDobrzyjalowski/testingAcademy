package test.com.bmw.psmg.sbb.views;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import test.com.bmw.psmg.sbb.junit.parameter.ParametersCsvReader;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import wt.util.WTException;
import wt.vc.views.ViewHelper;

import java.util.Collection;

/**
 * Class verify existence of required Views in the Windchill.
 *
 * @author Karol Poliszkiewicz
 */
@RunWith(JUnitParamsRunner.class)
public class ViewsSmokeTest {

    private static final Logger LOGGER = Logger.getLogger(ViewsSmokeTest.class.getName());
    private static final String VIEW_PARAMETERS = "viewsTestParameters.csv";
    private static final String US_NUMBER = "PSMGSBB-187";

    public static final String PARAMETERS_METHOD = "parametersForViews";

    /**
     * Method load parameters from {@link #VIEW_PARAMETERS} file.
     *
     * @return Collection of view test parameters
     */
    @SuppressWarnings("unused")
    private Collection<String> parametersForViews() {
        return new ParametersCsvReader<String>(this.getClass().getResourceAsStream(VIEW_PARAMETERS)).map(r -> r.get(0)).read();
    }

    /**
     * This method verify existence Views in Windchill.
     *
     * @param viewName determine applied view
     */
    @Test
    @Parameters(method = PARAMETERS_METHOD)
    public void testFailIfViewNotExistence(String viewName) {
        LOGGER.info("Testing '" + viewName + "' View.");
        try {
            assertNotNull(ViewHelper.service.getView(viewName));
        } catch (NullPointerException | WTException e) {
            LOGGER.error(e);
            fail(US_NUMBER + " - Not found '" + viewName + "' View.");
        }
    }

}
