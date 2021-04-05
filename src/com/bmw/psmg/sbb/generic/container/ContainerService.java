package com.bmw.psmg.sbb.generic.container;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.ptc.windchill.baseserver.logger.BaseLogger;
import wt.fc.PersistenceManager;
import wt.fc.QueryResult;
import wt.inf.container.WTContainer;
import wt.pds.StatementSpec;
import wt.query.QuerySpec;
import wt.query.SearchCondition;
import wt.util.WTException;

import java.time.Duration;
import java.util.Optional;

/**
 * Service for low-level operations on containers.
 *
 * @author Karol Poliszkiewicz
 */
public class ContainerService {

    private static final BaseLogger LOGGER = BaseLogger.getLogger(ContainerService.class.getName());
    private static final Duration CACHE_EXPIRE_DURATION_IN_MINUTES = Duration.ofMinutes(30);
    private static final long CACHE_MAX_SIZE = 100L;

    private final Cache<String, WTContainer> cache;
    private final PersistenceManager persistenceManager;

    /**
     * Initialize ContainerService object with required dependencies.
     *
     * @param persistenceManager instance of PersistenceManager
     */
    public ContainerService(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
        this.cache = CacheBuilder.newBuilder().expireAfterAccess(CACHE_EXPIRE_DURATION_IN_MINUTES).maximumSize(CACHE_MAX_SIZE).build();
    }

    /**
     * Find WTContainer by name. The name cannot be null.
     *
     * @param name name of searching WTContainer
     * @return Optional of WTContainer
     */
    public Optional<WTContainer> findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Container name cannot be null.");
        }
        WTContainer wtContainer = cache.getIfPresent(name);
        if (wtContainer == null) {
            LOGGER.debug(() -> String.format("%s container is not present in the cache.", name));
            try {
                QuerySpec querySpec = new QuerySpec(WTContainer.class);
                querySpec.appendWhere(new SearchCondition(WTContainer.class, WTContainer.NAME, SearchCondition.EQUAL, name), new int[]{0});
                QueryResult queryResult = persistenceManager.find((StatementSpec) querySpec);
                if (queryResult != null && queryResult.hasMoreElements()) {
                    wtContainer = (WTContainer) queryResult.nextElement();
                    LOGGER.debug(() -> String.format("Adding container's object to cache by name: %s", name));
                    cache.put(name, wtContainer);
                }
            } catch (WTException e) {
                LOGGER.error(e::getLocalizedMessage, e);
            }
        }
        return Optional.ofNullable(wtContainer);
    }
}
