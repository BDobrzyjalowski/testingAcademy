<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<qml bypassAccessControl="false" caseInsensitive="true" addTimeToDateFields="false" forAdvancedSearch="true" mainType="MMG Position Variant" joinModel="false" xsi:noNamespaceSchemaLocation="qml.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <query>
        <selectOrConstrain distinct="false" group="false">
            <layoutSectionHeader layoutSectionId="View_Specific_Enterprise_Data" text="View Specific Enterprise Data"/>
            <reportAttribute heading="Plant" reportAttributeId="Name_4" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
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
            <layoutSectionHeader layoutSectionId="Assembly_Location" text="Assembly Location"/>
            <reportAttribute heading="Description" reportAttributeId="Description" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Assembly Location" isExternal="false" type="java.lang.String" propertyName="description">description</column>
            </reportAttribute>
            <reportAttribute heading="EBO" reportAttributeId="EBO" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Assembly Location" isExternal="false" type="java.lang.String" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.AssemblyLocation~MBA|typeInfoPlantFunctionalData.ptc_str_1">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.AssemblyLocation~MBA|typeInfoPlantFunctionalData.ptc_str_1</column>
            </reportAttribute>
            <reportAttribute heading="Quantity" reportAttributeId="Quantity" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Assembly Location" isExternal="false" type="com.ptc.core.meta.common.FloatingPoint" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.AssemblyLocation~MBA|typeInfoPlantFunctionalData.ptc_dbl_1">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.AssemblyLocation~MBA|typeInfoPlantFunctionalData.ptc_dbl_1</column>
            </reportAttribute>
            <reportAttribute heading="MAPP ID" reportAttributeId="MAPP_ID" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Assembly Location" isExternal="false" type="java.lang.String" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.AssemblyLocation~MBA|typeInfoPlantFunctionalData.ptc_str_11">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.AssemblyLocation~MBA|typeInfoPlantFunctionalData.ptc_str_11</column>
            </reportAttribute>
            <reportAttribute heading="Boolean" reportAttributeId="Boolean" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Assembly Location" isExternal="false" type="java.lang.String" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.AssemblyLocation~MBA|typeInfoPlantFunctionalData.ptc_str_10">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.AssemblyLocation~MBA|typeInfoPlantFunctionalData.ptc_str_10</column>
            </reportAttribute>
            <reportAttribute heading="Band" reportAttributeId="Band" userCanSelect="false" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <parameterValue type="java.lang.String" enumIdentifier="WCTYPE|undefined~MBA|undefined"/>
            </reportAttribute>
            <reportAttribute heading="Assembly Station" reportAttributeId="Assembly_Station" userCanSelect="false" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <parameterValue type="java.lang.String" enumIdentifier="WCTYPE|undefined~MBA|undefined"/>
            </reportAttribute>
        </selectOrConstrain>
        <from>
            <table alias="MMG Position Variant" isExternal="false" xposition="0px" yposition="40px">WCTYPE|wt.part.WTPart|com.bmw.psmg.sbb.PositionVariant|com.bmw.psmg.sbb.PositionVariantMMG</table>
            <table alias="View Specific Enterprise Data" isExternal="false" xposition="344px" yposition="0px">WCTYPE|com.ptc.windchill.enterprise.data.EnterpriseData|com.ptc.windchill.enterprise.data.enterpriseData.PlantSpecificEnterpriseData</table>
            <table alias="Assembly Location" isExternal="false" xposition="854px" yposition="133px">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.AssemblyLocation</table>
            <table alias="View" isExternal="false" xposition="50px" yposition="143px">wt.vc.views.View</table>
            <table alias="Plant Specific Functional Data Master" isExternal="false" xposition="799px" yposition="0px">com.ptc.windchill.enterprise.data.PlantFunctionalDataMaster</table>
            <table alias="AssemblyStation_Workcenter" isExternal="false" xposition="301px" yposition="256px">com.ptc.windchill.mpml.resource.MPMWorkCenter</table>
            <table alias="Band_Workcenter" isExternal="false" xposition="683px" yposition="359px">com.ptc.windchill.mpml.resource.MPMWorkCenter</table>
        </from>
        <where>
            <compositeCondition type="or">
                <condition>
                    <standardCondition>
                        <operand>
                            <column alias="Band_Workcenter" isExternal="false" type="java.lang.String" propertyName="name">master&gt;name</column>
                        </operand>
                        <operator type="like"/>
                        <operand>
                            <reportAttributeReference id="Band"/>
                        </operand>
                    </standardCondition>
                </condition>
                <condition>
                    <standardCondition>
                        <operand>
                            <column alias="Band_Workcenter" isExternal="false" type="java.lang.String" propertyName="number">master&gt;number</column>
                        </operand>
                        <operator type="like"/>
                        <operand>
                            <reportAttributeReference id="Band"/>
                        </operand>
                    </standardCondition>
                </condition>
                <condition>
                    <standardCondition>
                        <operand>
                            <column alias="AssemblyStation_Workcenter" isExternal="false" type="java.lang.String" propertyName="name">master&gt;name</column>
                        </operand>
                        <operator type="like"/>
                        <operand>
                            <reportAttributeReference id="Assembly_Station"/>
                        </operand>
                    </standardCondition>
                </condition>
                <condition>
                    <standardCondition>
                        <operand>
                            <column alias="AssemblyStation_Workcenter" isExternal="false" type="java.lang.String" propertyName="number">master&gt;number</column>
                        </operand>
                        <operator type="like"/>
                        <operand>
                            <reportAttributeReference id="Assembly_Station"/>
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
                <toAliasTarget alias="Assembly Location"/>
            </join>
        </linkJoin>
        <referenceJoin>
            <join name="view">
                <fromAliasTarget alias="View Specific Enterprise Data"/>
                <toAliasTarget alias="View"/>
            </join>
            <join name="typeInfoPlantFunctionalData.ptc_ref_1">
                <fromAliasTarget alias="Assembly Location"/>
                <toAliasTarget alias="Band_Workcenter"/>
            </join>
            <join name="typeInfoPlantFunctionalData.ptc_ref_2">
                <fromAliasTarget alias="Assembly Location"/>
                <toAliasTarget alias="AssemblyStation_Workcenter"/>
            </join>
        </referenceJoin>
        <descriptionForExport>This report is used for Related Assembly Location Search of Position Variants.</descriptionForExport>
    </query>
</qml>