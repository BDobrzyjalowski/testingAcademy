package com.bmw.psmg.sbb.generic.organization;

import org.apache.log4j.Logger;
import wt.fc.PersistenceServerHelper;
import wt.fc.QueryResult;
import wt.inf.container.OrgContainer;
import wt.query.QuerySpec;
import wt.query.SearchCondition;
import wt.util.WTException;

/**
 * Utility class that helps managing organization related objects
 *
 * @author Daniel Zietala
 */
public class OrganizationUtility {

    private static final Logger LOGGER = Logger.getLogger(OrganizationUtility.class.getName());

    private OrganizationUtility() {
        throw new IllegalStateException("OrganizationUtility is the utility class.");
    }

    /**
     * Fetches the OrgContainer object from the database based on name.
     *
     * @param organizationName name of the target organization
     * @return OrgContainer object representing organization
     * @throws WTException when PersistenceServerHelper cannot execute query
     */
    public static OrgContainer getOrgContainer(String organizationName) throws WTException {
        QuerySpec query = new QuerySpec(OrgContainer.class);
        query.appendWhere(new SearchCondition(OrgContainer.class, OrgContainer.NAME, SearchCondition.EQUAL, organizationName), new int[]{});
        QueryResult result = PersistenceServerHelper.manager.query(query);
        if (result != null && result.hasMoreElements()) {
            return (OrgContainer) result.nextElement();
        }
        LOGGER.info("Organization " + organizationName + " not found!");
        return null;
    }

}
