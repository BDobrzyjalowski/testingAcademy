<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<qml bypassAccessControl="false" caseInsensitive="true" addTimeToDateFields="false" forAdvancedSearch="true" mainType="MMG Position Variant" joinModel="false" xsi:noNamespaceSchemaLocation="qml.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <query>
        <selectOrConstrain distinct="false" group="false">
            <layoutSectionHeader layoutSectionId="View_Specific_Enterprise_Data" text="View Specific Enterprise Data"/>
            <reportAttribute heading="Plant" reportAttributeId="Name_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
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
            <layoutSectionHeader layoutSectionId="Assembly_Location" text="Takerate"/>
            <reportAttribute heading="Description" reportAttributeId="Description" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Takerate" isExternal="false" type="java.lang.String" propertyName="description">description</column>
            </reportAttribute>
            <reportAttribute heading="Units over Lifetime" reportAttributeId="Units_over_Lifetime" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Takerate" isExternal="false" type="java.lang.Long" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Takerate~MBA|typeInfoPlantFunctionalData.ptc_lng_1">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Takerate~MBA|typeInfoPlantFunctionalData.ptc_lng_1</column>
            </reportAttribute>
            <reportAttribute heading="Peak Production Volume" reportAttributeId="Peak_Production_Volume" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Takerate" isExternal="false" type="java.lang.Long" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Takerate~MBA|typeInfoPlantFunctionalData.ptc_lng_10">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Takerate~MBA|typeInfoPlantFunctionalData.ptc_lng_10</column>
            </reportAttribute>
            <reportAttribute heading="Peak Year" reportAttributeId="Peak_Year" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Takerate" isExternal="false" type="java.lang.Long" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Takerate~MBA|typeInfoPlantFunctionalData.ptc_lng_11">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Takerate~MBA|typeInfoPlantFunctionalData.ptc_lng_11</column>
            </reportAttribute>
        </selectOrConstrain>
        <from>
            <table alias="MMG Position Variant" isExternal="false" xposition="0px" yposition="40px">WCTYPE|wt.part.WTPart|com.bmw.psmg.sbb.PositionVariant|com.bmw.psmg.sbb.PositionVariantMMG</table>
            <table alias="View Specific Enterprise Data" isExternal="false" xposition="344px" yposition="0px">WCTYPE|com.ptc.windchill.enterprise.data.EnterpriseData|com.ptc.windchill.enterprise.data.enterpriseData.PlantSpecificEnterpriseData</table>
            <table alias="View" isExternal="false" xposition="50px" yposition="143px">wt.vc.views.View</table>
            <table alias="Plant Specific Functional Data Master" isExternal="false" xposition="799px" yposition="0px">com.ptc.windchill.enterprise.data.PlantFunctionalDataMaster</table>
            <table alias="Takerate" isExternal="false" xposition="841px" yposition="138px">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Takerate</table>
        </from>
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
                <toAliasTarget alias="Takerate"/>
            </join>
        </linkJoin>
        <referenceJoin>
            <join name="view">
                <fromAliasTarget alias="View Specific Enterprise Data"/>
                <toAliasTarget alias="View"/>
            </join>
        </referenceJoin>
        <descriptionForExport>This report is used for Related Takerate of Position Variants.</descriptionForExport>
    </query>
</qml>