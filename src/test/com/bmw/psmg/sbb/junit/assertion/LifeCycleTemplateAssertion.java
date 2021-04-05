package test.com.bmw.psmg.sbb.junit.assertion;

import static org.junit.Assert.fail;

import org.apache.log4j.Logger;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import test.com.bmw.psmg.sbb.datamodel.utilities.StringTestUtility;
import wt.lifecycle.LifeCycleHelper;
import wt.lifecycle.LifeCycleTemplate;
import wt.lifecycle.State;
import wt.util.WTException;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Assertion class simplify LifeCycle Template tests.
 *
 * @author Karol Poliszkiewicz
 */
public class LifeCycleTemplateAssertion extends AbstractAssert<LifeCycleTemplateAssertion, LifeCycleTemplate> {

    private static final Logger LOGGER = Logger.getLogger(LifeCycleTemplateAssertion.class.getName());
    private static final String SIZE_ASSERT_ERROR = "%s template has different size of states, expected: <%s>, but was: <%s>";

    private LifeCycleTemplateAssertion(LifeCycleTemplate actual) {
        super(actual, LifeCycleTemplateAssertion.class);
    }

    /**
     * Constructor initialize LifeCycle Template assertion class.
     *
     * @param template testing template object
     * @return assertion object
     */
    public static LifeCycleTemplateAssertion assertThatLifeCycleTemplate(LifeCycleTemplate template) {
        return new LifeCycleTemplateAssertion(template);
    }

    /**
     * Method checks existence of actual life cycle template.
     *
     * @param errorMessage error message string.
     */
    public void hasExists(String errorMessage) {
        Assertions.assertThat(actual).overridingErrorMessage(errorMessage).isNotNull();
    }

    /**
     * Method verify life cycle template states.
     *
     * @param expectedStates collection of life cycle template expected states.
     */
    public void hasSameStates(List<State> expectedStates) {
        isNotNull();
        LOGGER.debug("Starting checking Life Cycle States");

        Collection<State> states = findStates();
        Assertions.assertThat(expectedStates.size()).overridingErrorMessage(String.format(SIZE_ASSERT_ERROR, actual.getName(), expectedStates.size(), states.size())).isEqualTo(states.size());

        StringBuilder errors = new StringBuilder();
        for (State state : states) {
            if (expectedStates.stream().noneMatch(state::equals)) {
                errors.append("'").append(state.getDisplay()).append("'\n");
            }
        }
        if (!errors.toString().isEmpty()) {
            fail(String.format("Not found states:%n%s", errors.toString()));
        }
    }

    /**
     * Method verify life cycle template states' display name.
     *
     * @param displayNames collection of life cycle template states' display names.
     */
    public void hasSameStateDisplays(List<String> displayNames) {
        hasSameStateDisplays(displayNames, Locale.getDefault());
    }

    /**
     * Method verify life cycle template states' display names.
     *
     * @param displayNames collection of life cycle template states' display names.
     * @param locale       of verifying display names
     */
    public void hasSameStateDisplays(List<String> displayNames, Locale locale) {
        isNotNull();
        LOGGER.debug("Starting checking Life Cycle states' display names");

        Collection<State> states = findStates();
        Assertions.assertThat(displayNames.size()).overridingErrorMessage(String.format(SIZE_ASSERT_ERROR, actual.getName(), displayNames.size(), states.size())).isEqualTo(states.size());

        StringBuilder errors = new StringBuilder();
        for (String displayName : displayNames) {
            if (states.stream().noneMatch(state -> StringTestUtility.equalsWithUmlauts(displayName, state.getDisplay(locale)))) {
                errors.append("Not found state display name, expected:<").append(displayName).append(">\n");
            }
        }
        if (!errors.toString().isEmpty()) {
            fail(String.format("Errors with %s:%n%s", actual.getName(), errors.toString()));
        }
    }

    /**
     * Find and return collection of states for actual Life Cycle Template.
     *
     * @return collection of states for actual Life Cycle Template.
     */
    @SuppressWarnings("unchecked")
    private Collection<State> findStates() {
        LOGGER.debug("Finding states for actual Life Cycle Template.");
        try {
            return new LinkedList<>(LifeCycleHelper.service.findStates(actual));
        } catch (WTException e) {
            LOGGER.error(e);
            fail("Not found states for <" + actual.getName() + ">");
        }
        return Collections.emptyList();
    }

}
