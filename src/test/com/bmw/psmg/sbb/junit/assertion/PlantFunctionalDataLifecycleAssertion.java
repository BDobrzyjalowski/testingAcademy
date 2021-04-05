package test.com.bmw.psmg.sbb.junit.assertion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.ptc.windchill.enterprise.data.PlantFunctionalData;
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
public class PlantFunctionalDataLifecycleAssertion extends AbstractAssert<PlantFunctionalDataLifecycleAssertion, String> {

    private static final Logger LOGGER = Logger.getLogger(PlantFunctionalDataLifecycleAssertion.class.getName());
    private static final String STATE_ASSERT_ERROR = "%s - wrong set life cycle state, expected: <%s>, but was: <%s>";
    private static final String STATE_SET_ERROR = "%s - cannot set life cycle state";


    private PlantFunctionalDataLifecycleAssertion(String actual) {
        super(actual, PlantFunctionalDataLifecycleAssertion.class);
    }

    /**
     * Constructor initialize LifeCycle Template assertion class.
     *
     * @param internalName testing PlantFunctionalData type's internal name
     * @return assertion object
     */
    public static PlantFunctionalDataLifecycleAssertion assertThatLifeCycle(String internalName) {
        return new PlantFunctionalDataLifecycleAssertion(internalName);
    }

    /**
     * Asserts that list of states could be set for test PlantFunctionalData.
     *
     * @param plantFunctionalData PlantFunctionalData object
     * @param states              - life cycle states to verify
     */
    public void hasSameTransitions(PlantFunctionalData plantFunctionalData, List<State> states) {
        isNotNull();
        LOGGER.debug("Starting checking Life Cycle States Transitions");
        try {
            for (State state : states) {
                plantFunctionalData = (PlantFunctionalData) LifeCycleHelper.service.setLifeCycleState(plantFunctionalData, state);
                assertEquals(String.format(STATE_ASSERT_ERROR, actual, state, plantFunctionalData.getState().getState()), state, plantFunctionalData.getState().getState());
            }
        } catch (WTException e) {
            LOGGER.error(e);
            fail(String.format(STATE_SET_ERROR, actual));
        }
    }

    /**
     * Asserts that list of states for all transitions would be set for test PlantFunctionalData.
     *
     * @param plantFunctionalData PlantFunctionalData object
     * @param states              - life cycle states to verify
     */
    public void hasCartesianProductTransitions(PlantFunctionalData plantFunctionalData, List<State> states) {
        isNotNull();
        LOGGER.debug("Starting checking Life Cycle Cartesian Product of States Transitions for " + actual);
        try {
            for (State state : states) {
                for (State test : states) {
                    plantFunctionalData = (PlantFunctionalData) LifeCycleHelper.service.setLifeCycleState(plantFunctionalData, state);
                    plantFunctionalData = (PlantFunctionalData) LifeCycleHelper.service.setLifeCycleState(plantFunctionalData, test);
                    assertEquals(String.format(STATE_ASSERT_ERROR, actual, state, plantFunctionalData.getState().getState()), test, plantFunctionalData.getState().getState());
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
     * @param plantFunctionalData PlantFunctionalData object
     * @param template            - expected template
     * @return this assertion object
     */
    public PlantFunctionalDataLifecycleAssertion hasTemplate(PlantFunctionalData plantFunctionalData, String template) {
        assertEquals(actual + " has incorrect Lifecycle Template", template, plantFunctionalData.getLifeCycleName());
        return this;
    }

    /**
     * Assert check initial state.
     *
     * @param plantFunctionalData PlantFunctionalData object
     * @param state               - expected state
     */
    public void hasInitialState(PlantFunctionalData plantFunctionalData, State state) {
        assertEquals(actual + " has incorrect Initial State", state, plantFunctionalData.getState().getState());
    }

}

