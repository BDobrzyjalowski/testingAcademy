package com.bmw.psmg.sbb.generic;


import java.util.List;

/**
 * Class which contains all constants used in the project.
 */
public final class PSMGSBBConstants {

    public static final String ORGANIZATION_NAME = "SBB";
    /*
     * GROUPS
     */
    public static final String ESI_AUTHORS_GROUP_NAME = "ESI Authors";
    public static final String SBB_ALL_USERS_GROUP_NAME = "sbbAllUser_group";
    public static final String PSMGSBB_PROPERTIES = "psmgsbb.properties";
    public static final String INTERNAL_NAME = "internalName";
    public static final String DISPLAY_NAME_EN = "displayNameEN";
    public static final String DISPLAY_NAME_DE = "displayNameDE";
    public static final String WINDCHILL_PROPERTY_TASK_ROOT_DIRECTORY = "wt.federation.taskRootDirectory";
    public static final String WEBJECT_INTERNAL_NAME = "INTERNAL_NAME";
    public static final String WEBJECT_DISPLAY_NAME_EN = "DISPLAY_NAME_EN";
    public static final String WEBJECT_DISPLAY_NAME_DE = "DISPLAY_NAME_DE";
    public static final String WEBJECT_PARENT_INTERNAL_NAME = "PARENT_INTERNAL_NAME";
    public static final String WEBJECT_INSTANTIABLE_PARAM = "INSTANTIABLE";
    public static final String WEBJECT_SET_ATTRIBUTES_PARAM = "SET_ATTRIBUTES";
    public static final String WEBJECT_ATTRIBUTE_NAME = "ATTRIBUTE_NAME";
    public static final String WEBJECT_ATTRIBUTE_VALUE = "ATTRIBUTE_VALUE";
    public static final String WEBJECT_STATE = "STATE";
    public static final String WEBJECT_GROUP_IN = "GROUP_IN";
    public static final String WEBJECT_OBID = "obid";
    public static final String WEBJECT_OID = "WEBJECT_OID";
    public static final String WEBJECT_GROUP_OUT = "GROUP_OUT";
    public static final String MISSING_MATERIAL_NAME = "Missing Material";
    public static final String WEBJECT_LOGGER = "LOGGER";
    public static final String WEBJECT_MESSAGE = "MESSAGE";
    public static final String WEBJECT_FOLDER = "folder";
    public static final String WEBJECT_TYPE = "TYPE";
    public static final String WEBJECT_DIRECTION = "DIRECTION";
    public static final String WEBJECT_WHERE = "WHERE";
    public static final String WEBJECT_VERSIONED_ALL_KEY = "ALL";
    public static final String WEBJECT_VERSIONED_LATEST_KEY = "LATEST";
    public static final String TRACK_EVENT_MESSAGES_QUEUE_NAME = "BMW Track Event Messages Queue";
    public static final String WINDCHILL_INTERFACE_QUEUE_JOB_TARGET_METHOD = "verifyTechnicalCompleteness";
    public static final String WEBJECT_PARAM_ORGANIZATION_NAME = "ORGANIZATION_NAME";
    public static final String WEBJECT_PARAM_DESCRIPTION = "DESCRIPTION";
    public static final String WEBJECT_PARAM_PARENT_DOMAIN_PATH = "PARENT_DOMAIN_PATH";
    public static final String WEBJECT_PARAM_CONTAINER_NAME = "CONTAINER_NAME";
    public static final String WINDCHILL_WAITING_QUEUE_TARGET_METHOD = "invokeWaitingLogic";
    public static final String WEBJECT_SORT_BY = "SORT_BY";

    public static final String WINDCHILL_QUEUE_JOB_TARGET_METHOD = "invoke";
    public static final String WINDCHILL_QUEUE_START_IMPORT_JMS_LOG = "****************************** START IMPORT %s Entry Number %s iDoc %s ******************************";
    public static final String WINDCHILL_QUEUE_END_IMPORT_JMS_LOG = "****************************** END IMPORT %s Entry Number %s iDoc %s ******************************";
    public static final String WINDCHILL_QUEUE_START_IMPORT_SOAP_LOG = "****************************** START IMPORT %s Entry Number %s ******************************";
    public static final String WINDCHILL_QUEUE_END_IMPORT_SOAP_LOG = "****************************** END IMPORT %s Entry Number %s ******************************";
    public static final String WINDCHILL_QUEUE_START_INTEGRATION_LOG = "****************************** START TECHINCAL COMPLETNESS %s ******************************";
    public static final String WINDCHILL_QUEUE_END_INTEGRATION_LOG = "****************************** END TECHINCAL COMPLETNESS %s ******************************";
    public static final String MMG_NODES_GROUP = "MMGNodes";
    public static final String MMG_ROOT_GROUP = "MMGRoot";
    public static final String MMG_LINKS_GROUP = "MMGLinks";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String ATTRIBUTE_PARAM = "ATTRIBUTE";
    public static final String ELEMENT_PARAM = "ELEMENT";
    public static final String VALUE_PARAM = "VALUE";
    public static final String LOGGER_PARAM = "LOGGER";
    public static final String DELFLG_PARAM = "DELFLG";
    public static final String QUNIT_PARAM = "QUNIT";
    public static final String PVNAME_PARAM = "PVNAME";
    public static final String PVGUID_PARAM = "PVGUID";
    public static final String EXPRESSION_PARAM = "EXPRESSION";
    public static final String EFFECTIVITY_PARAM = "EFFECTIVITY";
    public static final String EFFECTIVITY_TYPE_DATE = "DATE";
    public static final String EFFECTIVITY_TYPE_LOT = "LOT";
    public static final String IDENTIFY_BY_PARAM = "IDENTIFY_BY";
    public static final String GENERIC_PART_INPUT_GROUP_NAME = "GenPartInputGroup";
    public static final String GENERIC_INPUT_GROUP = "GenericInput";
    public static final String IDOCNUMBER_ATTIRBUTE = "IDOCNUMBER";
    public static final String SERIAL_ATTRIBUTE = "SERIAL";
    public static final String INFO_ENGINE_TASKS_ROOT_DIRECTORY_PROPERTY = "wt.federation.taskRootDirectory";
    public static final String OUTER_STRUCTURE_GROUP = "outerStructure";
    public static final String CLASSIFICATION_NODE = "CLASSIFICATION_NODE";
    public static final String GERMAN_KEY = "D";
    public static final String ENGLISH_KEY = "E";
    public static final String BMW_SERIAL_PPG = "BMW_SERIAL_PPG";
    public static final String BMW_SERIAL_UPG = "BMW_SERIAL_UPG";
    public static final String BMW_SERIAL_MODULEORG = "BMW_SERIAL_MODULEORG";
    public static final String BMW_SERIAL_GENERIC_PART = "BMW_SERIAL_GENERIC_PART";
    public static final String TRANS_ID = "TRANS_ID";
    public static final String TRANS_COUNT = "TRANS_COUNT";
    public static final String TRANS_EVENT = "transEvent";
    public static final String TRANS_EVENT_XML_TAG = "TRANS_EVENT";
    public static final String SERAIL_NUMBER = "serailNum";
    public static final String TRANS_END = "transEnd";
    public static final String IDOC_NUMBER = "iDocNum";
    public static final String SERIAL = "Serial";
    public static final String NUMBER_OF_OBJECTS = "NUMBER_OF_OBJECTS";
    public static final String TYPE = "Type";
    public static final String DEFAULT_LOT_EFFECTIVITY_CONTEXT = "REL";
    public static final String ALTERNATE1 = "alternate1";
    public static final String ALTERNATE2 = "alternate2";
    public static final String EVENT_NODE_CHANGED = "NODE_CHANGED";
    public static final String EVENT_VARIANT_CHANGED = "VARIANT_CHANGED";
    public static final String ALL_MODULE_ORGS_GROUP = "sbb_AllModuleOrgs_group";
    private static final String BMW_DOMAIN = "com.bmw.psmg.sbb.";
    public static final String TYP_ENTW_BEZ = "TYP_ENTW_BEZ";
    public static final String PROTOCOL_SEPARATOR = "|";

    private PSMGSBBConstants() {
    }

    /**
     * Class contains attributes for windchill links
     */
    public static final class WTLinkAttributes {

        public static final String USED_BY_ROLE = "usedBy";
        public static final String USES_ROLE = "uses";

        private WTLinkAttributes() {
        }

    }

    /**
     * Class contains max String length constraints for attributes
     */
    public static final class AttributesMaxStringLengths {

        public static final Integer BMW_SUBTYPE = 2;
        public static final Integer BMW_PRODUCT_PROCESS_GRP = 9;
        public static final Integer BMW_TYPE = 8;
        public static final Integer BMW_NODENUMBER = 8;
        public static final Integer BMW_MODULEORG = 5;
        public static final Integer BMW_MODULE = 5;
        public static final Integer BMW_UNIFIED_PARTS_GRP = 5;
        public static final Integer BMW_COMMODITY = 4;
        public static final Integer BMW_CLASS = 18;
        public static final Integer BMW_PDM_TIMESTAMP = 21;
        public static final Integer BMW_PDM_PARENT_GUID = 32;
        public static final Integer BMW_PDM_PARENT_NAME = 40;
        public static final Integer BMW_PDM_GENERIC_PARENT_GUID = 32;
        public static final Integer BMW_DEPARTMENT = 4;
        public static final Integer BMW_SERIAL = 14;

        private AttributesMaxStringLengths() {
        }

    }

    /**
     * Class contains used lifeCycles.
     */
    public static final class LifeCycles {

        public static final String TWO_PHASE_DEVELOPMENT = "Two Phase Development";
        public static final String MMG_POSITION_VARIANT_LIFECYCLE = "BMW MMG Position Variant Life Cycle";
        public static final String PSMG_POSITION_VARIANT_LIFECYCLE = "BMW PSMG Position Variant Life Cycle";
        public static final String GENERIC = "BMW Generic Life Cycle";
        public static final String MATERIAL = "Basic";
        public static final String T_ATTRIBUTES = "BMW T Attributes Life Cycle";
        public static final String M_ATTRIBUTES = "BMW M Attributes Life Cycle";
        public static final String VARIANT_SPECIFICATION = "BMW Variant Specification Lifecycle";
        public static final String BMW_CHANGE_NOTICE = "BMW Change Notice Life Cycle";
        public static final String BMW_CHANGE_CONTEXT_PROXY = "BMW Change Context Proxy Lifecycle";
        public static final String BMW_CHANGE_TASK = "BMW Change Activity Life Cycle";
        public static final String PSMG_CHANGE_TASK = "PSMG Change Activity Life Cycle";
        public static final String MMG_CHANGE_TASK = "MMG Change Activity Life Cycle";
        public static final String SBB_CHANGE_NOTICE = "SBB Change Notice Life Cycle";
        public static final String SBB_CHANGE_NOTICE_2_0 = "SBB Change Notice 2.0 Life Cycle";
        public static final String SBB_CHANGE_TASK = "SBB Change Activity Life Cycle";
        public static final String SBB_CHANGE_TASK_2_0 = "SBB Change Activity 2.0 Life Cycle";
        public static final String BMW_SUPPLY_CHAIN_PSFD = "BMW Supply Chain PSFD";
        public static final String TYPE_USAGE_CHANGE_NOTICE = "Type Usage Change Notice Life Cycle";
        public static final String TYPE_USAGE_CHANGE_TASK = "Type Usage Change Task Life Cycle";
        public static final String BMW_WORK_SCOPE_LIFECYCLE = "BMW Work Scope Life Cycle";

        private LifeCycles() {
        }

    }

    /**
     * Class contains used lifecycle states.
     */
    public static final class States {

        public static final String DELETED = "PSMGSBB_DELETED";
        public static final String E_FIXED = "PSMGSBB_E_FIXED";
        public static final String FIXED = "PSMGSBB_FIXED";
        public static final String IN_REWORK = "PSMGSBB_IN_REWORK";
        public static final String PENDING = "PENDING"; // This is OOTB state
        public static final String ACTIVE = "PSMGSBB_ACTIVE";
        public static final String INACTIVE = "PSMGSBB_INACTIVE";
        public static final String KONZ = "PSMGSBB_KONZ";
        public static final String PRJA = "PSMGSBB_PRJA";
        public static final String PRJG = "PSMGSBB_PRJG";
        public static final String AVER = "PSMGSBB_AVER";
        public static final String VERF = "PSMGSBB_VERF";
        public static final String AVOR = "PSMGSBB_AVOR";
        public static final String VORF = "PSMGSBB_VORF";
        public static final String APRO = "PSMGSBB_APRO";
        public static final String PROF = "PSMGSBB_PROF";
        public static final String IN_WORK = "PSMGSBB_IN_WORK";
        public static final String DEVELOPMENT_MATURITY = "PSMGSBB_DEVELOPMENT_MATURITY";
        public static final String PRODUCTION_MATURITY = "PSMGSBB_PRODUCTION_MATURITY";
        public static final String DOC_PRODUCTIVE = "PSMGSBB_DOC_PRODUCTIVE";
        public static final String INACTIVE_RUN_OUT = "PSMGSBB_INACTIVE_RUN_OUT";
        public static final String PSMGSBB_CANCELLED = "PSMGSBB_CANCELLED";
        public static final String OPEN = "OPEN"; // This is OOTB state
        public static final String CLOSED = "CLOSED"; // This is OOTB state
        public static final String UNDER_REVIEW = "UNDERREVIEW"; // This is OOTB state
        public static final String APPROVED = "APPROVED"; // This is OOTB state
        public static final String REJECTED = "REJECTED"; // This is OOTB state
        public static final String IMPLEMENTATION = "IMPLEMENTATION"; // This is OOTB state
        public static final String REWORK = "REWORK"; // This is OOTB state
        public static final String RELEASED = "RELEASED"; // This is OOTB state
        public static final String RESOLVED = "RESOLVED"; // This is OOTB state
        public static final String CANCELED = "CANCELLED"; // This is OOTB state
        public static final String COMPLETED = "COMPLETED"; // This is OOTB state
        public static final String M_FIXED = "PSMGSBB_M_FIXED";
        public static final String M_READY = "PSMGSBB_M_READY";
        public static final String T_FIXED = "PSMGSBB_T_FIXED";
        public static final String T_READY = "PSMGSBB_T_READY";
        public static final String READY = "PSMGSBB_READY";
        public static final String NEW = "PSMGSBB_NEW";
        public static final String IMPORT_COMPLETED = "PSMGSBB_IMPORT_COMPLETED";
        public static final String IMPORT_FAILED = "PSMGSBB_IMPORT_FAILED";
        public static final String VERIFICATION_FAILED = "PSMGSBB_VERIFICATION_FAILED";
        public static final String VERIFICATION_COMPLETED = "PSMGSBB_VERIFICATION_COMPLETED";
        public static final String PSMG_VIEW_FAILED = "PSMGSBB_PSMG_VIEW_FAILED";
        public static final String NOT_RELEVANT = "PSMGSBB_NOT_RELEVANT";
        public static final String OBSOLETE = "OBSOLETE";
        public static final String CREATION = "CREATION";
        public static final String INWORK = "INWORK";

        private States() {
        }

    }

    /**
     * Class contains used role internal names.
     */
    public static final class Roles {

        public static final String CHANGE_ADMIN_PLANT = "SBB_CHANGE_ADMIN_PLANT";
        public static final String ASSIGNEE = "ASSIGNEE";
        public static final String SBB_E_APPROVER = "SBB_E_APPROVER";
        public static final String SBB_E_EXPERT = "SBB_E_EXPERT";
        public static final String SBB_E_REVIEWER = "SBB_E_REVIEWER";

        private Roles() {
        }

    }

    /**
     * Class contains used views.
     */
    public static final class Views {

        public static final String DESIGN = "Design"; // This is OOTB view
        public static final String MMG = "MMG";
        public static final String PSMG = "PSMG";
        public static final String A120 = "A120";
        public static final String A121 = "A121";
        public static final String A120_02_40 = "A120";
        public static final String A130 = "A130";
        public static final String Z100 = "Z100";

        private Views() {
        }

    }

    /**
     * Class contains used preferences.
     */
    public static final class Preferences {

        public static final String CONFIGURABLE_MODULE_SUPPORT = "ENABLE_CONFIGURABLE_PARTS";
        public static final String FIND_VARIANTS_ACTION_FOR_CONFIGURABLE_MODULES = "FIND_VARIANT_PREF";
        public static final String DISPLAY_ASSIGNED_EXPRESSIONS = "DISPLAY_ASSIGNED_EXPRESSIONS";
        public static final Boolean DISPLAY_ASSIGNED_EXPRESSIONS_EXPECTED_VALUE = true;
        public static final String DOC_RECORD_SEPARATOR = "com.bmw.psmg.sbb.DOC_RECORD_SEPARATOR";
        public static final String BMW_PORTAL_ADDRESS = "/sbb/DisplaySettings/PortalAddress";
        public static final String BMW_MY_WORKPLACE_PORTAL_URL = "/sbb/DisplaySettings/MyWorkplaceApplicationURL";
        public static final String BMW_TYPE_USAGE_LINK_VIEWS = "/sbb/DisplaySettings/TypeUsageViews";
        public static final String TIMEOUT_PSDM = "/sbb/Interfaces/Integration/IF Integration - PSDM type determination timeout";
        public static final String MMG_ROLLBACK = "/sbb/Interfaces/Integration/IF Integration - Rollback MMG import when PSDM type determination fails";
        public static final String MMG_PLANT_ROLLBACK = "/sbb/Interfaces/Integration/IF Integration - Rollback MMG import when security label for Plant determination fails";
        public static final String MMG_DERIVATE_ROLLBACK = "/sbb/Interfaces/Integration/IF Integration - Rollback MMG import when security label for Derivate determination fails";
        public static final String ASSEMBLY_SITE_ERROR_HANDLING = "/sbb/Interfaces/Integration/IF Integration - Fail whole transformation workflow when update Assembly Site PFDs fails";
        public static final String INITIATE_VERIFY_FUNCTIONAL_CONSISTENCY_FOR_OUTDATED = "/sbb/Interfaces/Integration/MMG Change Task/Initiate 'Verify Functional Consistency' for outdated messages";
        public static final String TIME_FOR_SCHEDULING_VERIFY_FUNCTIONAL_CONSISTENCY_FOR_OUTDATED = "/sbb/Interfaces/Integration/MMG Change Task/Time for scheduling 'Verify Functional Consistency' for outdated messages";
        public static final String PLANT_SPECIFIC_VIEWS = "EnterpriseData.ListPlantSpecificViews";
        public static final String INITIAL_LOAD_MESSAGE_CUTOFF = "/sbb/Interfaces/Integration/Initial Load message cutoff";
        public static final String ENABLE_PRELOADED_CACHE = "/sbb/Interfaces/IF_PSM_SBB_103/enablePreloadedCache";
        public static final String USE_PRS_WHEN_MISSING_IN_CACHE = "/sbb/Interfaces/IF_PSM_SBB_103/usePRSWhenMissingInCache";
        public static final String CHUNK_SIZE_FOR_CREATE_CASE_OF_GENERIC_PART_TRANSFORMATION = "/sbb/Interfaces/Integration/Chunk size for create case of Generic Part transformation";

        public static final String SESSION_RECOVERY_ATTEMPTS = "/sbb/Interfaces/MQ Mesasge Recovery/Session Recovery Attempts";
        public static final String SESSION_RECOVERY_WAITING_PERIOD = "/sbb/Interfaces/MQ Mesasge Recovery/Session Recovery Waiting Period (min.)";
        public static final String PROCESS_FAILING_MSG = "/sbb/Interfaces/Integration/IF Integration - Track event message even though some objects for import message could not be imported";
        public static final String EXCLUDE_AK_MSG_FROM_CN = "/sbb/Interfaces/Integration/IF Integration - Exclude assembly kit messages from Change Notice for Initial Load";
        public static final String MAT_002_VALIDATE_MESSAGES = "/sbb/Interfaces/MAT/IFMATSBB002/numberOfQueues/validateMessages";
        public static final String MAT_002_NUMBER_OF_QUEUES_PREF_PATH = "/sbb/Interfaces/MAT/IFMATSBB002/numberOfQueues";
        public static final String MMG_101_NUMBER_OF_QUEUES_PREF_PATH = "/sbb/Interfaces/MMG/IFMMGSBB101/numberOfQueues";
        public static final String MMG_100_NUMBER_OF_QUEUES_PREF_PATH = "/sbb/Interfaces/MMG/IFMMGSBB100/numberOfQueues";
        public static final String IF_ROM_SBB_001_INTEGRATION_FREQUENCY = "/sbb/Interfaces/IF-ROM-SBB-001/IntegrationSyncFrequency";
        public static final String IF_ROM_SBB_001_SEND_STATUS_FREQUENCY = "/sbb/Interfaces/IF-ROM-SBB-001/SendStatusFrequency";
        public static final String IF_ROM_SBB_001_INTEGRATION_ACTIVE = "/sbb/Interfaces/IF-ROM-SBB-001/IntegrationActive";
        public static final String ROMA_FUNCTIONAL_GROUPS_MAPPING_PREFERENCE = "/sbb/Interfaces/IF-ROM-SBB-001/RoMaRoleSBBFunctGroupMapping";
        public static final String ROMA_DIFF_TYPES_PREFERENCE = "/sbb/Interfaces/IF-ROM-SBB-001/RoMaRoleDiffTypes";
        public static final String ROMA_COOP_PROJECT_MAPPING_PREFERENCE = "/sbb/Interfaces/IF-ROM-SBB-001/RoMaCOOPProjectMapping";
        public static final String ROMA_PROJECT_ROLE_MAPPING_PREFERENCE = "/sbb/Interfaces/IF-ROM-SBB-001/RoMaRoleSBBProjRoleMapping";
        public static final String ROMA_LICENSE_GROUP_MAPPING_PREFERENCE = "/sbb/Interfaces/IF-ROM-SBB-001/RoMaRoleSBBLicenseGroupMapping";
        public static final String IF_ROM_SBB_001_CLEANUP_FREQUENCY_PREFERENCE = "/sbb/Interfaces/IF-ROM-SBB-001/CleanupFrequency";
        public static final String IF_SBB_MAT_003_DISPLAY_URL_PREFERENCE = "/sbb/Interfaces/MAT/IF-SBB-MAT-003/MatrxUrl";
        public static final String IF_INTEGRATION_THROTTLE_MAX_RUNNING_WFSYNCROBOTS = "/sbb/Interfaces/Integration/ThrottleMaxRunningWfSyncRobots";
        public static final String IF_INTEGRATION_THROTTLE_MAX_WAITING_QUEUE_ENTRIES = "/sbb/Interfaces/Integration/ThrottleMaxWaitingQueueEntries";
        public static final String IF_INTEGRATION_THROTTLE_SURPASSED_THRESHOLD_SLEEP_SECONDS = "/sbb/Interfaces/Integration/ThrottleSurpassedThresholdSleepSeconds";

        private Preferences() {
        }

    }

    /**
     * Class contains used Standard Profiles.
     */
    public static final class StandardProfiles {

        public static final String BMW_SBB_ALL_USER_PROFILE = "BMW SBB All User";

        private StandardProfiles() {
        }

    }

    /**
     * Class contains internal names of known types.
     */
    public static final class Types {

        public static final String WT_PART_MASTER_INTERNAL_NAME = "wt.part.WTPartMaster"; // This is OOTB WTPartMaster internal name
        public static final String WT_PART_INTERNAL_NAME = "wt.part.WTPart"; // OOTB WTPart internal name
        public static final String VARIANT_SPEC_INTERNAL_NAME = "com.ptc.wpcfg.doc.VariantSpec"; // OOTB VariantSpec internal name
        public static final String ROOT_INTERNAL_NAME = BMW_DOMAIN + "Root";
        public static final String NODE_INTERNAL_NAME = BMW_DOMAIN + "Node";
        public static final String NODE_MMG_INTERNAL_NAME = BMW_DOMAIN + "NodeMMG";
        public static final String NODE_PLANT_INTERNAL_NAME = BMW_DOMAIN + "NodePlant";
        public static final String NO_GENERIC_NODE_INTERNAL_NAME = BMW_DOMAIN + "NoGenericNode";
        public static final String GENERIC_PART_INTERNAL_NAME = BMW_DOMAIN + "GenericPart";
        public static final String NO_GENERIC_PART_INTERNAL_NAME = BMW_DOMAIN + "NoGenericPart";
        public static final String POSITION_VARIANT_INTERNAL_NAME = BMW_DOMAIN + "PositionVariant";
        public static final String POSITION_VARIANT_MMG_INTERNAL_NAME = BMW_DOMAIN + "PositionVariantMMG";
        public static final String POSITION_VARIANT_PSMG_INTERNAL_NAME = BMW_DOMAIN + "PositionVariantPSMG";
        public static final String RELEASE_INTERNAL_NAME = BMW_DOMAIN + "Release";
        public static final String EFFECTIVITY_CONTEXT_INTERNAL_NAME = BMW_DOMAIN + "EffectivityContext";
        public static final String ASSEMBLY_KIT_HEADER_INTERNAL_NAME = BMW_DOMAIN + "AssemblyKitHeader";
        public static final String DEMAND_LOCATION_INTERNAL_NAME = BMW_DOMAIN + "DemandLocation";
        public static final String COLOR_POSITION_INTERNAL_NAME = BMW_DOMAIN + "ColorPosition";
        public static final String FLEXIBLE_ASSEMBLY_INTERNAL_NAME = BMW_DOMAIN + "FlexibleAssembly";
        public static final String MODULAR_ASSEMBLY_INTERNAL_NAME = BMW_DOMAIN + "ModularAssembly";
        public static final String MODULAR_SCOPE_INTERNAL_NAME = BMW_DOMAIN + "ModularScope";
        public static final String SINGLE_PART_INTERNAL_NAME = BMW_DOMAIN + "SinglePart";
        public static final String COLOR_VARIANT_INTERNAL_NAME = BMW_DOMAIN + "ColorVariant";
        public static final String COLOR_SCHEMA_INTERNAL_NAME = BMW_DOMAIN + "ColorSchema";
        public static final String MATERIAL_INTERNAL_NAME = BMW_DOMAIN + DisplayNames.MATERIAL;
        public static final String CHOICE_INTERNAL_NAME = "com.ptc.windchill.option.model.Choice";
        public static final String OPTION_INTERNAL_NAME = "com.ptc.windchill.option.model.Option";// OOTB Option internal name
        public static final String OPTION_SET_NAME = "OptionSet_all";
        public static final String CONFIGURATION_CHOICE_INTERNAL_NAME = BMW_DOMAIN + "Configuration$com_ptc_windchill_option_model_Choice";
        public static final String CLASS_CHOICE_INTERNAL_NAME = BMW_DOMAIN + "Class$com_ptc_windchill_option_model_Choice";
        public static final String CONFIGURATION_OPTION_INTERNAL_NAME = BMW_DOMAIN + "Configuration$com_ptc_windchill_option_model_Option";
        public static final String CLASS_OPTION_INTERNAL_NAME = BMW_DOMAIN + "Class$com_ptc_windchill_option_model_Option";
        public static final String BOLTING_CASE = BMW_DOMAIN + "BoltingCase";
        public static final String BMW_CHANGE_NOTICE = BMW_DOMAIN + "BMWChangeNotice";
        public static final String BMW_CHANGE_TASK = BMW_DOMAIN + "BMWChangeTask";
        public static final String PSMG_CHANGE_TASK = BMW_DOMAIN + "PSMGChangeTask";
        public static final String MMG_CHANGE_TASK = BMW_DOMAIN + "MMGChangeTask";
        public static final String TYPE_FILTER_INTERNAL_NAME = BMW_DOMAIN + "TypeFilter";
        public static final String CKD_FILTER_INTERNAL_NAME = BMW_DOMAIN + "CKDFilter";
        public static final String SUPPLIER_INTERNAL_NAME = BMW_DOMAIN + DisplayNames.SUPPLIER;
        public static final String IN_HOUSE_PRODUCTION = BMW_DOMAIN + "InhouseProduction";
        public static final String ASSEMBLY_LOCATION_INTERNAL_NAME = BMW_DOMAIN + "AssemblyLocation";
        public static final String ASSEMBLY_SITE_INTERNAL_NAME = BMW_DOMAIN + "AssemblySite";
        public static final String BUILDING_LOCATION_COMPONENT_INTERNAL_NAME = BMW_DOMAIN + "BuildingLocationComponent";
        public static final String SUPPLY_TYPE_INTERNAL_NAME = BMW_DOMAIN + "SupplyType";
        public static final String TAKERATE_INTERNAL_NAME = BMW_DOMAIN + DisplayNames.TAKERATE;
        public static final String LINE_SIDE_LOCATION_INTERNAL_NAME = BMW_DOMAIN + "LineSideLocation";
        public static final String PACKAGING_INTERNAL_NAME = BMW_DOMAIN + DisplayNames.PACKAGING;
        public static final String VIEW_SPECIFIC_ENTERPRISE_DATA = "com.ptc.windchill.enterprise.data.enterpriseData.PlantSpecificEnterpriseData";
        public static final String SBB_CHANGE_NOTICE = BMW_DOMAIN + "SBBChangeNotice";
        public static final String SBB_CHANGE_NOTICE_2_0 = BMW_DOMAIN + "SBBChangeNotice_2.0";
        public static final String SBB_CHANGE_TASK = BMW_DOMAIN + "SBBChangeTask";
        public static final String SBB_CHANGE_TASK_2_0 = BMW_DOMAIN + "SBBChangeTask_2.0";
        public static final String CONFIG_LINK_FILTER = BMW_DOMAIN + "ConfigLinkFilter";
        public static final String SUPPLIER_VENDOR_TYPE = "VENDOR";
        public static final String SUPPLIER_MANUFACTURER_TYPE = "MANUFACTURER";
        public static final String BUILDING_LOCATION_COMPONENT_TYPE = "BuildingLocationComponent";
        public static final String DEMAND_LOCATION_TYPE = "Demand Location";
        public static final String ASSEMBLY_SITE_TYPE = "Assembly Site";
        public static final String BMW_CHANGE_CONTEXT_PROXY = BMW_DOMAIN + "BMWChangeContextProxy";
        public static final String USAGE_LINK_TYPE = "wt.part.WTPartUsageLink";
        public static final String ASSOCIATIVE_TO_SCC_LINK_TYPE = "com.ptc.windchill.mpml.pmi.AssociativeToSCCLink";
        public static final String ALIAS_EXPRESSION_INTERNAL_NAME = "com.ptc.windchill.option.model.ExpressionAlias";
        public static final String OPTION_SET_INTERNAL_NAME = "com.ptc.windchill.option.model.OptionSet";
        public static final String TYPE_USAGE_CHANGE_NOTICE = BMW_DOMAIN + "TypeUsageChangeNotice";
        public static final String TYPE_USAGE_CHANGE_TASK = BMW_DOMAIN + "TypeUsageChangeTask";
        public static final String UNHISTORICAL_CHANGE_NOTICE = BMW_DOMAIN + "BMWUnhistoricalChangeNotice";
        public static final String UNHISTORICAL_CHANGE_TASK = BMW_DOMAIN + "BMWUnhistoricalChangeTask";
        public static final String ROMA_USER = BMW_DOMAIN + "RomaUser";
        public static final String WORK_SCOPE = BMW_DOMAIN + "WorkScope";

        private Types() {
        }

    }

    /**
     * Class contains constant WTPart numbers.
     */
    public static final class Numbers {

        public static final String ROOT = "ROOT";

        private Numbers() {
        }

    }

    /**
     * Class contains master text of display names of known types.
     */
    public static final class DisplayNames {

        public static final String NO_GENERIC_NODE = "OHNE GENERISCHE REFERENZIERUNG";
        public static final String NO_GENERIC_PART = "No Generic Part";
        public static final String GENERIC_PART = "Generic Part";
        public static final String POSITION_VARIANT = "Position Variant";
        public static final String POSITION_VARIANT_MMG = "MMG Position Variant";
        public static final String POSITION_VARIANT_MMG_DE = "MMG Positionsvariante";
        public static final String POSITION_VARIANT_PSMG = "PSMG Position Variant";
        public static final String FLEXIBLE_ASSEMBLY = "Flexible Assembly";
        public static final String MODULAR_ASSEMBLY = "Modular Assembly";
        public static final String MODULAR_SCOPE = "Modular Scope";
        public static final String COLOR_POSITION = "Color Position";
        public static final String SINGLE_PART = "Single Part";
        public static final String ASSEMBLY_KIT_HEADER = "Assembly Kit Header";
        public static final String ROOT = "Root";
        public static final String PART_USAGE_LINK = "Part Usage";
        public static final String NODE_MMG = "MMG Node";
        public static final String MATERIAL = "Material";
        public static final String FOLDER = "Folder";
        public static final String CLASSIFICATION_NODE = "Classification Node";
        public static final String BUILDING_LOCATION_COMPONENT = "Building Location Component";
        public static final String DEMAND_LOCATION_COMPONENT = "Demand Location";
        public static final String ASSEMBLY_SITE_COMPONENT = "Assembly Site";
        public static final String INHOUSE_PRODUCTION = "Inhouse Production";
        public static final String POSITION_VARIANT_HISTORICAL_LAYER = POSITION_VARIANT + " Historical Layer";
        public static final String SUPPLIER = "Supplier";
        public static final String SUPPLY_TYPE = "Supply Type";
        public static final String TAKERATE = "Takerate";
        public static final String ASSEMBLY_LOCATION = "Assembly Location";
        public static final String LINE_SIDE_LOCATION = "Line Side Location (PVB)";
        public static final String PACKAGING = "Packaging";
        public static final String SUPPLIER_DE = "Lieferant";
        public static final String SUPPLY_TYPE_DE = "Versorgung";
        public static final String TAKERATE_DE = "Verbaurate";
        public static final String ASSEMBLY_LOCATION_DE = "Verbauort";
        public static final String LINE_SIDE_LOCATION_DE = "Bereitstellort";
        public static final String PACKAGING_DE = "Verpackung";
        public static final String INHOUSE_PRODUCTION_DE = "Hausteil Fertigung";
        public static final String STATE_DELETED_EN = "Deleted";
        public static final String STATE_DELETED_DE = STATE_DELETED_EN;
        public static final String STATE_E_FIXED_EN = "E-Fixed";
        public static final String STATE_E_FIXED_DE = STATE_E_FIXED_EN;
        public static final String STATE_FIXED_EN = "Fixed";
        public static final String STATE_FIXED_DE = STATE_FIXED_EN;
        public static final String STATE_IN_REWORK_EN = "In Rework";
        public static final String STATE_IN_REWORK_DE = STATE_IN_REWORK_EN;
        public static final String STATE_ACTIVE_EN = "Active";
        public static final String STATE_ACTIVE_DE = "Aktiv";
        public static final String STATE_INACTIVE_EN = "Inactive";
        public static final String STATE_INACTIVE_DE = "Inaktiv";
        public static final String STATE_KONZ_EN = "KONZ";
        public static final String STATE_KONZ_DE = STATE_KONZ_EN;
        public static final String STATE_PRJA_EN = "PRJA";
        public static final String STATE_PRJA_DE = STATE_PRJA_EN;
        public static final String STATE_PRJG_EN = "PRJG";
        public static final String STATE_PRJG_DE = STATE_PRJG_EN;
        public static final String STATE_AVER_EN = "AVER";
        public static final String STATE_AVER_DE = STATE_AVER_EN;
        public static final String STATE_VERF_EN = "VERF";
        public static final String STATE_VERF_DE = STATE_VERF_EN;
        public static final String STATE_AVOR_EN = "AVOR";
        public static final String STATE_AVOR_DE = STATE_AVOR_EN;
        public static final String STATE_VORF_EN = "VORF";
        public static final String STATE_VORF_DE = STATE_VORF_EN;
        public static final String STATE_APRO_EN = "APRO";
        public static final String STATE_APRO_DE = STATE_APRO_EN;
        public static final String STATE_PROF_EN = "PROF";
        public static final String STATE_PROF_DE = STATE_PROF_EN;
        public static final String STATE_INWORK_DE = "Wird bearbeitet";
        public static final String STATE_INWORK_EN = "In Work";
        public static final String STATE_RELEASED_DE = "Freigegeben";
        public static final String STATE_RELEASED_EN = "Released";
        public static final String STATE_CANCELLED_OOTB_DE = "Abgebrochen";
        public static final String STATE_CANCELLED_OOTB_EN = "Canceled";
        public static final String STATE_IN_WORK_EN = "10";
        public static final String STATE_IN_WORK_DE = STATE_IN_WORK_EN;
        public static final String STATE_DEVELOPMENT_MATURITY_EN = "20";
        public static final String STATE_DEVELOPMENT_MATURITY_DE = STATE_DEVELOPMENT_MATURITY_EN;
        public static final String STATE_PRODUCTION_MATURITY_EN = "30";
        public static final String STATE_PRODUCTION_MATURITY_DE = STATE_PRODUCTION_MATURITY_EN;
        public static final String STATE_DOC_PRODUCTIVE_EN = "50";
        public static final String STATE_DOC_PRODUCTIVE_DE = STATE_DOC_PRODUCTIVE_EN;
        public static final String STATE_INACTIVE_RUN_OUT_EN = "80";
        public static final String STATE_INACTIVE_RUN_OUT_DE = STATE_INACTIVE_RUN_OUT_EN;
        public static final String STATE_CANCELLED_EN = "99";
        public static final String STATE_CANCELLED_DE = STATE_CANCELLED_EN;
        public static final String STATE_M_FIXED_EN = "M Fixed";
        public static final String STATE_M_FIXED_DE = STATE_M_FIXED_EN;
        public static final String STATE_M_READY_EN = "M Ready";
        public static final String STATE_M_READY_DE = STATE_M_READY_EN;
        public static final String STATE_T_FIXED_EN = "T Fixed";
        public static final String STATE_T_FIXED_DE = STATE_T_FIXED_EN;
        public static final String STATE_T_READY_EN = "T Ready";
        public static final String STATE_T_READY_DE = STATE_T_READY_EN;
        public static final String STATE_OBSOLETE_EN = "Obsolete";
        public static final String STATE_OBSOLETE_DE = "Obsolet";
        public static final String BMW_INTERFACES_QUEUE = "BMW Interface Queue";
        public static final String IF_MMG_SBB_001_WINDCHILL_QUEUE = "BMW IF-MMG-SBB-001 Generic Queue";
        public static final String IF_MMG_SBB_002_WINDCHILL_QUEUE = "BMW IF-MMG-SBB-002 Generic Part Queue";
        public static final String IF_MMG_SBB_004_WINDCHILL_QUEUE = "BMW IF-MMG-SBB-004 ModulOrg Queue";
        public static final String IF_MMG_SBB_100_WINDCHILL_QUEUE = "BMW IF-MMG-SBB-100 Position Variant Queue";
        public static final String IF_MMG_SBB_100_WINDCHILL_WAITING_QUEUE = "BMW IF-MMG-SBB-100 Waiting Position Variant Queue";
        public static final String IF_MMG_SBB_101_WINDCHILL_QUEUE = "BMW IF-MMG-SBB-101 Assembly Kit Queue";
        public static final String IF_MMG_SBB_101_WINDCHILL_WAITING_QUEUE = "BMW IF-MMG-SBB-101 Waiting Assembly Kit Queue";
        public static final String STATE_READY_DE = "Ready";
        public static final String STATE_READY_EN = "Ready";
        public static final String STATE_NOT_RELEVANT_EN = "Not Relevant";
        public static final String STATE_NOT_RELEVANT_DE = "Nicht Relevant";
        public static final String BMW_CHANGE_NOTICE = "BMW Change Notice";
        public static final String SECURITY_LABEL = "Security Label";
        public static final String TYPE_ASSIGNMENT = "Type Assignment";
        public static final String TYPE_USAGE_LINK = "Type Usage Link";
        public static final String VSED = "View Specific Enterprise Data";
        public static final String PSMG_CHANGE_TASK = "PSMG Change Task";
        public static final String CHANGE_ACTIVITY = "Change Activity";
        public static final String EFFECTIVITY_DATE_TYPE = "Date Effectivity";
        public static final String STATE_CREATION_DE = "Erstellung";
        public static final String STATE_CREATION_EN = "Creation";
        public static final String WORK_SCOPE = "Work Scope";
        public static final String WORK_SCOPE_DE = "Arbeitsumfang";
        public static final String SUPPLIER_VENDOR = "Vendor";
        public static final String SUPPLIER_MANUFACTURER = "Manufacturer";

        private DisplayNames() {
        }

    }

    /**
     * Class contains attribute names.
     */
    public static final class Attributes {

        public static final String BMW_EVENT_ID = "BMW_EVENT_ID";
        public static final String BMW_EFFECTIVITY = "BMW_EFFECTIVITY";
        public static final String BMW_MAT_EFFECTIVITY = "BMW_MAT_EFFECTIVITY";
        public static final String BMW_TYPE_ASSIGNMENT = "BMW_TYPE_ASSIGNMENT";
        public static final String BMW_ALL_COLOR_NO = "BMW_ALL_COLOR_NO";
        public static final String BMW_MATERIAL_NO = "BMW_MATERIAL_NO";
        public static final String BMW_MATERIAL_QUANTITY = "BMW_MATERIAL_QUANTITY";
        public static final String BMW_MATERIAL_UNIT = "BMW_MATERIAL_UNIT";
        public static final String BMW_MATERIAL_VERSION = "BMW_MATERIAL_VERSION";
        public static final String BMW_MATERIAL_NAME = "BMW_MATERIAL_NAME";
        public static final String BMW_NAEL = "BMW_NAEL";
        public static final String BMW_JIT_CODE = "BMW_JIT_CODE";
        public static final String BMW_JIT_VARIANT = "BMW_JIT_VARIANT";
        public static final String BMW_SOURCING_TYPE = "BMW_SOURCING_TYPE";
        public static final String BMW_CHARACTER_CODE = "BMW_CHARACTER_CODE";
        public static final String BMW_CHANGE_NO = "BMW_CHANGE_NO";
        public static final String BMW_LEFT_RIGHT_CODE = "BMW_LEFT_RIGHT_CODE";
        public static final String BMW_L_CODE = "BMW_L_CODE";
        public static final String BMW_PART_TYPE = "BMW_PART_TYPE";
        public static final String BMW_PDM_CHANGE_NO = "BMW_PDM_CHANGE_NO";
        public static final String BMW_PDM_GUID = "BMW_PDM_GUID";
        public static final String BMW_SERIES_PROD_PART_WEIGHT = "BMW_SERIES_PROD_PART_WEIGHT";
        public static final String BMW_CLASS = "BMW_CLASS";
        public static final String BMW_SUBTYPE = "BMW_SUBTYPE";
        public static final String BMW_PRODUCT_PROCESS_GRP = "BMW_PRODUCT_PROCESS_GRP";
        public static final String BMW_TYPE = "BMW_TYPE";
        public static final String BMW_NODENUMBER = "BMW_NODENUMBER";
        public static final String BMW_MODULEORG = "BMW_MODULEORG";
        public static final String BMW_MODULE = "BMW_MODULE";
        public static final String BMW_UNIFIED_PARTS_GRP = "BMW_UNIFIED_PARTS_GRP";
        public static final String BMW_COMMODITY = "BMW_COMMODITY";
        public static final String BMW_PDM_TIMESTAMP = "BMW_PDM_TIMESTAMP";
        public static final String BMW_PDM_PARENT_GUID = "BMW_PDM_PARENT_GUID";
        public static final String BMW_PDM_PARENT_NAME = "BMW_PDM_PARENT_NAME";
        public static final String BMW_PDM_GENERIC_PARENT_GUID = "BMW_PDM_GENERIC_PARENT_GUID";
        public static final String BMW_DEPARTMENT = "BMW_DEPARTMENT";
        public static final String BMW_PDM_COUNT = "BMW_PDM_COUNT";
        public static final String BMW_RELEASE_EFFECTIVITY = "BMW_RELEASE_EFFECTIVITY";
        public static final String BMW_ASIS_DEV_OF_DIM = "BMW_ASIS_DEV_OF_DIM";
        public static final String BMW_STRUCTURE_KEY = "BMW_STRUCTURE_KEY";
        public static final String STATE_STATE = "state.state";
        public static final String NAME = "name";
        public static final String NUMBER = "number";
        public static final String VIEW = "view";
        public static final String VIEW_ID = "view.id";
        public static final String CHECKOUT_INFO_STATE = "checkoutInfo.state";
        public static final String MASTER_REFERENCE = "masterReference";
        public static final String BMW_STD_DESC = "BMW_STD_DESC";
        public static final String BMW_PDM_GENERIC_PART_GUID = "BMW_PDM_GENERIC_PART_GUID";
        public static final String BMW_SERIAL_MASTER = "BMW_SERIAL_MASTER";
        public static final String BMW_SERIAL = "BMW_SERIAL";
        public static final String BMW_RELEASE_NOTE = "BMW_RELEASE_NOTE";
        public static final String BMW_COLOR_CODE = "BMW_COLOR_CODE";
        public static final String BMW_COLOR_CONTROL_CODE = "BMW_COLOR_CONTROL_CODE";
        public static final String BMW_SUPPL_COLOR_DEVIATIONS = "BMW_SUPPL_COLOR_DEVIATIONS";
        public static final String BMW_DOC_INFO_RECORD = "BMW_DOC_INFO_RECORD";
        public static final String BMW_PLANT = "BMW_PLANT";
        public static final String BMW_COMP_PLANT="BMW_COMP_PLANT";
        public static final String BMW_PLANT_DESCRIPTION="BMW_PLANT_DESCRIPTION";
        public static final String BMW_STARD_DESC="BMW_STARD_DESC";
        public static final String BMW_STARD_ID="BMW_STARD_ID";
        public static final String BMW_VEHICLE_PLANT="BMW_VEHICLE_PLANT";
        public static final String BMW_SUPPLIER="BMW_SUPPLIER";
        public static final String BMW_PLANT_KEY = "BMW_PLANT_KEY";
        public static final String BMW_TYP_EOP = "BMW_TYP_EOP";
        public static final String BMW_TYP_SOP = "BMW_TYP_SOP";
        public static final String BMW_TYPE_CATEGORY = "BMW_TYPE_CATEGORY";
        public static final String BMW_MULTIPLE_USAGE = "BMW_MULTIPLE_USAGE";
        public static final String BMW_SMALL_PART = "BMW_SMALL_PART";
        public static final String BMW_GENERIC_PART_ID = "BMW_GENERIC_PART_ID";
        public static final String BMW_GENERIC_PART_GUID = "BMW_GENERIC_PART_GUID";
        public static final String BMW_ENGINE_SIZE = "BMW_ENGINE_SIZE";
        public static final String BMW_PCP_CATEGORY = "BMW_PCP_CATEGORY";
        public static final String BMW_DESIGN_SCOPE = "BMW_DESIGN_SCOPE";
        public static final String BMW_CLASS_COMMODITY = "COMMODITY";
        public static final String BMW_CLASS_MULTIPLE_USAGE = "BMW_CLASS_MULTIPLE_USAGE";
        public static final String BMW_CLASS_SMALL_PART = "BMW_CLASS_SMALL_PART";
        public static final String BMW_CLASS_GENERIC_PART_ID = "BMW_CLASS_GENERIC_PART_ID";
        public static final String BMW_CLASS_GENERIC_PART_GUID = "BMW_CLASS_GENERIC_PART_GUID";
        public static final String BMW_CLASS_STD_DESC = "BMW_CLASS_STD_DESC";
        public static final String BMW_CLASS_UPG = "UPG";
        public static final String BMW_CLASS_DEPARTMENT = "BMW_CLASS_DEPARTMENT";
        public static final String BMW_CLASS_PCP_CATEGORY = "BMW_CLASS_PCP_CATEGORY";
        public static final String BMW_CLASS_ENGINE_SCOPE = "BMW_CLASS_ENGINE_SCOPE";
        public static final String BMW_CLASS_MODULEORG = "MODULEORG";
        public static final String BMW_CLASS_DESIGN_SCOPE = "BMW_CLASS_DESIGN_SCOPE";
        public static final String BMW_PRIORITIZED_WEIGHT = "BMW_PRIORITIZED_WEIGHT";
        public static final String BMW_WEIGHT_TYPE = "BMW_WEIGHT_TYPE";
        public static final String BMW_CREATE_TIMESTAMP = "BMW_CREATE_TIMESTAMP";
        public static final String BMW_PV_GUID = "BMW_PV_GUID";
        public static final String BMW_PVH_GUID = "BMW_PVH_GUID";
        public static final String BMW_GENERIC_CLASS = "BMW_GENERIC_CLASS";
        public static final String BMW_MODULEORG_CLASS = "BMW_MODULEORG_CLASS";
        public static final String BMW_UPG_CLASS = "BMW_UPG_CLASS";
        public static final String BMW_COMMODITY_CLASS = "BMW_COMMODITY_CLASS";
        public static final String BMW_TYP_USAGE_AEKONTX = "BMW_TYP_USAGE_AEKONTX";
        public static final String BMW_TYP_USAGE_RELOBJID = "BMW_TYP_USAGE_RELOBJID";
        public static final String BMW_TYP_USAGE_DATE = "BMW_TYP_USAGE_DATE";
        public static final String BMW_TYP_USAGE_STATE = "BMW_TYP_USAGE_STATE";
        public static final String BMW_TYP_INFORMATION = "BMW_TYP_INFORMATION";
        public static final String BMW_TYP_KEY = "BMW_TYP_KEY";
        public static final String BMW_TYPE_USAGE = "BMW_TYPE_USAGE";
        public static final String BMW_TYP_USAGE_KEY = "BMW_TYP_USAGE_KEY";
        public static final String BMW_TYP_USAGE_INFO = "BMW_TYP_USAGE_INFO";
        public static final String BMW_TYPE_USAGE_SOP = "BMW_TYPE_USAGE_SOP";
        public static final String BMW_TYPE_USAGE_EOP = "BMW_TYPE_USAGE_EOP";
        public static final String BMW_TYPE_USAGE_PLANT = "BMW_TYPE_USAGE_PLANT";
        public static final String BMW_TYP_USAGE_PLANT_SOP = "BMW_TYP_USAGE_PLANT_SOP";
        public static final String BMW_TYP_USAGE_PLANT_EOP = "BMW_TYP_USAGE_PLANT_EOP";
        public static final String BMW_TYP_USAGE_EFF_VORF = "BMW_TYP_USAGE_EFF_VORF";
        public static final String BMW_TYP_USAGE_EFF_PROF = "BMW_TYP_USAGE_EFF_PROF";
        public static final String BMW_TYP_USAGE_EFF_BAW = "BMW_TYP_USAGE_EFF_BAW";
        public static final String BMW_TYP_USAGE_EFF_ENTFALL = "BMW_TYP_USAGE_EFF_ENTFALL";
        public static final String BMW_TYP_USAGE_CHANGETASK = "BMW_TYP_USAGE_CHANGETASK";
        public static final String BMW_TYP_USAGE_CHANGETASK_STATE = "BMW_TYP_USAGE_CHANGETASK_STATE";
        public static final String BMW_TARGET_USAGE_MATURITY = "BMW_TARGET_USAGE_MATURITY";
        public static final String BMW_TYP_USAGE_TARGET_EFF = "BMW_TYP_USAGE_TARGET_EFF";
        public static final String BMW_3_CYCLE = "BMW_3_CYCLE";
        public static final String BMW_BK_SUPPLIERTYPE = "BMW_BK_SUPPLIERTYPE";
        public static final String BMW_BK_SUPPLIERID = "BMW_BK_SUPPLIERID";
        public static final String BMW_BK_PLANTDESCRIPTION = "BMW_BK_PLANTDESCRIPTION";
        public static final String BMW_BK_PLANT = "BMW_BK_PLANT";
        public static final String BMW_BK_PLANTKEY = "BMW_BK_PLANTKEY";
        public static final String BMW_BK_PLANTLOCATION = "BMW_BK_PLANTLOCATION";
        public static final String BMW_LABEL_APPLICATION_LOC = "BMW_LABEL_APPLICATION_LOC";
        public static final String BMW_LABEL_LEGALLY_STIP = "BMW_LABEL_LEGALLY_STIP";
        public static final String BMW_LABEL_APP_LOC_LEG_STIP = "BMW_LABEL_APP_LOC_LEG_STIP";
        public static final String BMW_LABEL_INSTALLATION_LOC = "BMW_LABEL_INSTALLATION_LOC";
        public static final String BMW_LABEL_REQUESTOR = "BMW_LABEL_REQUESTOR";
        public static final String BMW_LABEL_CONSTRUCTION = "BMW_LABEL_CONSTRUCTION";
        public static final String BMW_LABEL_RELEASE = "BMW_LABEL_RELEASE";
        public static final String BMW_PORTAL_M_ID = "BMW_PORTAL_M_ID";
        public static final String BMW_PREL_COMMENTARY_DE = "BMW_PREL_COMMENTARY_DE";
        public static final String BMW_PREL_COMMENTARY_EN = "BMW_PREL_COMMENTARY_EN";
        public static final String BMW_SUBS_COMMENTARY_DE = "BMW_SUBS_COMMENTARY_DE";
        public static final String BMW_SUBS_COMMENTARY_EN = "BMW_SUBS_COMMENTARY_EN";
        public static final String BMW_COLOR_EXC_CODE = "BMW_COLOR_EXC_CODE";
        public static final String BMW_TIGHT_TORQUE = "BMW_TIGHT_TORQUE";
        public static final String BMW_MAX_TIGHT_TORQUE = "BMW_MAX_TIGHT_TORQUE";
        public static final String BMW_MIN_TIGHT_TORQUE = "BMW_MIN_TIGHT_TORQUE";
        public static final String BMW_MIN_PRETEN_FORCE = "BMW_MIN_PRETEN_FORCE";
        public static final String BMW_BOLTING_CLASS = "BMW_BOLTING_CLASS";
        public static final String BMW_BOLTING_CATEGORY = "BMW_BOLTING_CATEGORY";
        public static final String BMW_TIGHT_ANGLE = "BMW_TIGHT_ANGLE";
        public static final String BMW_ANGLE_TOLERANCE = "BMW_ANGLE_TOLERANCE";
        public static final String BMW_ENGINEER = "BMW_ENGINEER";
        public static final String BMW_ENGINEER_DEPT = "BMW_ENGINEER_DEPT";
        public static final String BMW_TIGHT_PROC = "BMW_TIGHT_PROC";
        public static final String BMW_REUSABLE = "BMW_REUSABLE";
        public static final String BMW_TIGHT_ELEMENT = "BMW_TIGHT_ELEMENT";
        public static final String BMW_RIVET_NUT_BOLT = "BMW_RIVET_NUT_BOLT";
        public static final String BMW_SET_FORCE_PATH = "BMW_SET_FORCE_PATH";
        public static final String BMW_DEV_SET_FORCE_PATH = "BMW_DEV_SET_FORCE_PATH";
        public static final String BMW_BOLTING_CASE_MOTORBIKE = "BMW_BOLTING_CASE_MOTORBIKE";
        public static final String BMW_EST_MAX_TIGHT_TORQUE = "BMW_EST_MAX_TIGHT_TORQUE";
        public static final String BMW_GRAD_CONTR_SHUT_DOWN_FOR = "BMW_GRAD_CONTR_SHUT_DOWN_FOR";
        public static final String BMW_MAX_ELONGATION = "BMW_MAX_ELONGATION";
        public static final String BMW_MIN_ELONGATION = "BMW_MIN_ELONGATION";
        public static final String BMW_TIGHT_SELF_TAP_SCREW = "BMW_TIGHT_SELF_TAP_SCREW";
        public static final String BMW_SCREW_PROTECTION = "BMW_SCREW_PROTECTION";
        public static final String BMW_SPEC_FOR_REWORK = "BMW_SPEC_FOR_REWORK";
        public static final String BMW_SPEC_FOR_SERVICE = "BMW_SPEC_FOR_SERVICE";
        public static final String BMW_BK = "BMW_BK";
        public static final String BMW_COUNTRY_CODE = "BMW_COUNTRY_CODE";
        public static final String BMW_BK_COUNTRYCODE = "BMW_BK_COUNTRYCODE";
        public static final String BMW_MO = "BMW_MO";
        public static final String BMW_MO_PLANT = "BMW_MO_PLANT";
        public static final String BMW_BO = "BMW_BO";
        public static final String BMW_BAND = "BMW_BAND";
        public static final String BMW_ASSEMBLY_STATION = "BMW_ASSEMBLY_STATION";
        public static final String BMW_SUPPLIER_DELIVERY_LOCATION = "BMW_SUPPLIER_DELIVERY_LOCATION";
        public static final String BMW_SUPPLIER_PRODUCTION_LOCATION = "BMW_SUPPLIER_PRODUCTION_LOCATION";
        public static final String BMW_PLANT_CODE = "BMW_PLANT_CODE";
        public static final String BMW_IDOC_NUMBER = "BMW_IDOC_NUMBER";
        public static final String CONTAINER_REF_ATTRIBUTE = "containerReference";
        public static final String CONTAINER_NAME = "containerName";
        public static final String BMW_IS_PARENT_CN = "BMW_IS_PARENT_CN";
        public static final String BMW_RELEASE = "BMW_RELEASE";
        public static final String BMW_RELEASE_SYNCHRONIZATION_DATE = "BMW_RELEASE_SYNCHRONIZATION_DATE";
        public static final String BMW_LFEAI = "BMW_LFEAI";
        public static final String BMW_BEZWI_DIFF_ASSIGNMENT = "BMW_BEZWI_DIFF_ASSIGNMENT";
        public static final String CONFIGURABLE_MODULE = "genericType";

        /* Attributes for BMW Change Notice for Technical Completeness */
        public static final String BMW_MESSAGE_COUNT = "BMW_MESSAGE_COUNT";
        public static final String BMW_MESSAGE_SEQUENCE = "BMW_MESSAGE_SEQUENCE";
        public static final String BMW_TOTAL_NUMBER_MESSAGES = "BMW_TOTAL_NUMBER_MESSAGES";
        public static final String BMW_TYP_T_STATUS = "BMW_TYP_T_STATUS";
        public static final String BMW_TYP_M_STATUS = "BMW_TYP_M_STATUS";
        public static final String BMW_DESC_DE = "BMW_DESC_DE";
        public static final String BMW_DESC_EN = "BMW_DESC_EN";
        public static final String BMW_DEV_SPEC_FOR_REWORK_DE = "BMW_DEV_SPEC_FOR_REWORK_DE";
        public static final String BMW_DEV_SPEC_FOR_REWORK_EN = "BMW_DEV_SPEC_FOR_REWORK_EN";
        public static final String BMW_DEV_SPEC_FOR_SERVICE_DE = "BMW_DEV_SPEC_FOR_SERVICE_DE";
        public static final String BMW_DEV_SPEC_FOR_SERVICE_EN = "BMW_DEV_SPEC_FOR_SERVICE_EN";
        public static final String PART_ITEM_ASSIGNED_EXPRESSION = "partItemAssignedExpression";
        public static final String VERSION_ID = "versionInfo.identifier.versionId";
        public static final String ITERATION_ID = "iterationInfo.identifier.iterationId";
        public static final String BMW_COMPONENT_TYPE_FLAG = "BMW_COMPONENT_TYPE_FLAG";
        public static final String BMW_LAST_UPDATED = "BMW_LAST_UPDATED";
        public static final String BMW_DRAWING_NOTE = "BMW_DRAWING_NOTE";
        public static final String BMW_DRAWING_FORMAT = "BMW_DRAWING_FORMAT";
        public static final String BMW_PART_MATERIAL = "BMW_PART_MATERIAL";
        public static final String BMW_VCR = "BMW_VCR";
        public static final String BMW_TEC_CLEANLINESS = "BMW_TEC_CLEANLINESS";
        public static final String BMW_PART_NUMBER_STATUS = "BMW_PART_NUMBER_STATUS";
        public static final String BMW_MIRROR_INVERTED_ID = "BMW_MIRROR_INVERTED_ID";
        public static final String BMW_PART_NUMBER_TYPE = "BMW_PART_NUMBER_TYPE";
        public static final String BMW_PART_NUMBER_DEPARTMENT = "BMW_PART_NUMBER_DEPARTMENT";
        public static final String BMW_PART_SURFACE = "BMW_PART_SURFACE";
        public static final String BMW_ABBREV_DESCRIPTION = "BMW_ABBREV_DESCRIPTION";
        public static final String BMW_DESIGNER = "BMW_DESIGNER";
        public static final String BMW_PART_NUMBER_GROUP = "BMW_PART_NUMBER_GROUP";
        public static final String BMW_HW_SW_CODE = "BMW_HW_SW_CODE";
        public static final String BMW_TRANSMISSION_CODE = "BMW_TRANSMISSION_CODE";
        public static final String BMW_GENERIC_PART = "BMW_GENERIC_PART";
        public static final String BMW_HAZARDOUS_GOODS_MARKING = "BMW_HAZARDOUS_GOODS_MARKING";
        public static final String BMW_ESD_MARKING = "BMW_ESD_MARKING";
        public static final String BMW_SPARE_PART_CODE = "BMW_SPARE_PART_CODE";
        public static final String BMW_PREDECESSOR_PART_NUMBER = "BMW_PREDECESSOR_PART_NUMBER";
        public static final String BMW_DMU_MODELL = "BMW_DMU_MODELL";
        public static final String BMW_EDITOR = "BMW_EDITOR";
        public static final String BMW_ORIGINAL_PART_NUMBER = "BMW_ORIGINAL_PART_NUMBER";
        public static final String BMW_DATE_OF_CREATION = "BMW_DATE_OF_CREATION";
        public static final String BMW_INIT_USE_MODEL = "BMW_INIT_USE_MODEL";
        public static final String BMW_FIRST_UPG = "BMW_FIRST_UPG";
        public static final String BMW_RECYCLING_CLASS = "BMW_RECYCLING_CLASS";
        public static final String BMW_MATERIAL_COLOR_CODE = "BMW_MATERIAL_COLOR_CODE";
        public static final String COMMENT = "COMMENT";
        public static final String CATEGORY = "category";
        /* Attributes for ERelease */
        public static final String BMW_RELEASE_REQUEST_ID = "BMW_RELEASE_REQUEST_ID";
        public static final String BMW_DERIVATE = "BMW_DERIVATE";
        public static final String BMW_MODULE_ORG = "BMW_MODULE_ORG";
        public static final String DERIVATIVE_PERMISSION = "DERIVATIVE_PERMISSION";
        public static final String BMW_INPUT_RELEASE = "BMW_INPUT_RELEASE";
        public static final String TARGET_USAGE_MATURITY_DEFAULT_VALUE = "UNDEFINED";
        public static final String BMW_APPROVAL_TYPE = "BMW_APPROVAL_TYPE";
        public static final String TYPE_USAGE_APPROVAL = "TYPEUSAGEAPPROVAL";
        /* Attributes for WRS */
        public static final String BMW_APPROVAL_OBJECT_NUMBER = "BMW_APPROVAL_OBJECT_NUMBER";
        public static final String BMW_TYP_USAGE_APPROVERS = "BMW_TYP_USAGE_APPROVERS";
        public static final String BMW_CC_NUMBER = "BMW_CC_NUMBER";
        public static final String BMW_CHANGE_CONTEXT_ID = "BMW_CC_ID";
        public static final String FOLDER_ID = "folder.id";
        public static final String DEFAULT_UNIT = "defaultUnit";
        public static final String PFD_CATEGORY_ENUM = "BMWCategory";
        public static final String PFD_DIFFERENTIATION = "BMWDifferentiation";
        public static final String PFD_LINE_ENUMERATION = "com.bmw.psmg.sbb.enumeration.dynamic.LineEnumeration";
        public static final String BMW_CC_ID = "BMW_CC_ID";

        /* Attributes for Roma User */
        public static final String BMW_FUNCTIONAL_GROUPS = "BMW_FUNCTIONAL_GROUPS";
        public static final String BMW_DIFFTYPE_GROUPS = "BMW_DIFFTYPE_GROUPS";
        public static final String BMW_LICENSE_GROUPS = "BMW_LICENSE_GROUPS";
        public static final String BMW_ROLES = "BMW_ROLES";
        public static final String BMW_PROJECTS = "BMW_PROJECTS";
        public static final String BMW_VSED_TYPE = "BMW_VSED_TYPE";

        public static final String ORG_DESCRIPTION = "orgDescription";
        public static final String BMW_DISPLAY = "BMW_DISPLAY";


        private Attributes() {
        }

    }

    /**
     * Class contains icon paths of known types.
     */
    public static final class IconPaths {

        private static final String ICON_BASE_PATH = "netmarkets/images/com/bmw/psmg/sbb/types/";
        public static final String MATERIAL_ICON_PATH = ICON_BASE_PATH + "material_rot.gif";
        public static final String ALL_COLOR_MATERIAL_ICON_PATH = ICON_BASE_PATH + "material_grey.gif";
        public static final String POSITION_VARIANT_MMG_ICON_PATH = ICON_BASE_PATH + "posvar_blau.gif";
        public static final String POSITION_VARIANT_MMG_MATERIAL_ICON_PATH = ICON_BASE_PATH + "posvar_blau_mat_rot.gif";
        public static final String POSITION_VARIANT_MMG_ALL_COLOR_MATERIAL_ICON_PATH = ICON_BASE_PATH + "posvar_blau_mat_grey.gif";
        public static final String POSITION_VARIANT_PSMG_ICON_PATH = ICON_BASE_PATH + "posvar_lila.gif";
        public static final String POSITION_VARIANT_PSMG_MATERIAL_ICON_PATH = ICON_BASE_PATH + "posvar_lila_mat_rot.gif";
        public static final String POSITION_VARIANT_ASSEMBLY_KIT_HEADER_ICON_PATH = ICON_BASE_PATH + "strukturknoten_bracket_empty.gif";
        public static final String POSITION_VARIANT_ASSEMBLY_KIT_HEADER_MATERIAL_ICON_PATH = ICON_BASE_PATH + "strukturknoten_bracket_mat_rot.gif";
        public static final String POSITION_VARIANT_ASSEMBLY_KIT_HEADER_ALL_COLOR_MATERIAL_ICON_PATH = ICON_BASE_PATH + "strukturknoten_bracket_mat_grey.gif";
        public static final String WORK_SCOPE_ICON_PATH = ICON_BASE_PATH + "work_scopes.png";

        private IconPaths() {
        }

    }

    /**
     * Class contains container names
     */
    public static final class ContainerNames {

        public static final String DEPENDENCIES_LIBRARY = "Merkmalsbibliothek";
        public static final String MATERIAL_PRODUCT = "Materialstamm";
        public static final String GENERIC_PRODUCT = "GENERIK";
        public static final String ASSEMBLY_KITS_PRODUCT = "Assembly Kits";
        public static final String TEST_ASSEMBLY_KITS_PRODUCT = "Assembly Kits Test";
        public static final String TEST_GENERIC_PRODUCT = "GENERIK_TEST";
        public static final String TEST_PRODUCT = "PSMGSBB Test Product";
        public static final String BOLTING_CASES = "Bolting Cases";
        public static final String TEST_BOLTING_CASES = "Bolting Cases Test";
        public static final String SST_TEST_PRODUCT = "SST_Test_Product";
        public static final String SST_TEST_PRODUCT_2 = "SST_Test_Product_2";
        public static final String MISSING_PRODUCT = "Missing_Product";
        public static final String CHANGE_MANAGEMENT_LIBRARY = "Change Management";
        public static final String TEST_CHANGE_MANAGEMENT_LIBRARY = "Change Management Test";

        private ContainerNames() {
        }

    }

    /**
     * Class contains Organizer names
     */
    public static final class Organizers {

        public static final String BMW_ATTRIBUTES = BMW_DOMAIN + "BMWAttributes";
        public static final String PART_ATTRIBUTES = BMW_DOMAIN + "PartAttributes";

        private Organizers() {
        }

    }

    /**
     * Class contains workflow names used in SBB
     */
    public static final class Workflows {

        public static final String SBB_VERIFY_FUNCTIONAL_CONSISTENCY = "SBB Verify Functional Consistency";
        public static final String SBB_COMPONENT_PLANT_INFORMATIVE_WORKFLOW = "SBB Component Plant Intimation Workflow";

        private Workflows() {
        }
    }

    /**
     * Class contains Team Template names used in SBB
     */
    public static final class TeamTemplates {

        public static final String BMW_WORK_SCOPE_TEAM = "BMW Work Scope Team";

        private TeamTemplates() {
        }
    }

    /**
     * Class contains JMS queue webject parameters
     */
    public static final class WebjectParameters {

        public static final String HOST = "HOST";
        public static final String PORT = "PORT";
        public static final String CHANNEL = "CHANNEL";
        public static final String DESTINATION = "DESTINATION";
        public static final String QM = "QM";
        public static final String USER = "USER";
        public static final String EXECUTE_TASK = "EXECUTE_TASK";
        public static final String XSL_URL = "XSL_URL";
        public static final String GROUP_NAME = "GROUP_NAME";
        public static final String INTERFACE_NAME = "INTERFACE_NAME";
        public static final String SBB001_HOST = "com.bmw.psmg.sbb.interfaces.mmg.mmg001.queueManager.host";
        public static final String SBB001_PORT = "com.bmw.psmg.sbb.interfaces.mmg.mmg001.queueManager.port";
        public static final String SBB001_CHANNEL = "com.bmw.psmg.sbb.interfaces.mmg.mmg001.queueManager.channel";
        public static final String SBB001_QUEUE = "com.bmw.psmg.sbb.interfaces.mmg.mmg001.queueManager.queue";
        public static final String SBB001_QNAME = "com.bmw.psmg.sbb.interfaces.mmg.mmg001.queueManager.name";
        public static final String SBB001_USER = "com.bmw.psmg.sbb.interfaces.mmg.mmg001.queueManager.user";
        public static final String SBB100_HOST = "com.bmw.psmg.sbb.interfaces.mmg.mmg100.queueManager.host";
        public static final String SBB100_PORT = "com.bmw.psmg.sbb.interfaces.mmg.mmg100.queueManager.port";
        public static final String SBB100_CHANNEL = "com.bmw.psmg.sbb.interfaces.mmg.mmg100.queueManager.channel";
        public static final String SBB100_QUEUE = "com.bmw.psmg.sbb.interfaces.mmg.mmg100.queueManager.queue";
        public static final String SBB100_QNAME = "com.bmw.psmg.sbb.interfaces.mmg.mmg100.queueManager.name";
        public static final String SBB100_USER = "com.bmw.psmg.sbb.interfaces.mmg.mmg100.queueManager.user";
        public static final String SBB101_HOST = "com.bmw.psmg.sbb.interfaces.mmg.mmg101.queueManager.host";
        public static final String SBB101_PORT = "com.bmw.psmg.sbb.interfaces.mmg.mmg101.queueManager.port";
        public static final String SBB101_CHANNEL = "com.bmw.psmg.sbb.interfaces.mmg.mmg101.queueManager.channel";
        public static final String SBB101_QUEUE = "com.bmw.psmg.sbb.interfaces.mmg.mmg101.queueManager.queue";
        public static final String SBB101_QNAME = "com.bmw.psmg.sbb.interfaces.mmg.mmg101.queueManager.name";
        public static final String SBB101_USER = "com.bmw.psmg.sbb.interfaces.mmg.mmg101.queueManager.user";
        public static final String SBB102_HOST = "com.bmw.psmg.sbb.interfaces.mmg.mmg102.queueManager.host";
        public static final String SBB102_PORT = "com.bmw.psmg.sbb.interfaces.mmg.mmg102.queueManager.port";
        public static final String SBB102_CHANNEL = "com.bmw.psmg.sbb.interfaces.mmg.mmg102.queueManager.channel";
        public static final String SBB102_QUEUE = "com.bmw.psmg.sbb.interfaces.mmg.mmg102.queueManager.queue";
        public static final String SBB102_QNAME = "com.bmw.psmg.sbb.interfaces.mmg.mmg102.queueManager.name";
        public static final String SBB102_USER = "com.bmw.psmg.sbb.interfaces.mmg.mmg102.queueManager.user";
        public static final String SBB002_HOST = "com.bmw.psmg.sbb.interfaces.mat.mat002.queueManager.host";
        public static final String SBB002_PORT = "com.bmw.psmg.sbb.interfaces.mat.mat002.queueManager.port";
        public static final String SBB002_CHANNEL = "com.bmw.psmg.sbb.interfaces.mat.mat002.queueManager.channel";
        public static final String SBB002_QUEUE = "com.bmw.psmg.sbb.interfaces.mat.mat002.queueManager.queue";
        public static final String SBB002_QNAME = "com.bmw.psmg.sbb.interfaces.mat.mat002.queueManager.name";
        public static final String SBB002_USER = "com.bmw.psmg.sbb.interfaces.mat.mat002.queueManager.user";
        public static final String MQ_SECRET = "com.bmw.psmg.sbb.interfaces.mmg.queueManager.password";
        public static final String MQ_SSL_CIPHER_SUITE = "com.bmw.psmg.sbb.interfaces.mmg.queueManager.sslciphersuite";
        public static final String MQ_SSL_CIPHER_SPEC = "com.bmw.psmg.sbb.interfaces.mmg.queueManager.sslcipherspec";
        public static final String NAV_CRIT_REFERENCE = "navigationCriteriaReference";
        public static final String CHOICES = "choice";
        public static final String EFFECTIVITY_TYPE = "EFFECTIVITY_TYPE";
        public static final String EFFECTIVITY_RESET_EXISTING = "RESET_EXISTING_EFFECTIVITY";
        public static final String MODIFICATION = "MODIFICATION";

        private WebjectParameters() {
        }

    }

    /**
     * Class contains JMS queue webject parameters {IF-MAT-SBB-002 - MatrX Interface Material}
     */
    public static final class MatrxWebjectParameters {

        public static final String HOST = "HOST";
        public static final String PORT = "PORT";
        public static final String CHANNEL = "CHANNEL";
        public static final String DESTINATION = "DESTINATION";
        public static final String QM = "QM";
        public static final String USER = "USER";
        public static final String EXECUTE_TASK = "EXECUTE_TASK";
        public static final String XSL_URL = "XSL_URL";
        public static final String GROUP_NAME = "GROUP_NAME";
        public static final String INTERFACE_NAME = "INTERFACE_NAME";
        public static final String MAT002_HOST = "com.bmw.psmg.sbb.interfaces.mat.mat002.queueManager.host";
        public static final String MAT002_PORT = "com.bmw.psmg.sbb.interfaces.mat.mat002.queueManager.port";
        public static final String MAT002_CHANNEL = "com.bmw.psmg.sbb.interfaces.mat.mat002.queueManager.channel";
        public static final String MAT002_QNAME = "com.bmw.psmg.sbb.interfaces.mat.mat002.queueManager.name";
        public static final String MAT002_USER = "com.bmw.psmg.sbb.interfaces.mat.mat002.queueManager.user";
        public static final String MAT002_SECRET = "com.bmw.psmg.sbb.interfaces.mat.mat002.queueManager.password";
        public static final String MAT002_SSL_CIPHER_SUITE = "com.bmw.psmg.sbb.interfaces.mat.mat002.queueManager.sslciphersuite";
        public static final String MAT002_SSL_CIPHER_SPEC = "com.bmw.psmg.sbb.interfaces.mat.mat002.queueManager.sslcipherspec";

        /* Queue Details for Matrx */
        public static final String MAT002_PP_QUEUE = "com.bmw.psmg.sbb.interfaces.mat.mat002.queueManager.pp.queue";
        public static final String MAT002_PH_QUEUE = "com.bmw.psmg.sbb.interfaces.mat.mat002.queueManager.ph.queue";
        public static final String MAT002_PN_QUEUE = "com.bmw.psmg.sbb.interfaces.mat.mat002.queueManager.pn.queue";
        public static final String MAT002_PM_QUEUE = "com.bmw.psmg.sbb.interfaces.mat.mat002.queueManager.pm.queue";
        public static final String MAT002_PZ_QUEUE = "com.bmw.psmg.sbb.interfaces.mat.mat002.queueManager.pz.queue";
        public static final String MAT002_PR_QUEUE = "com.bmw.psmg.sbb.interfaces.mat.mat002.queueManager.pr.queue";
        public static final String MAT002_PE_QUEUE = "com.bmw.psmg.sbb.interfaces.mat.mat002.queueManager.pe.queue";
        public static final String MAT002_PA_QUEUE = "com.bmw.psmg.sbb.interfaces.mat.mat002.queueManager.pa.queue";
        public static final String MAT002_PS_QUEUE = "com.bmw.psmg.sbb.interfaces.mat.mat002.queueManager.ps.queue";
        public static final String MAT002_PV_QUEUE = "com.bmw.psmg.sbb.interfaces.mat.mat002.queueManager.pv.queue";
        public static final String MAT002_PW_QUEUE = "com.bmw.psmg.sbb.interfaces.mat.mat002.queueManager.pw.queue";

        private MatrxWebjectParameters() {
        }

    }



    /**
     * enum for Type Usage Link Effectivity Attribute mapping
     */
    public enum TypeUsageLinkEffectivityAttributeName {

        VORF(Attributes.BMW_TYP_USAGE_EFF_VORF), PROF(Attributes.BMW_TYP_USAGE_EFF_PROF), BAW(Attributes.BMW_TYP_USAGE_EFF_BAW), ENTFALL(Attributes.BMW_TYP_USAGE_EFF_ENTFALL);

        private final String typeUsageLinkAttributeName;

        TypeUsageLinkEffectivityAttributeName(String s) {
            this.typeUsageLinkAttributeName = s;
        }

        @Override
        public String toString() {
            return this.typeUsageLinkAttributeName;
        }
    }

    /**
     * enum for Approval Processes
     */
    public enum ApprovalProcess {

        PRODUCTIONAPPROVAL("PRODUCTIONAPPROVAL");

        private final String approvalProcessType;

        ApprovalProcess(String s) {
            this.approvalProcessType = s;
        }

        @Override
        public String toString() {
            return this.approvalProcessType;
        }
    }
}
