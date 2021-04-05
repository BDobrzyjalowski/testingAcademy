package test.com.bmw.psmg.sbb.datamodel.part.attributes;

import static org.junit.Assert.fail;

import com.bmw.psmg.sbb.generic.PSMGSBBConstants;
import com.bmw.psmg.sbb.generic.container.WTContainersUtility;
import com.bmw.psmg.sbb.utilities.PartBuilder;
import test.com.bmw.psmg.sbb.datamodel.utilities.AttributeTestUtility;
import test.com.bmw.psmg.sbb.junit.RollbackRule;
import test.com.bmw.psmg.sbb.junit.parameter.ParametersCsvReader;

import com.ptc.test.core.NestedRunWith;
import com.ptc.test.remote.Server;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import wt.inf.container.WTContainer;
import wt.part.WTPart;
import wt.util.WTException;
import wt.vc.views.ViewHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Class verifies custom attributes on any object defined in ObjectTypes file.
 *
 * @author Bartlomiej Dobrzyjalowski
 */
@RunWith(Server.class)
@NestedRunWith(JUnitParamsRunner.class)
public class AttributesTest {

    private static final Logger LOGGER = Logger.getLogger(AttributesTest.class.getName());
    private static final String US_NUMBER = "PSMGSBB-3817";
    private static final String ATTRIBUTES_FILENAME = "AttributesTest.csv";
    private static final String ATTRIBUTES_INPUTFILE = "attributes.csv";
    private static final String CSV_FILENAME = "ObjectTypes.csv";
    private static final Map<String, Map<String, String[]>> objectAttributes = new HashMap<>();

    /**
     * Injects JUnit custom rule, which rollback all changes makes after each test.
     */
    @Rule
    public RollbackRule rollbackRule = new RollbackRule();

    /**
     * Method prepare test data for attributes.
     */
    @BeforeClass
    public static void setUp() {
        entriesForObjectParameters();
    }

    /**
     * Release loaded test data after all tests execution.
     */
    @AfterClass
    public static void tearDown() {
        objectAttributes.clear();
    }

    /**
     * Method load parameters for entries test
     */
    private static void entriesForObjectParameters() {
        Reader reader = new BufferedReader(new InputStreamReader(AttributesTest.class.getResourceAsStream(ATTRIBUTES_FILENAME), StandardCharsets.UTF_8));
        try (CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
            csvParser.getRecords().forEach(r -> {
                if (objectAttributes.containsKey(r.get(0))) {
                    objectAttributes.get(r.get(0)).put(r.get(1), new String[]{r.get(2), r.get(3)});
                } else {
                    Map<String, String[]> tmpMap = new HashMap<>();
                    tmpMap.put(r.get(1), new String[]{r.get(2), r.get(3)});
                    objectAttributes.put(r.get(0), tmpMap);
                }
            });
        } catch (IOException e) {
            LOGGER.error(e);
            fail(US_NUMBER + " - Not found attributes test data.");
        }
    }

    /**
     * Method load test data set from {@link #CSV_FILENAME} before {@link #testAttributesForObjectInstance} and {@link #testAttributesDisplayNameForObjectInstance}.
     *
     * @return Collection of test global enumerations.
     */
    @SuppressWarnings("unused")
    private Collection<String[]> parametersForAttributes() {
        return new ParametersCsvReader<String>(this.getClass().getResourceAsStream(CSV_FILENAME)).parse();
    }

    /**
     * Test case verifies if all of custom attributes are set on Object type
     *
     * @param internalName  internal name of test object
     * @param containerName name of the test object's container
     * @throws WTException when can't access Locale from session or can't get Message with list of not existing attributes
     */
    @Test
    @Parameters(method = "parametersForAttributes")
    public void testAttributesForObjectInstance(String internalName, String containerName) throws WTException {
        WTContainer testProductContainer = WTContainersUtility.getContainerObjectByName(containerName);
        WTPart part = new PartBuilder(internalName).view(ViewHelper.service.getView(PSMGSBBConstants.Views.PSMG)).container(testProductContainer).endItem(true).build();
        AttributeTestUtility.checkAttributesForObjectInstance(part, objectAttributes.get(internalName), US_NUMBER);
    }

    /**
     * Test case verifies if all of custom attributes has correct display values on Object type
     *
     * @param internalName  internal name of test object
     * @param containerName name of the test object's container
     * @throws WTException when can't access Locale from session or can't get Message with list of not existing attributes
     */
    @Test
    @Parameters(method = "parametersForAttributes")
    public void testAttributesDisplayNameForObjectInstance(String internalName, String containerName) throws WTException {
        WTContainer testProductContainer = WTContainersUtility.getContainerObjectByName(containerName);
        WTPart part = new PartBuilder(internalName).view(ViewHelper.service.getView(PSMGSBBConstants.Views.MMG)).container(testProductContainer).endItem(true).build();
        AttributeTestUtility.checkAttributesDisplayNamesForObjectInstance(part, objectAttributes.get(internalName), US_NUMBER);
    }

    /**
     * Initial method for setting up data
     */
    @BeforeClass
    public static void setUpForCommentsAttribute() {
        populateCommentsAttribute();
    }

    /**
     * Method load test data set from {@link #ATTRIBUTES_INPUTFILE}
     */
    @SuppressWarnings("unused")
    private Collection<String> parametersForPositionVariantAndBoltingCase() {
        return new ParametersCsvReader<String>(this.getClass().getResourceAsStream(ATTRIBUTES_INPUTFILE)).map(r -> r.get(0)).read();
    }

    /**
     * Method to populate Comments Attribute
     */
    private static void populateCommentsAttribute() {
        Reader reader = new BufferedReader(new InputStreamReader(AttributesTest.class.getResourceAsStream(ATTRIBUTES_INPUTFILE), StandardCharsets.UTF_8));
        try (CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
            csvParser.getRecords().forEach(r -> {
                if (objectAttributes.containsKey(r.get(0))) {
                    objectAttributes.get(r.get(0)).put(r.get(1), new String[]{r.get(2)});
                } else {
                    Map<String, String[]> tmpMap = new HashMap<>();
                    tmpMap.put(r.get(1), new String[]{r.get(2)});
                    objectAttributes.put(r.get(0), tmpMap);
                }
            });
        } catch (IOException e) {
            LOGGER.error(e);
            fail(US_NUMBER + " - Not found attributes test data.");
        }
    }

    /**
     * Test case validate attributes on MMG position Variant Bolting Case and Inhouse Production type
     *
     * @param internalName  internal name of the test object
     * @throws WTException when loading or setting attributes
     */
    @Test
    @Parameters(method = "parametersForPositionVariantAndBoltingCase")
    public void testAttributesForPositionVariantBoltingCaseAndInhouseProduction(String internalName) throws WTException {
        LOGGER.debug(String.format("Testing %s.", internalName));
        AttributeTestUtility.checkAttributesForTypeDefinition(internalName, objectAttributes.get(internalName), US_NUMBER);
    }
}
