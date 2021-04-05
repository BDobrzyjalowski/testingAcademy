package test.com.bmw.psmg.sbb.junit;

import com.ptc.windchill.baseserver.logger.BaseLogger;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import wt.pom.PersistenceException;
import wt.pom.Transaction;

/**
 * Rule class starts transaction before JUnit test method and rollback transaction after JUnit test method execution.
 *
 * @author Karol Poliszkiewicz
 */
public class RollbackRule extends TestWatcher {

    private static final BaseLogger LOGGER = BaseLogger.getLogger(RollbackRule.class.getName());

    private final Transaction transaction;

    /**
     * Initializes Rollback rule class and creates Transaction object.
     */
    public RollbackRule() {
        super();
        this.transaction = new Transaction();
    }

    @Override
    protected void starting(Description description) {
        try {
            LOGGER.debug(() -> String.format("Starting transaction before test: %s#%s", description.getClassName(), description.getMethodName()));
            transaction.start();
            super.starting(description);
        } catch (PersistenceException e) {
            LOGGER.error(() -> String.format("Cannot start Windchill Transaction before test: %s#%s", description.getClassName(), description.getMethodName()), e);
            failed(e, description);
        }
    }

    @Override
    protected void finished(Description description) {
        LOGGER.debug(() -> String.format("Rollback transaction after test: %s#%s", description.getClassName(), description.getMethodName()));
        transaction.rollback();
        super.finished(description);
    }
}
