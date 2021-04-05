package test.com.bmw.psmg.sbb.containers;

import static org.assertj.core.api.Assertions.fail;

import com.bmw.psmg.sbb.generic.container.ContainerService;
import test.com.bmw.psmg.sbb.junit.parameter.ParametersCsvReader;

import com.ptc.test.core.NestedRunWith;
import com.ptc.test.remote.Server;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import wt.fc.PersistenceHelper;

import java.util.Collection;
import java.util.function.Function;

/**
 * Class for testing containers existence in Windchill
 *
 * @author Pawel Miron
 * @author Karol Poliszkiewicz
 */
@RunWith(Server.class)
@NestedRunWith(JUnitParamsRunner.class)
public class ContainersExistenceSmokeTest {

    private static final String CONTAINER_EXISTENCE_PARAMETERS = "ContainersExistenceSmokeTestParameters.csv";
    private static final int CONTAINER_EXISTENCE_NAME_INDEX = 0;

    private final ContainerService containerService = new ContainerService(PersistenceHelper.manager);

    /**
     * Method load parameters from {@link #CONTAINER_EXISTENCE_PARAMETERS} file.
     *
     * @return Collection of product container test parameters
     */
    @SuppressWarnings("unused")
    private Collection<String> parametersForContainerExistence() {
        Function<CSVRecord, String> mapper = r -> r.get(CONTAINER_EXISTENCE_NAME_INDEX);
        return new ParametersCsvReader<String>(this.getClass().getResourceAsStream(CONTAINER_EXISTENCE_PARAMETERS)).map(mapper).read();
    }

    /**
     * Test case verify existence of required containers.
     *
     * @param containerName name of testing Container
     */
    @Test
    @Parameters(method = "parametersForContainerExistence")
    public void shouldExistsWithProperName(String containerName) {
        containerService.findByName(containerName).orElseGet(() -> fail("Container not exists: <%s>.", containerName));
    }
}
