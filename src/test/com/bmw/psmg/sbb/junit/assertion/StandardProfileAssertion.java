package test.com.bmw.psmg.sbb.junit.assertion;

import static org.junit.Assert.fail;

import com.ptc.netmarkets.model.NmOid;
import com.ptc.netmarkets.roleAccess.ProfileCommands;
import com.ptc.netmarkets.roleAccess.ProfileUIComponent;
import com.ptc.netmarkets.util.beans.NmCommandBean;
import org.apache.log4j.Logger;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import wt.inf.container.WTContainerRef;
import wt.org.StandardProfile;
import wt.org.WTPrincipal;
import wt.util.WTException;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Assertion class simplify standard profile tests.
 *
 * @author Karol Poliszkiewicz
 */
public class StandardProfileAssertion extends AbstractAssert<StandardProfileAssertion, StandardProfile> {

    private static final Logger LOGGER = Logger.getLogger(StandardProfileAssertion.class.getName());

    private StandardProfileAssertion(StandardProfile actual) {
        super(actual, StandardProfileAssertion.class);
    }

    /**
     * Constructor initialize Preference assertion class.
     *
     * @param profile unique identity key of preference
     * @return assertion object
     */
    public static StandardProfileAssertion assertThatStandardProfile(StandardProfile profile) {
        return new StandardProfileAssertion(profile);
    }

    /**
     * Method checks name of actual standard profile.
     *
     * @param expected expected name of actual preference.
     * @return assertion object
     */
    public StandardProfileAssertion hasName(String expected) {
        isNotNull();
        Assertions.assertThat(actual.getName()).overridingErrorMessage("Expected profile name is <%s>, but was <%s>.", expected, actual.getName()).isEqualTo(expected);
        return this;
    }

    /**
     * Method checks description of actual standard profile.
     *
     * @param expected expected description of actual preference.
     * @return assertion object
     */
    public StandardProfileAssertion hasDescription(String expected) {
        isNotNull();
        try {
            Assertions.assertThat(actual.getDescription()).overridingErrorMessage("Expected profile description is <%s>, but was <%s>.", expected, actual.getDescription()).isEqualTo(expected);
        } catch (WTException e) {
            String error = String.format("Cannot get description of <%s> standard profile.", actual.getName());
            LOGGER.error(error, e);
            fail(error);
        }
        return this;
    }

    /**
     * Method checks container reference of actual standard profile.
     *
     * @param expected expected container reference of actual preference.
     * @return assertion object
     */
    public StandardProfileAssertion hasContainerReference(WTContainerRef expected) {
        isNotNull();

        WTContainerRef organizationRef = null;
        try {
            LOGGER.debug("Getting organization reference");
            organizationRef = actual.getOrganization().getContainerReference();
        } catch (WTException e) {
            String error = String.format("Cannot get organization reference of <%s> standard profile.", actual.getName());
            LOGGER.error(error, e);
            fail(error);
        }

        LOGGER.debug("Comparing organization references");
        Assertions.assertThat(organizationRef).overridingErrorMessage("Expected profile container reference is <%s>, but was <%s>.", expected, organizationRef).isEqualTo(expected);
        return this;
    }

    /**
     * Method checks the actual standard profile is enabled.
     *
     * @return assertion object
     */
    public StandardProfileAssertion isEnabled() {
        isNotNull();
        Assertions.assertThat(actual.isDisabled()).overridingErrorMessage("Expected profile should be enabled, but is not.").isFalse();
        return this;
    }

    /**
     * Method checks access of actual standard profile.
     *
     * @param category expected profile access ui component category attribute
     * @param name     expected profile access ui component name attribute
     */
    @SuppressWarnings("unchecked")
    public void hasAccess(String category, String name) {
        isNotNull();

        try {
            LOGGER.debug("Start searching ProfileUIComponent");
            Optional<ProfileUIComponent> optional = getProfileComponents().stream().filter(profileUIComponent -> name.equalsIgnoreCase(profileUIComponent.getUIC())).findAny();
            if (optional.isPresent()) {
                ProfileUIComponent component = optional.get();
                LOGGER.debug("Found ProfileUIComponent: " + component);
                Boolean isEnable = component.getTrueCategory().stream().anyMatch(o -> ((String) o).equalsIgnoreCase(category));
                Assertions.assertThat(isEnable).overridingErrorMessage("Expected profile access <%s> should be enabled, but is not.", name).isTrue();
            } else {
                fail(String.format("Cannot find expected access by name <%s>", name));
            }
        } catch (WTException e) {
            String error = String.format("Cannot get access entries of <%s> standard profile.", actual.getName());
            LOGGER.error(error, e);
            fail(error);
        }
    }

    /**
     * Method checks members of actual standard profile.
     *
     * @param wtPrincipal expected profile member
     */
    public void hasMember(WTPrincipal wtPrincipal) {
        isNotNull();

        try {
            LOGGER.debug("Starting checks member <" + wtPrincipal.getName() + ">");
            Assertions.assertThat(actual.isMember(wtPrincipal)).overridingErrorMessage("Expected <%s> is not a member of standard profile <%s>.", wtPrincipal.getName(), actual.getName()).isTrue();
        } catch (WTException e) {
            String error = String.format("Cannot get member of <%s> standard profile.", actual.getName());
            LOGGER.error(error, e);
            fail(error);
        }
    }

    private ArrayList<ProfileUIComponent> getProfileComponents() throws WTException {
        NmCommandBean commandBean = new NmCommandBean();
        commandBean.setPrimaryOid(new NmOid(actual));
        return ProfileCommands.getProfileComponentList(commandBean);
    }

}
