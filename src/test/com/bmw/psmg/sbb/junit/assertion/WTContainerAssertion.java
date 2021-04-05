package test.com.bmw.psmg.sbb.junit.assertion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.ptc.windchill.baseserver.logger.BaseLogger;
import com.ptc.windchill.option.model.OptionSet;
import com.ptc.windchill.option.service.OptionHelper;
import org.assertj.core.api.AbstractAssert;
import wt.fc.ObjectReference;
import wt.fc.QueryResult;
import wt.fc.collections.WTCollection;
import wt.folder.FolderHelper;
import wt.folder.SubFolder;
import wt.folder.SubFolderReference;
import wt.inf.container.WTContainer;
import wt.inf.container.WTContainerRef;
import wt.inf.container.WTContainerTemplate;
import wt.inf.team.ContainerTeam;
import wt.inf.team.ContainerTeamHelper;
import wt.inf.team.ContainerTeamManaged;
import wt.org.WTGroup;
import wt.org.WTPrincipal;
import wt.org.WTPrincipalReference;
import wt.project.Role;
import wt.util.WTException;
import wt.util.WTPropertyVetoException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Assertion class to simplify WTContainer test cases.
 *
 * @author Karol Poliszkiewicz
 */
public class WTContainerAssertion extends AbstractAssert<WTContainerAssertion, WTContainer> {

    private static final BaseLogger LOGGER = BaseLogger.getLogger(WTContainerAssertion.class.getName());

    private final String name;

    private WTContainerAssertion(WTContainer actual) {
        super(actual, WTContainerAssertion.class);
        this.name = actual.getName();
    }

    /**
     * Constructor initialize Container assertion class.
     *
     * @param container testing Container
     * @return assertion object
     */
    public static WTContainerAssertion assertThat(WTContainer container) {
        return new WTContainerAssertion(container);
    }

    /**
     * Method compare existing container folders with expected folders.
     *
     * @param expectedFolderPaths collection of expected actual container's folders
     */
    public void hasFolders(Set<String> expectedFolderPaths) {
        isNotNull();
        Set<String> errors = new HashSet<>();
        try {
            LOGGER.debug(() -> String.format("Getting folders for container: %s", name));
            Set<String> actualFolderPaths = fetchFolderPaths();
            expectedFolderPaths.stream().filter(folder -> !actualFolderPaths.contains(folder)).forEach(errors::add);
        } catch (WTException e) {
            LOGGER.error(e::getLocalizedMessage, e);
            fail(String.format("Cannot load <%s> container folders.", name));
        }
        if (!errors.isEmpty()) {
            fail(String.format("Found some problems with %s's folders: %s", name, String.join(",", errors)));
        }
    }

    private Set<String> fetchFolderPaths() throws WTException {
        Set<String> folderPaths = new HashSet<>();
        WTCollection descendantFolders = FolderHelper.service.getAllDescendantFolders(actual.getDefaultCabinetReference());
        for (Object descendantFolder : descendantFolders) {
            SubFolderReference subFolderReference = (SubFolderReference) descendantFolder;
            SubFolder subFolder = (SubFolder) subFolderReference.getObject();
            folderPaths.add(subFolder.getFolderPath());
        }
        LOGGER.debug(() -> String.format("Loaded container folders: %s", Arrays.toString(folderPaths.toArray())));
        return folderPaths;
    }

    /**
     * Method checks template name in actual container.
     *
     * @param templateName name of expected template.
     */
    public void hasTemplate(String templateName) {
        LOGGER.debug(() -> "Getting template of container: " + name);
        WTContainerTemplate containerTemplate = actual.getContainerTemplate();
        assertNotNull(String.format("%s container's template is null.", name), containerTemplate);
        assertEquals(String.format("%s container don't use template: <%s>", name, templateName), containerTemplate.getName(), templateName);
    }

    /**
     * Method verifies count of Option Sets in container, should be there only one the Option Set.
     *
     * @param expectedOptionSet name of expected OptionSet
     */
    public void hasOptionSet(String expectedOptionSet) {
        try {
            LOGGER.debug(() -> "Getting option sets from OptionHelper.service.listOptionSets");
            Set<String> optionSetNames = fetchOptionSets().stream().map(OptionSet::getName).collect(Collectors.toUnmodifiableSet());
            assertTrue(String.format("%s container has not any options set where name is %s.", name, expectedOptionSet), optionSetNames.contains(expectedOptionSet));
        } catch (WTException e) {
            LOGGER.error(e::getLocalizedMessage, e);
            fail("Cannot fetch Options Sets. Test aborted.");
        }
    }

    private List<OptionSet> fetchOptionSets() throws WTException {
        List<OptionSet> optionSets = new LinkedList<>();
        QueryResult queryResult = OptionHelper.service.listOptionSets(WTContainerRef.newWTContainerRef(actual));
        while (queryResult.hasMoreElements()) {
            optionSets.add((OptionSet) queryResult.nextElement());
        }
        return optionSets;
    }

    /**
     * Method verifies registered Option Sets in expected container.
     *
     * @param expectedOptionSet name of expected OptionSet
     */
    public void hasRegisteredOptionSet(String expectedOptionSet) {
        try {
            LOGGER.debug(() -> "Getting option set from " + name);
            OptionSet optionSet = OptionHelper.service.getAssignedOptionSet(ObjectReference.newObjectReference(actual), null).getOptionSet();
            assertNotNull("Not found any Option Set in container: <" + name + '>', optionSet);
            assertEquals("Option set has incorrect name in the '" + name + "' container, ", expectedOptionSet, optionSet.getName());
        } catch (WTException e) {
            LOGGER.error(e::getLocalizedMessage, e);
            fail("Cannot fetch assigned Options Sets. Test aborted.");
        }
    }



    /**
     * Method checks principals team of actual container.
     *
     * @param principals collection of expected principals.
     */
    public void hasSamePrincipals(Map<String, String> principals) {
        Set<String> errors = new HashSet<>();
        try {
            LOGGER.debug(() -> "Getting team of container: " + name);
            ContainerTeam containerTeam = ContainerTeamHelper.service.getContainerTeam((ContainerTeamManaged) actual);
            Map<WTPrincipalReference, List<Role>> teamMembers = fetchTeamMembers(containerTeam);
            for (Map.Entry<String, String> entry : principals.entrySet()) {
                String expectedPrincipal = entry.getKey();
                Role expectedRole = Role.toRole(entry.getValue());
                if (!hasTeamMember(teamMembers, expectedPrincipal, expectedRole)) {
                    errors.add(expectedPrincipal);
                }
            }
        } catch (WTException e) {
            LOGGER.error(e::getLocalizedMessage, e);
            fail(String.format("Cannot fetch container data where name is <%s>.", name));
        }
        if (!errors.isEmpty()) {
            fail(String.format("These principals should be in the team of %s:%n%s", name, String.join(",", errors)));
        }
    }

    @SuppressWarnings("unchecked")
    private Map<WTPrincipalReference, List<Role>> fetchTeamMembers(ContainerTeam containerTeam) throws WTException {
        return containerTeam.getAllMembers();
    }

    private boolean hasTeamMember(Map<WTPrincipalReference, List<Role>> teamMembers, String expectedUser, Role expectedRole) throws WTException {
        return teamMembers.entrySet().stream().filter(e -> expectedUser.equals(e.getKey().getName())).anyMatch(e -> anyRole(expectedRole, e.getValue()));
    }

    private boolean anyRole(Role expectedRole, List<Role> roles) {
        return roles.stream().anyMatch(expectedRole::equals);
    }
}
