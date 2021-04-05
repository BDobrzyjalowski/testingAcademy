package test.com.bmw.psmg.sbb.junit.assertion;

import com.bmw.psmg.sbb.generic.PSMGSBBSoftTypeUtility;
import com.bmw.psmg.sbb.utilities.ExpressionUtils;

import com.ptc.windchill.option.model.ExpressionAlias;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import wt.inf.container.WTContainerRef;

import java.util.Optional;

/**
 * Assertion class simplify Expression Alias tests.
 *
 * @author Karol Poliszkiewicz
 */
public class ExpressionAliasAssertion extends AbstractAssert<ExpressionAliasAssertion, ExpressionAlias> {

    private ExpressionAliasAssertion(ExpressionAlias actual) {
        super(actual, ExpressionAliasAssertion.class);
    }

    /**
     * Constructor initialize Expression Alias assertion class.
     *
     * @param expressionAlias testing Expression Alias
     * @return assertion object
     */
    public static ExpressionAliasAssertion assertThatExpressionAlias(ExpressionAlias expressionAlias) {
        return new ExpressionAliasAssertion(expressionAlias);
    }

    /**
     * Method checks existence of actual Expression Alias.
     *
     * @param errorMessage error message value
     * @return assertion object
     */
    public ExpressionAliasAssertion hasExists(String errorMessage) {
        isNotNull();
        Assertions.assertThat(actual).overridingErrorMessage(errorMessage).isNotNull();
        return this;
    }

    /**
     * Method compares actual Expression Alias type definition with expected value.
     *
     * @param expected value of expected type definition
     * @return assertion object
     */
    public ExpressionAliasAssertion hasTypeDefinition(String expected) {
        isNotNull();
        String actualType = PSMGSBBSoftTypeUtility.getTypeString(actual, false);
        String errorMessage = String.format("Expected alias expression has the incorrect type. Expected: '%s', Actual: '%s'.", expected, actualType);
        Assertions.assertThat(actualType).overridingErrorMessage(errorMessage).isEqualTo(expected);
        return this;
    }

    /**
     * Method compares actual Expression Alias number with expected value.
     *
     * @param expected value of expected number
     * @return assertion object
     */
    public ExpressionAliasAssertion hasNumber(String expected) {
        isNotNull();
        String number = actual.getNumber();
        String errorMessage = String.format("Expected alias expression has the incorrect number. Expected: '%s', Actual: '%s'.", expected, number);
        Assertions.assertThat(number).overridingErrorMessage(errorMessage).isEqualTo(expected);
        return this;
    }

    /**
     * Method compares actual Expression Alias name with expected value.
     *
     * @param expected value of expected name
     * @return assertion object
     */
    public ExpressionAliasAssertion hasName(String expected) {
        isNotNull();
        String name = actual.getName();
        String errorMessage = String.format("Expected alias expression has the incorrect name. Expected: '%s', Actual: '%s'.", expected, name);
        Assertions.assertThat(name).overridingErrorMessage(errorMessage).isEqualTo(expected);
        return this;
    }

    /**
     * Method compares actual Expression Alias description with expected value.
     *
     * @param expected value of expected description
     * @return assertion object
     */
    public ExpressionAliasAssertion hasDescription(String expected) {
        isNotNull();
        String description = actual.getDescription();
        String errorMessage = String.format("Expected alias expression has the incorrect description. Expected: '%s', Actual: '%s'.", expected, description);
        Assertions.assertThat(description).overridingErrorMessage(errorMessage).isEqualTo(expected);
        return this;
    }

    /**
     * Method compares actual Expression Alias expression with expected value.
     *
     * @param expected       value of expected expression
     * @param wtContainerRef WTContainer reference
     */
    public void hasExpression(String expected, WTContainerRef wtContainerRef) {
        isNotNull();
        Optional<String> expression = ExpressionUtils.getDisplayExpression(actual, wtContainerRef);
        if (expression.isPresent()) {
            String actual = expression.get();
            String errorMessage = String.format("Expected alias expression has the incorrect expression definition. Expected: '%s', Actual: '%s'.", expected, actual);
            Assertions.assertThat(actual).overridingErrorMessage(errorMessage).isEqualTo(expected);
        } else {
            failWithMessage("Cannot get display expression value of alias expression", expression);
        }
    }

}
