package test.com.bmw.psmg.sbb.datamodel.utilities;

import com.ptc.core.lwc.common.LayoutProperty;
import com.ptc.core.lwc.common.view.GroupDefinitionReadView;
import com.ptc.core.lwc.common.view.GroupMembershipReadView;
import com.ptc.core.lwc.common.view.LayoutComponentReadView;
import com.ptc.core.lwc.common.view.LayoutDefinitionReadView;
import com.ptc.core.lwc.common.view.TypeDefinitionReadView;
import com.ptc.core.lwc.server.TypeDefinitionServiceHelper;
import com.ptc.core.meta.common.TypeIdentifierHelper;
import org.apache.log4j.Logger;
import wt.util.WTException;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

/**
 * The class simplify testing visibility of attributes.
 *
 * @author Bartlomiej Dobrzyjalowski
 */
public class LayoutsTestUtility {

    private static final Logger LOGGER = Logger.getLogger(LayoutsTestUtility.class.getName());

    private LayoutsTestUtility() {
    }

    private static Set<String> retrieveAttributesFromLayoutForType(String type, String layoutName, String groupName) throws WTException {
        Optional<LayoutDefinitionReadView> layout = getLayoutFromType(type, layoutName);
        if (layout.isPresent()) {
            return getGroupMembersFromLayout(layout.get(), groupName);
        }
        return new HashSet<>();
    }

    private static Optional<LayoutComponentReadView> getGroupFromLayout(LayoutDefinitionReadView layout, String groupName) {
        Collection<LayoutComponentReadView> allLayoutComponents = layout.getAllLayoutComponents();
        for (LayoutComponentReadView component : allLayoutComponents) {
            LayoutProperty displayName1 = component.getProperty("displayName");
            String displayName = displayName1.getValue().toString();
            if (groupName.equals(displayName)) {
                return Optional.of(component);
            }
        }
        LOGGER.error("Group with name " + groupName + "not found");
        return Optional.empty();
    }

    private static Set<String> getGroupMembersFromLayout(LayoutDefinitionReadView layout, String groupName) {
        Set<String> layoutAttributes = new HashSet<>();
        Optional<LayoutComponentReadView> group = getGroupFromLayout(layout, groupName);
        if (group.isPresent()) {
            layoutAttributes.addAll(getAllMembersFromLayoutDefinitionReadView(group.get()));
        }
        return layoutAttributes;
    }

    private static Optional<LayoutDefinitionReadView> getLayoutFromType(String typeInternalName, String layoutName) throws WTException {
        TypeDefinitionReadView typeDefReadView = TypeDefinitionServiceHelper.service.getTypeDefView(TypeIdentifierHelper.getTypeIdentifier(typeInternalName));
        if (typeDefReadView != null) {
            Collection<LayoutDefinitionReadView> allLayouts = typeDefReadView.getAllLayouts();
            for (LayoutDefinitionReadView layout : allLayouts) {
                if (layout.getName().equals(layoutName)) {
                    return Optional.of(layout);
                }
            }
        }
        LOGGER.error("Layout with name " + layoutName + "not found");
        return Optional.empty();
    }

    /**
     * The method return names of all members of Layout
     * @param layoutComponentReadView Layout object
     * @return Set of names of all members of layout
     */
    public static Set<String> getAllMembersFromLayoutDefinitionReadView(LayoutComponentReadView layoutComponentReadView) {
        Set<String> layoutAttributes = new HashSet<>();
        List<GroupMembershipReadView> allMembers = ((GroupDefinitionReadView) layoutComponentReadView).getAllMembers();
        for (GroupMembershipReadView groupMember : allMembers) {
            layoutAttributes.add(groupMember.getName());
        }
        return layoutAttributes;
    }

    private static boolean isAttributeVisibleOnLayout(String attributeName, String type, String layoutName, String groupName) throws WTException {
        Set<String> visibleAttributes = retrieveAttributesFromLayoutForType(type, layoutName, groupName);
        return visibleAttributes.contains(attributeName);
    }


    /**
     * The method return all attributes which had incorrect visibility in specific layout on specific object type.
     *
     * @param layoutName           getGroupMembersFromLayout of layout
     * @param typeInternalName     internal getGroupMembersFromLayout of type
     * @param groupName            getGroupMembersFromLayout of group
     * @param attributesVisibility Map with attributes internal getGroupMembersFromLayout and theirs visibility (true or false)
     * @return String with internal names of all incorrect attributes, empty String in case of correct attributes
     * @throws WTException when cannot get TypeDefinitionReadView object from type internal getGroupMembersFromLayout.
     */
    public static String getAllAttributesWithIncorrectVisibilityOnEditLayout(String layoutName, String typeInternalName, String groupName, Map<String, Boolean> attributesVisibility) throws WTException {
        StringBuilder messageOutputBuilder = new StringBuilder();
        for (Entry<String, Boolean> entry : attributesVisibility.entrySet()) {
            boolean attributeVisibleOnLayout = LayoutsTestUtility.isAttributeVisibleOnLayout(entry.getKey(), typeInternalName, layoutName, groupName);
            Boolean expected = entry.getValue();
            if (!expected.equals(attributeVisibleOnLayout)) {
                LOGGER.error("Wrong visibility of attribute " + entry.getKey() + " on " + layoutName + " in " + groupName);
                messageOutputBuilder.append(entry.getKey()).append(" ");
            }
        }
        return messageOutputBuilder.toString();
    }

    /**
     * The method return all groups which had incorrect display name(german or english) in specific layout on specific object type.
     *
     * @param layoutName       getGroupMembersFromLayout of layout
     * @param typeInternalName internal getGroupMembersFromLayout of type
     * @param groupName        getGroupMembersFromLayout of group
     * @param groups           Map with groups names and theirs display names (english and german)
     * @return String with names of all incorrect groups, empty String in case of correct attributes
     * @throws WTException when cannot get TypeDefinitionReadView object from type internal getGroupMembersFromLayout.
     */
    public static String getAllGroupsWithIncorrectDisplayNames(String layoutName, String typeInternalName, String groupName, Map<String, String[]> groups) throws WTException {
        StringBuilder messageOutputBuilder = new StringBuilder();
        for (Entry<String, String[]> entry : groups.entrySet()) {
            String[] displayNames = LayoutsTestUtility.getDisplayNamesForGroupOnLayout(groupName, layoutName, typeInternalName);
            String[] expectedDisplayNames = entry.getValue();
            if (!expectedDisplayNames[0].equals(displayNames[0])) {
                LOGGER.error("Wrong english display name of " + entry.getKey() + " on " + layoutName);
                messageOutputBuilder.append("English display name on " + entry.getKey()).append(" ");
            }
            if (!expectedDisplayNames[1].equals(displayNames[1])) {
                LOGGER.error("Wrong german display name of " + entry.getKey() + " on " + layoutName);
                messageOutputBuilder.append("German display name on " + entry.getKey()).append(" ");
            }
        }
        return messageOutputBuilder.toString();
    }

    private static String[] getDisplayNamesForGroupOnLayout(String groupName, String layoutName, String typeInternalName) throws WTException {
        Optional<LayoutDefinitionReadView> layout = getLayoutFromType(typeInternalName, layoutName);
        if (layout.isPresent()) {
            Optional<LayoutComponentReadView> group = getGroupFromLayout(layout.get(), groupName);
            if (group.isPresent()) {
                LayoutProperty displayName1 = group.get().getProperty("displayName");
                return new String[]{displayName1.getValue(Locale.US, true).toString(), displayName1.getValue(Locale.GERMAN, true).toString()};
            }
        }
        return new String[]{};
    }
}
