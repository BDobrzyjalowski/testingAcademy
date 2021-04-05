package com.bmw.psmg.sbb.generic.container;

import org.apache.log4j.Logger;
import wt.fc.PersistenceHelper;
import wt.fc.QueryResult;
import wt.inf.container.WTContainer;
import wt.pds.StatementSpec;
import wt.query.ArrayExpression;
import wt.query.ClassAttribute;
import wt.query.QuerySpec;
import wt.query.SearchCondition;
import wt.util.WTException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

/**
 * Class contains reusable methods for working with WTContainers
 *
 * @author Pawel Miron
 */
public class WTContainersUtility {

    private static final Logger LOGGER = Logger.getLogger(WTContainersUtility.class.getName());

    private WTContainersUtility() {
    }

    /**
     * Method gets WTContainer object by its name
     *
     * @param name Name of the WTContainer
     * @return WTContainer with given name or null if container does not exists
     * @throws WTException during QuerySpec creation
     */
    public static WTContainer getContainerObjectByName(String name) throws WTException {
        QuerySpec querySpec = new QuerySpec(WTContainer.class);
        querySpec.appendWhere(new SearchCondition(WTContainer.class, WTContainer.NAME, SearchCondition.EQUAL, name), new int[]{0});
        QueryResult queryResult = PersistenceHelper.manager.find((StatementSpec) querySpec);
        if (queryResult != null && queryResult.hasMoreElements()) {
            return (WTContainer) queryResult.nextElement();
        } else {
            LOGGER.info("WTContainer with name " + name + " doesn't found");
            return null;
        }
    }

    /**
     * Find WTContainer by name
     *
     * @param name Name of the WTContainer
     * @return optional of WTContainer
     * @deprecated use {@link ContainerService#findByName(String)}.
     */
    @Deprecated
    public static Optional<WTContainer> findContainer(String name) {
        WTContainer container = null;
        try {
            container = getContainerObjectByName(name);
        } catch (WTException e) {
            LOGGER.error(String.format("Cannot find WTContainer with NAME = '%s'", name), e);
        }
        return Optional.ofNullable(container);
    }

    /**
     * This method returns the collection of WTContainer by name.
     *
     * @param names Names of the WTContainers
     * @return WTContainer collection, if containers do not exists the method return empty collection
     * @throws WTException during QuerySpec creation
     */
    public static Collection<WTContainer> findContainersByName(Collection<String> names) throws WTException {
        QuerySpec querySpec = new QuerySpec(WTContainer.class);
        querySpec.appendWhere(new SearchCondition(new ClassAttribute(WTContainer.class, WTContainer.NAME), SearchCondition.IN, new ArrayExpression(names.toArray(new String[0]))), new int[]{0});
        QueryResult queryResult = PersistenceHelper.manager.find((StatementSpec) querySpec);
        Collection<WTContainer> containers = new HashSet<>();
        while (queryResult != null && queryResult.hasMoreElements()) {
            containers.add((WTContainer) queryResult.nextElement());
        }
        return containers;
    }

}
