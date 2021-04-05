package test.com.bmw.psmg.sbb.junit.assertion;

import static org.junit.Assert.fail;

import com.bmw.psmg.sbb.generic.PSMGSBBConstants.Types;
import com.bmw.psmg.sbb.utilities.PartBuilder;

import org.apache.log4j.Logger;
import org.assertj.core.api.AbstractAssert;
import wt.util.WTException;
import wt.vc.views.ViewHelper;

/**
 * Assertion class simplify part view tests.
 *
 * @author Karol Poliszkiewicz
 */
public class PartViewAssertion extends AbstractAssert<PartViewAssertion, String> {

    private static final Logger LOGGER = Logger.getLogger(PartViewAssertion.class.getName());

    private PartViewAssertion(String actual) {
        super(actual, PartViewAssertion.class);
    }

    /**
     * Constructor initialize part view assertion class.
     *
     * @param internalName testing part type's internal name
     * @return assertion object
     */
    public static PartViewAssertion assertThatPartView(String internalName) {
        return new PartViewAssertion(internalName);
    }

    /**
     * Method checks assignation of view to part. Test fail if it throw constraint exception.
     *
     * @param expected expected assigned view.
     */
    public void hasView(String expected) {
        try {
            new PartBuilder(Types.FLEXIBLE_ASSEMBLY_INTERNAL_NAME).view(ViewHelper.service.getView(expected)).build();
        } catch (WTException e) {
            LOGGER.error(e);
            fail("Cannot assigned '" + expected + "' view for the " + actual + " object.");
        }
    }

}
