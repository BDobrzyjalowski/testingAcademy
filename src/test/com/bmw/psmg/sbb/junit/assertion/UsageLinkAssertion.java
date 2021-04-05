package test.com.bmw.psmg.sbb.junit.assertion;

import org.apache.log4j.Logger;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import wt.part.WTPart;
import wt.part.WTPartMaster;
import wt.part.WTPartUsageLink;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Assertion class simplify usage link tests.
 *
 * @author Karol Poliszkiewicz
 */
public class UsageLinkAssertion extends AbstractAssert<UsageLinkAssertion, WTPartUsageLink> {

    private static final Logger LOGGER = Logger.getLogger(UsageLinkAssertion.class.getName());

    private UsageLinkAssertion(WTPartUsageLink actual) {
        super(actual, UsageLinkAssertion.class);
    }

    /**
     * Constructor initialize usage link assertion class.
     *
     * @param usageLink actual usage link to assert
     * @return assertion object
     */
    public static UsageLinkAssertion assertThatUsageLink(WTPartUsageLink usageLink) {
        LOGGER.debug("Create new UsageLinkAssertion object.");
        return new UsageLinkAssertion(usageLink);
    }

    /**
     * Assertion verify 'used By' role in link.
     *
     * @param expectedUsedBy expected usage link parent
     * @return assertion object
     */
    public UsageLinkAssertion isUsedBy(WTPart expectedUsedBy) {
        isNotNull();

        LOGGER.debug("Verifying 'used by' role.");
        WTPart actualUsedBy = actual.getUsedBy();
        LOGGER.debug(actualUsedBy.getName() + " used by' " + expectedUsedBy.getName());
        Assertions.assertThat(actualUsedBy).overridingErrorMessage("Expected 'Used By' role is <%s>, but was <%s>.", actualUsedBy.getNumber(), expectedUsedBy.getNumber()).isEqualTo(expectedUsedBy);

        return this;
    }

    /**
     * Assertion verify 'uses' role in link.
     *
     * @param children list of usage link children
     * @return assertion object
     */
    public UsageLinkAssertion usesAny(Collection<WTPart> children) {
        isNotNull();

        LOGGER.debug("Verifying 'uses' role.");
        WTPartMaster actualUses = actual.getUses();
        List<WTPartMaster> masters = children.stream().map(WTPart::getMaster).collect(Collectors.toList());
        LOGGER.debug(actualUses + " uses " + Arrays.toString(children.toArray()));

        Assertions.assertThat(actualUses).overridingErrorMessage("Expected 'Uses' role is <%s> not found in <%s>.", actualUses.getNumber(), Arrays.toString(masters.toArray())).isIn(masters);

        return this;
    }

}
