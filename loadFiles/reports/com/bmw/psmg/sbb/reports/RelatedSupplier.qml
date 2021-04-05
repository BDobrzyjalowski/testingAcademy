<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<qml bypassAccessControl="false" caseInsensitive="true" addTimeToDateFields="false" forAdvancedSearch="true" mainType="MMG Position Variant" joinModel="false" xsi:noNamespaceSchemaLocation="qml.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <query>
        <selectOrConstrain distinct="false" group="false">
            <layoutSectionHeader layoutSectionId="View_Specific_Enterprise_Data" text="View Specific Enterprise Data"/>
            <reportAttribute heading="Plant" reportAttributeId="Name_2" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
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
            <layoutSectionHeader layoutSectionId="Supplier" text="Supplier"/>
            <reportAttribute heading="Description" reportAttributeId="Description" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Supplier (com.bmw.psmg.sbb.Supplier)" isExternal="false" type="java.lang.String" propertyName="description">description</column>
            </reportAttribute>
            <reportAttribute heading="Portal M ID" reportAttributeId="Portal_M_ID" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Supplier (com.bmw.psmg.sbb.Supplier)" isExternal="false" type="java.lang.String" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Supplier~MBA|typeInfoPlantFunctionalData.ptc_str_1">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Supplier~MBA|typeInfoPlantFunctionalData.ptc_str_1</column>
            </reportAttribute>
            <reportAttribute heading="Supplier Prepackaging" reportAttributeId="Supplier_Prepackaging" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Supplier (com.bmw.psmg.sbb.Supplier)" isExternal="false" type="java.lang.Boolean" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Supplier~MBA|typeInfoPlantFunctionalData.ptc_bln_1">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Supplier~MBA|typeInfoPlantFunctionalData.ptc_bln_1</column>
            </reportAttribute>
            <reportAttribute heading="Supplier Delivery Location" reportAttributeId="Supplier_Delivery_Location" userCanSelect="false" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <parameterValue type="java.lang.String" enumIdentifier="WCTYPE|undefined~MBA|undefined"/>
            </reportAttribute>
            <reportAttribute heading="Supplier Production Location" reportAttributeId="Supplier_Production_Location" userCanSelect="false" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <parameterValue type="java.lang.String" enumIdentifier="WCTYPE|undefined~MBA|undefined"/>
            </reportAttribute>
        </selectOrConstrain>
        <from>
            <table alias="MMG Position Variant" isExternal="false" xposition="0px" yposition="40px">WCTYPE|wt.part.WTPart|com.bmw.psmg.sbb.PositionVariant|com.bmw.psmg.sbb.PositionVariantMMG</table>
            <table alias="View Specific Enterprise Data" isExternal="false" xposition="344px" yposition="0px">WCTYPE|com.ptc.windchill.enterprise.data.EnterpriseData|com.ptc.windchill.enterprise.data.enterpriseData.PlantSpecificEnterpriseData</table>
            <table alias="Supplier (com.bmw.psmg.sbb.Supplier)" isExternal="false" xposition="651px" yposition="254px">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Supplier</table>
            <table alias="View" isExternal="false" xposition="76px" yposition="161px">wt.vc.views.View</table>
            <table alias="Plant Specific Functional Data Master" isExternal="false" xposition="294px" yposition="215px">com.ptc.windchill.enterprise.data.PlantFunctionalDataMaster</table>
            <table alias="Organization (wt.org.WTOrganization) 1" isExternal="false" xposition="1371px" yposition="371px">wt.org.WTOrganization</table>
            <table alias="Supplier (com.ptc.windchill.suma.supplier.Supplier)" isExternal="false" xposition="1018px" yposition="353px">com.ptc.windchill.suma.supplier.Supplier</table>
            <table alias="Supplier (com.ptc.windchill.suma.supplier.Supplier) 1" isExternal="false" xposition="1049px" yposition="183px">com.ptc.windchill.suma.supplier.Supplier</table>
            <table alias="Organization (wt.org.WTOrganization)" isExternal="false" xposition="1303px" yposition="201px">wt.org.WTOrganization</table>
        </from>
        <where>
            <compositeCondition type="and">
                <compositeCondition type="or">
                    <condition>
                        <standardCondition>
                            <operand>
                                <column alias="Organization (wt.org.WTOrganization)" isExternal="false" type="java.lang.String" propertyName="description">description</column>
                            </operand>
                            <operator type="like"/>
                            <operand>
                                <reportAttributeReference id="Supplier_Delivery_Location"/>
                            </operand>
                        </standardCondition>
                    </condition>
                    <condition>
                        <standardCondition>
                            <operand>
                                <column alias="Organization (wt.org.WTOrganization)" isExternal="false" type="java.lang.String" propertyName="name">name</column>
                            </operand>
                            <operator type="like"/>
                            <operand>
                                <reportAttributeReference id="Supplier_Delivery_Location"/>
                            </operand>
                        </standardCondition>
                    </condition>
                </compositeCondition>
                <compositeCondition type="or">
                    <condition>
                        <standardCondition>
                            <operand>
                                <column alias="Organization (wt.org.WTOrganization) 1" isExternal="false" type="java.lang.String" propertyName="description">description</column>
                            </operand>
                            <operator type="like"/>
                            <operand>
                                <reportAttributeReference id="Supplier_Production_Location"/>
                            </operand>
                        </standardCondition>
                    </condition>
                    <condition>
                        <standardCondition>
                            <operand>
                                <column alias="Organization (wt.org.WTOrganization) 1" isExternal="false" type="java.lang.String" propertyName="name">name</column>
                            </operand>
                            <operator type="like"/>
                            <operand>
                                <reportAttributeReference id="Supplier_Production_Location"/>
                            </operand>
                        </standardCondition>
                    </condition>
                </compositeCondition>
                <condition>
                    <standardCondition>
                        <operand>
                            <column alias="Supplier (com.bmw.psmg.sbb.Supplier)" isExternal="false" type="boolean" propertyName="latestIteration">iterationInfo.latest</column>
                        </operand>
                        <operator type="equal"/>
                        <operand>
                            <constant type="boolean" isMacro="false" xml:space="preserve">1</constant>
                        </operand>
                    </standardCondition>
                </condition>
                <condition>
                    <standardCondition>
                        <operand>
                            <column alias="MMG Position Variant" isExternal="false" type="boolean" propertyName="latestIteration">iterationInfo.latest</column>
                        </operand>
                        <operator type="equal"/>
                        <operand>
                            <constant type="boolean" isMacro="false" xml:space="preserve">1</constant>
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
                <toAliasTarget alias="Supplier (com.bmw.psmg.sbb.Supplier)"/>
            </join>
        </linkJoin>
        <referenceJoin>
            <join name="view">
                <fromAliasTarget alias="View Specific Enterprise Data"/>
                <toAliasTarget alias="View"/>
            </join>
            <join name="typeInfoPlantFunctionalData.ptc_ref_2" outerJoinAlias="Supplier (com.ptc.windchill.suma.supplier.Supplier) 1">
                <fromAliasTarget alias="Supplier (com.bmw.psmg.sbb.Supplier)"/>
                <toAliasTarget alias="Supplier (com.ptc.windchill.suma.supplier.Supplier) 1"/>
            </join>
            <join name="typeInfoPlantFunctionalData.ptc_ref_1" outerJoinAlias="Supplier (com.ptc.windchill.suma.supplier.Supplier)">
                <fromAliasTarget alias="Supplier (com.bmw.psmg.sbb.Supplier)"/>
                <toAliasTarget alias="Supplier (com.ptc.windchill.suma.supplier.Supplier)"/>
            </join>
            <join name="organizationReference" outerJoinAlias="Organization (wt.org.WTOrganization) 1">
                <fromAliasTarget alias="Supplier (com.ptc.windchill.suma.supplier.Supplier)"/>
                <toAliasTarget alias="Organization (wt.org.WTOrganization) 1"/>
            </join>
            <join name="organizationReference" outerJoinAlias="Organization (wt.org.WTOrganization)">
                <fromAliasTarget alias="Supplier (com.ptc.windchill.suma.supplier.Supplier) 1"/>
                <toAliasTarget alias="Organization (wt.org.WTOrganization)"/>
            </join>
        </referenceJoin>
        <descriptionForExport>This report is used for Related Supplier of Position Variants.</descriptionForExport>
    </query>
</qml>