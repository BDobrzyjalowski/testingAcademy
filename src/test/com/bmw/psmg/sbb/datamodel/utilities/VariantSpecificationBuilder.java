package test.com.bmw.psmg.sbb.datamodel.utilities;

import com.bmw.psmg.sbb.generic.PSMGSBBConstants;
import com.bmw.psmg.sbb.generic.container.WTContainersUtility;

import com.ptc.wpcfg.doc.VariantSpec;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import wt.fc.PersistenceHelper;
import wt.folder.Folder;
import wt.folder.FolderEntry;
import wt.folder.FolderHelper;
import wt.folder.SubFolder;
import wt.inf.container.WTContainer;
import wt.type.TypedUtilityServiceHelper;
import wt.util.WTException;
import wt.util.WTPropertyVetoException;

import java.rmi.RemoteException;

/**
 * Class provide creation of VariantSpec from components like Internal Name, Container, etc.
 *
 * @author Patryk Zajko
 */
public class VariantSpecificationBuilder {

    private static final Logger LOGGER = Logger.getLogger(VariantSpecificationBuilder.class.getName());
    private Folder folder;
    private String internalName;
    private String name;
    private String number;
    private String folderName;
    private WTContainer container;

    /**
     * Method set Internal name of creating VariantSpec.
     *
     * @param internalName of creating VariantSpec
     */
    public VariantSpecificationBuilder(String internalName) {
        this.internalName = internalName;
    }

    /**
     * Generate random name for Folder.
     *
     * @return random folder name
     */
    public static String randomFolderName() {
        LOGGER.debug("Start generating Folder name");
        return RandomStringUtils.random(7, true, true);
    }

    /**
     * Generate random name for VariantSpec.
     *
     * @return random name
     */
    public static String randomVariantSpecificationName() {
        LOGGER.debug("Start generating VariantSpecification name");
        return RandomStringUtils.random(7, true, true);
    }

    /**
     * Method set name of creating VariantSpec.
     *
     * @param name of creating VariantSpec
     * @return this creating object
     */
    public VariantSpecificationBuilder name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Method set Folder name of creating VariantSpec.
     *
     * @param folderName of creating VariantSpec
     * @return this creating object
     */
    public VariantSpecificationBuilder folderName(String folderName) {
        this.folderName = folderName;
        return this;
    }

    /**
     * Method set Number of creating VariantSpec.
     *
     * @param number of creating VariantSpec
     * @return this creating object
     */
    public VariantSpecificationBuilder number(String number) {
        this.number = number;
        return this;
    }

    /**
     * Method set Container of creating VariantSpec.
     *
     * @param container of creating VariantSpec
     * @return this creating object
     */
    public VariantSpecificationBuilder container(WTContainer container) {
        this.container = container;
        return this;
    }

    /**
     * Method set Folder of creating VariantSpec.
     *
     * @param folder of creating VariantSpec
     * @return this creating object
     */
    public VariantSpecificationBuilder folder(Folder folder) {
        this.folder = folder;
        return this;
    }

    /**
     * Method create and save VariantSpec in Database.
     *
     * @return object of VariantSpec
     * @throws WTException when cannot create VariantSpec or not find type definition reference
     * @see VariantSpec
     * @see wt.type.TypeDefinitionReference
     */
    public VariantSpec build() throws WTException {
        return build(true);
    }

    /**
     * Method create VariantSpec.
     *
     * @param save - when true the method save VariantSpec object
     * @return object of VariantSpec
     * @throws WTException when cannot create VariantSpec or not find type definition reference
     * @see VariantSpec
     * @see wt.type.TypeDefinitionReference
     */
    public VariantSpec build(boolean save) throws WTException {
        if (StringUtils.isEmpty(internalName)) {
            throw new IllegalArgumentException("Internal name is required to create VariantSpecification!");
        }
        if (StringUtils.isEmpty(name)) {
            name = randomVariantSpecificationName();
        }
        if (container == null) {
            container = WTContainersUtility.getContainerObjectByName(PSMGSBBConstants.ContainerNames.TEST_PRODUCT);
        }
        VariantSpec variantSpec = VariantSpec.newVariantSpec();
        try {
            variantSpec.setName(name);
            variantSpec.setTypeDefinitionReference(TypedUtilityServiceHelper.service.getTypeDefinitionReference(internalName));
            variantSpec.setContainer(container);
            if (number != null) {
                variantSpec.setNumber(number);
            }
            if (StringUtils.isEmpty(folderName) && folder == null) {
                folderName = randomFolderName();
                folder = SubFolder.newSubFolder("VariantSpecification " + folderName, container.getDefaultCabinet());
                folder = (Folder) PersistenceHelper.manager.store(folder);
            }
            FolderHelper.assignLocation((FolderEntry) variantSpec, folder);
            variantSpec.setTypeDefinitionReference(TypedUtilityServiceHelper.service.getTypeDefinitionReference(internalName));
            return save ? (VariantSpec) PersistenceHelper.manager.save(variantSpec) : variantSpec;
        } catch (WTPropertyVetoException | RemoteException e) {
            LOGGER.error("Internal exception during VariantSpec creation", e);
            throw new WTException(e, String.format("Cannot create VariantSpec of '%s' type.", internalName));
        }
    }


}
