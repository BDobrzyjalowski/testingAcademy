<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<qml bypassAccessControl="false" caseInsensitive="true" addTimeToDateFields="false" forAdvancedSearch="true" mainType="MMG Position Variant" joinModel="false" xsi:noNamespaceSchemaLocation="qml.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <query>
        <selectOrConstrain distinct="false" group="false">
            <layoutSectionHeader layoutSectionId="View_Specific_Enterprise_Data" text="View Specific Enterprise Data"/>
            <reportAttribute heading="Plant" reportAttributeId="Name" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="View" isExternal="false" type="java.lang.String" propertyName="name">name</column>
            </reportAttribute>
            <layoutSectionHeader layoutSectionId="Department_Data_Master" text="Department Data Master"/>
            <reportAttribute heading="Category" reportAttributeId="Category" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Plant Specific Functional Data Master" isExternal="false" type="java.lang.String" propertyName="category">category</column>
            </reportAttribute>
            <reportAttribute heading="Hall/Line" reportAttributeId="Alternate1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Plant Specific Functional Data Master" isExternal="false" type="java.lang.String" propertyName="alternate1">alternate1</column>
            </reportAttribute>
            <reportAttribute heading="Differentiation" reportAttributeId="Alternate2" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Plant Specific Functional Data Master" isExternal="false" type="java.lang.String" propertyName="alternate2">alternate2</column>
            </reportAttribute>
            <layoutSectionHeader layoutSectionId="Assembly_Location" text="Building Location Component"/>
            <reportAttribute heading="Description" reportAttributeId="Description_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Building Location Component" isExternal="false" type="java.lang.String" propertyName="description">description</column>
            </reportAttribute>
            <reportAttribute heading="BK - Supplier ID" reportAttributeId="Name_1_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Organization (wt.org.WTOrganization)" isExternal="false" type="java.lang.String" propertyName="name">name</column>
            </reportAttribute>
            <reportAttribute heading="BK - Plant" reportAttributeId="Plant_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Supplier (com.ptc.windchill.suma.supplier.Supplier)" isExternal="false" type="java.lang.String" propertyName="WCTYPE|com.ptc.windchill.suma.supplier.Supplier~MBA|typeInfoSupplier.ptc_str_4">WCTYPE|com.ptc.windchill.suma.supplier.Supplier~MBA|typeInfoSupplier.ptc_str_4</column>
            </reportAttribute>
            <reportAttribute heading="BK - Plant Key" reportAttributeId="Plant_Key" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Supplier (com.ptc.windchill.suma.supplier.Supplier)" isExternal="false" type="java.lang.String" propertyName="WCTYPE|com.ptc.windchill.suma.supplier.Supplier~MBA|typeInfoSupplier.ptc_str_5">WCTYPE|com.ptc.windchill.suma.supplier.Supplier~MBA|typeInfoSupplier.ptc_str_5</column>
            </reportAttribute>
            <reportAttribute heading="BK - Description" reportAttributeId="Description" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Organization (wt.org.WTOrganization)" isExternal="false" type="java.lang.String" propertyName="description">description</column>
            </reportAttribute>
            <reportAttribute heading="BK - Country Code" reportAttributeId="Country_Code_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Supplier (com.ptc.windchill.suma.supplier.Supplier)" isExternal="false" type="java.lang.String" propertyName="WCTYPE|com.ptc.windchill.suma.supplier.Supplier~IBA|BMW_COUNTRY_CODE">WCTYPE|com.ptc.windchill.suma.supplier.Supplier~IBA|BMW_COUNTRY_CODE</column>
            </reportAttribute>
        </selectOrConstrain>
        <from>
            <table alias="MMG Position Variant" isExternal="false" xposition="0px" yposition="40px">WCTYPE|wt.part.WTPart|com.bmw.psmg.sbb.PositionVariant|com.bmw.psmg.sbb.PositionVariantMMG</table>
            <table alias="View Specific Enterprise Data" isExternal="false" xposition="344px" yposition="0px">WCTYPE|com.ptc.windchill.enterprise.data.EnterpriseData|com.ptc.windchill.enterprise.data.enterpriseData.PlantSpecificEnterpriseData</table>
            <table alias="View" isExternal="false" xposition="50px" yposition="143px">wt.vc.views.View</table>
            <table alias="Plant Specific Functional Data Master" isExternal="false" xposition="799px" yposition="0px">com.ptc.windchill.enterprise.data.PlantFunctionalDataMaster</table>
            <table alias="Building Location Component" isExternal="false" xposition="823px" yposition="152px">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.BuildingLocationComponent</table>
            <table alias="Organization (wt.org.WTOrganization)" isExternal="false" xposition="466px" yposition="284px">wt.org.WTOrganization</table>
            <table alias="Supplier (com.ptc.windchill.suma.supplier.Supplier)" isExternal="false" xposition="786px" yposition="361px">com.ptc.windchill.suma.supplier.Supplier</table>
        </from>
        <where>
            <compositeCondition type="or">
                <condition>
                    <standardCondition>
                        <operand>
                            <column alias="Building Location Component" isExternal="false" type="long" propertyName="typeInfoPlantFunctionalData.ptc_ref_1.objectId.id">typeInfoPlantFunctionalData.ptc_ref_1.key.id</column>
                        </operand>
                        <operator type="equal"/>
                        <operand>
                            <column alias="Supplier (com.ptc.windchill.suma.supplier.Supplier)" isExternal="false" type="long" propertyName="persistInfo.objectIdentifier.id">thePersistInfo.theObjectIdentifier.id</column>
                        </operand>
                    </standardCondition>
                </condition>
                <condition>
                    <standardCondition>
                        <operand>
                            <column alias="Organization (wt.org.WTOrganization)" isExternal="false" type="long" propertyName="persistInfo.objectIdentifier.id">thePersistInfo.theObjectIdentifier.id</column>
                        </operand>
                        <operator type="like"/>
                        <operand>
                            <column alias="Supplier (com.ptc.windchill.suma.supplier.Supplier)" isExternal="false" type="long" propertyName="organizationReference.objectId.id">organizationReference.key.id</column>
                        </operand>
                    </standardCondition>
                </condition>
            </compositeCondition>
        </where>
        <linkJoin>
            <join name="com.ptc.windchill.enterprise.data.EnterpriseDataLink">
                <fromAliasTarget alias="MMG Position Variant"/>
                <toAliasTarget alias="View Specific Enterprise Data"/>
            </join>
            <join name="com.ptc.windchill.enterprise.data.PlantFunctionalDataLink">
                <fromAliasTarget alias="View Specific Enterprise Data"/>
                <toAliasTarget alias="Plant Specific Functional Data Master"/>
            </join>
            <join name="com.ptc.windchill.enterprise.data.PlantFunctionalDataMasterIteration">
                <fromAliasTarget alias="Plant Specific Functional Data Master"/>
                <toAliasTarget alias="Building Location Component"/>
            </join>
        </linkJoin>
        <referenceJoin>
            <join name="view">
                <fromAliasTarget alias="View Specific Enterprise Data"/>
                <toAliasTarget alias="View"/>
            </join>
            <join name="organizationReference">
                <fromAliasTarget alias="Supplier (com.ptc.windchill.suma.supplier.Supplier)"/>
                <toAliasTarget alias="Organization (wt.org.WTOrganization)"/>
            </join>
            <join name="typeInfoPlantFunctionalData.ptc_ref_1">
                <fromAliasTarget alias="Building Location Component"/>
                <toAliasTarget alias="Supplier (com.ptc.windchill.suma.supplier.Supplier)"/>
            </join>
        </referenceJoin>
        <descriptionForExport>This report is used for Related Building Location Component Search of Position Variants.</descriptionForExport>
    </query>
</qml>