package test.com.bmw.psmg.sbb.junit.assertion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.bmw.psmg.sbb.generic.PSMGSBBConstants.LifeCycles;

import org.apache.log4j.Logger;
import org.assertj.core.api.AbstractAssert;
import wt.lifecycle.LifeCycleHelper;
import wt.lifecycle.LifeCycleTemplateReference;
import wt.lifecycle.State;
import wt.part.WTPart;
import wt.util.WTException;

import java.util.List;

/**
 * Assertion class simplify LifeCycle tests.
 *
 * @author Karol Poliszkiewicz
 */
public class PartLifeCycleAssertion extends AbstractAssert<PartLifeCycleAssertion, WTPart> {

    private static final Logger LOGGER = Logger.getLogger(PartLifeCycleAssertion.class.getName());
    private static final String STATE_ASSERT_ERROR = "%s - wrong set life cycle state, expected: <%s>, but was: <%s>";
    private static final String STATE_SET_ERROR = "%s - cannot set life cycle state";

    private PartLifeCycleAssertion(WTPart actual) {
        super(actual, PartLifeCycleAssertion.class);
    }

    /**
     * Constructor initialize LifeCycle Template assertion class.
     *
     * @param part testing part
     * @return assertion object
     */
    public static PartLifeCycleAssertion assertThatLifeCycle(WTPart part) {
        return new PartLifeCycleAssertion(part);
    }

    /**
     * Asserts that list of states could be set for test part.
     *
     * @param states - life cycle states to verify
     */
    public void hasSameTransitions(List<State> states) {
        isNotNull();
        LOGGER.debug("Starting checking Life Cycle States Transitions");
        try {
            WTPart testPart = actual;
            for (State state : states) {
                testPart = (WTPart) LifeCycleHelper.service.setLifeCycleState(testPart, state, true);
                assertEquals(String.format(STATE_ASSERT_ERROR, actual, state, testPart.getState().getState()), state, testPart.getState().getState());
            }
        } catch (WTException e) {
            LOGGER.error(e);
            fail(String.format(STATE_SET_ERROR, actual));
        }
    }

    /**
     * Asserts that list of states for all transitions would be set for test part.
     *
     * @param states - life cycle states to verify
     */
    public void hasCartesianProductTransitions(List<State> states) {
        isNotNull();
        LOGGER.debug("Starting checking Life Cycle Cartesian Product of States Transitions for " + actual);
        try {
            WTPart testPart = actual;
            for (State state : states) {
                for (State test : states) {
                    testPart = (WTPart) LifeCycleHelper.service.setLifeCycleState(testPart, state, true);
                    testPart = (WTPart) LifeCycleHelper.service.setLifeCycleState(testPart, test, true);
                    assertEquals(String.format(STATE_ASSERT_ERROR, actual, state, testPart.getState().getState()), test, testPart.getState().getState());
                }
            }
        } catch (WTException e) {
            LOGGER.error(e);
            fail(String.format(STATE_SET_ERROR, actual));
        }
    }

    /**
     * Asserts verify reassign Life Cycle Template.
     *
     * @param templateName - destination life cycle template name
     */
    public void canReassign(String templateName) {
        isNotNull();
        LOGGER.debug("Starting reassign Life Cycle Template for " + actual);
        try {
            WTPart testPart = actual;
            LifeCycleTemplateReference basicReference = LifeCycleHelper.service.getLifeCycleTemplateReference(templateName);
            LifeCycleHelper.service.reassign(testPart, basicReference);
            assertEquals(actual + " cannot reassign Lifecycle template.", LifeCycles.TWO_PHASE_DEVELOPMENT, testPart.getLifeCycleName());
        } catch (WTException e) {
            LOGGER.error(e);
            fail(String.format(STATE_SET_ERROR, actual));
        }
    }

    /**
     * Assert check assigned Life Cycle Template.
     *
     * @param template - expected template
     * @return this assertion object
     */
    public PartLifeCycleAssertion hasTemplate(String template) {
        assertEquals(actual + " has incorrect Lifecycle Template", template, actual.getLifeCycleName());
        return this;
    }

    /**
     * Assert check initial state.
     *
     * @param state - expected state
     */
    public void hasInitialState(State state) {
        assertEquals(actual + " has incorrect Initial State", state, actual.getState().getState());
    }
}
