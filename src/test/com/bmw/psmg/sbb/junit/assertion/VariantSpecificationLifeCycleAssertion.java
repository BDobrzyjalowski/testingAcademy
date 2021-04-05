package test.com.bmw.psmg.sbb.junit.assertion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.ptc.wpcfg.doc.VariantSpec;
import org.apache.log4j.Logger;
import org.assertj.core.api.AbstractAssert;
import wt.lifecycle.LifeCycleHelper;
import wt.lifecycle.State;
import wt.util.WTException;

import java.util.List;

/**
 * Assertion class simplify LifeCycle tests.
 *
 * @author Patryk Zajko
 */
public class VariantSpecificationLifeCycleAssertion extends AbstractAssert<VariantSpecificationLifeCycleAssertion, String> {

    private static final Logger LOGGER = Logger.getLogger(VariantSpecificationLifeCycleAssertion.class.getName());
    private static final String STATE_ASSERT_ERROR = "%s - wrong set life cycle state, expected: <%s>, but was: <%s>";
    private static final String STATE_SET_ERROR = "%s - cannot set life cycle state";

    private VariantSpecificationLifeCycleAssertion(String actual) {
        super(actual, VariantSpecificationLifeCycleAssertion.class);
    }

    /**
     * Constructor initialize LifeCycle Template assertion class.
     *
     * @param internalName testing part type's internal name
     * @return assertion object
     */
    public static VariantSpecificationLifeCycleAssertion assertThatLifeCycle(String internalName) {
        return new VariantSpecificationLifeCycleAssertion(internalName);
    }

    /**
     * Asserts that list of states could be set for test VariantSpecification.
     * @param variantSpecification VariantSpec object
     * @param states - life cycle states to verify
     */
    public void hasSameTransitions(VariantSpec variantSpecification, List<State> states) {
        isNotNull();
        LOGGER.debug("Starting checking Life Cycle States Transitions");
        try {
            for (State state : states) {
                variantSpecification = (VariantSpec) LifeCycleHelper.service.setLifeCycleState(variantSpecification, state, true);
                assertEquals(String.format(STATE_ASSERT_ERROR, actual, state, variantSpecification.getState().getState()), state, variantSpecification.getState().getState());
            }
        } catch (WTException e) {
            LOGGER.error(e);
            fail(String.format(STATE_SET_ERROR, actual));
        }
    }

    /**
     * Asserts that list of states for all transitions would be set for test variantspecification.
     *
     * @param variantSpecification VariantSpec object
     * @param states - life cycle states to verify
     */
    public void hasCartesianProductTransitions(VariantSpec variantSpecification, List<State> states) {
        isNotNull();
        LOGGER.debug("Starting checking Life Cycle Cartesian Product of States Transitions for " + actual);
        try {
            for (State state : states) {
                for (State test : states) {
                    variantSpecification = (VariantSpec) LifeCycleHelper.service.setLifeCycleState(variantSpecification, state, true);
                    variantSpecification = (VariantSpec) LifeCycleHelper.service.setLifeCycleState(variantSpecification, test, true);
                    assertEquals(String.format(STATE_ASSERT_ERROR, actual, state, variantSpecification.getState().getState()), test, variantSpecification.getState().getState());
                }
            }
        } catch (WTException e) {
            LOGGER.error(e);
            fail(String.format(STATE_SET_ERROR, actual));
        }
    }

    /**
     * Assert check assigned Life Cycle Template.
     *
     * @param variantSpecification VariantSpec object
     * @param template - expected template
     * @return this assertion object
     */
    public VariantSpecificationLifeCycleAssertion hasTemplate(VariantSpec variantSpecification, String template) {
        assertEquals(actual + " has incorrect Lifecycle Template", template, variantSpecification.getLifeCycleName());
        return this;
    }

    /**
     * Assert check initial state.
     *
     * @param variantSpecification VariantSpec object
     * @param state - expected state
     */
    public void hasInitialState(VariantSpec variantSpecification, State state) {
        assertEquals(actual + " has incorrect Initial State", state, variantSpecification.getState().getState());
    }
}
