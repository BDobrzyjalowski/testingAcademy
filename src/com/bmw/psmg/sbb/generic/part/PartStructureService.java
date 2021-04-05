package com.bmw.psmg.sbb.generic.part;

import static com.bmw.psmg.sbb.generic.PSMGSBBConstants.WTLinkAttributes.USED_BY_ROLE;

import org.apache.log4j.Logger;
import wt.fc.PersistenceManager;
import wt.fc.QueryResult;
import wt.part.WTPart;
import wt.part.WTPartMaster;
import wt.part.WTPartUsageLink;
import wt.util.WTException;
import wt.vc.config.LatestConfigSpec;

import java.util.Optional;

/**
 * Service using PersistenceManager to navigate Parts structure.
 *
 * @author Karol Poliszkiewicz
 */
public class PartStructureService {

    private static final Logger LOGGER = Logger.getLogger(PartStructureService.class.getName());

    private final PersistenceManager manager;

    /**
     * Initialize class with required dependencies.
     *
     * @param manager instance of PersistenceManager
     */
    public PartStructureService(PersistenceManager manager) {
        this.manager = manager;
    }

    /**
     * Find master of WTPart's parent.
     *
     * @param partMaster child's master of expected parent
     * @return existing part's parent or empty optional
     */
    public Optional<WTPartMaster> findParent(WTPartMaster partMaster) {
        LatestConfigSpec configSpec = new LatestConfigSpec();
        try {
            QueryResult usedBy = configSpec.process(manager.navigate(partMaster, USED_BY_ROLE, WTPartUsageLink.class, true));
            if (usedBy.hasMoreElements()) {
                WTPart parent = (WTPart) usedBy.nextElement();
                return Optional.of(parent.getMaster());
            }
        } catch (WTException e) {
            LOGGER.error("Cannot find parent for part: " + partMaster.getNumber() + ", " + partMaster.getName(), e);
        }
        return Optional.empty();
    }
}
