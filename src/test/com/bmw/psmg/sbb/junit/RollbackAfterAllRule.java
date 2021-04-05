package test.com.bmw.psmg.sbb.junit;

import org.apache.log4j.Logger;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import wt.pom.Transaction;

/**
 * Rule class starts transaction before all JUnit test method and rollback transaction after all JUnit test methods execution.
 *
 * @author Karol Poliszkiewicz
 */
public class RollbackAfterAllRule implements TestRule {

    private static final Logger LOGGER = Logger.getLogger(RollbackAfterAllRule.class.getName());

    /**
     * Wrap test class to transaction class using rollback rule.
     */
    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                LOGGER.debug("Starting transaction for test: " + description.getClassName());
                Transaction transaction = new Transaction();
                try {
                    transaction.start();
                    base.evaluate();
                } finally {
                    LOGGER.debug("Rollback transaction for test: " + description.getClassName());
                    transaction.rollback();
                }
            }
        };
    }

}
