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
            <layoutSectionHeader layoutSectionId="Assembly_Location" text="Packaging"/>
            <reportAttribute heading="Description" reportAttributeId="Description" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Packaging" isExternal="false" type="java.lang.String" propertyName="description">description</column>
            </reportAttribute>
            <reportAttribute heading="Container Owner" reportAttributeId="Container_Owner" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Packaging" isExternal="false" type="java.lang.String" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Packaging~MBA|typeInfoPlantFunctionalData.ptc_str_12">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Packaging~MBA|typeInfoPlantFunctionalData.ptc_str_12</column>
            </reportAttribute>
            <reportAttribute heading="Load Unit Nr." reportAttributeId="Load_Unit_Nr_" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Packaging" isExternal="false" type="java.lang.String" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Packaging~MBA|typeInfoPlantFunctionalData.ptc_str_13">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Packaging~MBA|typeInfoPlantFunctionalData.ptc_str_13</column>
            </reportAttribute>
            <reportAttribute heading="Type Load Unit" reportAttributeId="Type_Load_Unit" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Packaging" isExternal="false" type="java.lang.String" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Packaging~MBA|typeInfoPlantFunctionalData.ptc_str_10">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Packaging~MBA|typeInfoPlantFunctionalData.ptc_str_10</column>
            </reportAttribute>
            <reportAttribute heading="Classification Load Unit" reportAttributeId="Classification_Load_Unit" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Packaging" isExternal="false" type="java.lang.String" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Packaging~MBA|typeInfoPlantFunctionalData.ptc_str_1">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Packaging~MBA|typeInfoPlantFunctionalData.ptc_str_1</column>
            </reportAttribute>
            <reportAttribute heading="Filling Degree Load Unit" reportAttributeId="Filling_Degree_Load_Unit" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Packaging" isExternal="false" type="java.lang.Long" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Packaging~MBA|typeInfoPlantFunctionalData.ptc_lng_11">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Packaging~MBA|typeInfoPlantFunctionalData.ptc_lng_11</column>
            </reportAttribute>
            <reportAttribute heading="Stacking Factor Load Unit" reportAttributeId="Stacking_Factor_Load_Unit" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Packaging" isExternal="false" type="java.lang.Long" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Packaging~MBA|typeInfoPlantFunctionalData.ptc_lng_14">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Packaging~MBA|typeInfoPlantFunctionalData.ptc_lng_14</column>
            </reportAttribute>
            <reportAttribute heading="Bin Nr." reportAttributeId="Bin_Nr_" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Packaging" isExternal="false" type="java.lang.String" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Packaging~MBA|typeInfoPlantFunctionalData.ptc_str_11">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Packaging~MBA|typeInfoPlantFunctionalData.ptc_str_11</column>
            </reportAttribute>
            <reportAttribute heading="Type Bin" reportAttributeId="Type_Bin" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Packaging" isExternal="false" type="java.lang.String" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Packaging~MBA|typeInfoPlantFunctionalData.ptc_str_14">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Packaging~MBA|typeInfoPlantFunctionalData.ptc_str_14</column>
            </reportAttribute>
            <reportAttribute heading="Filling Degree Bin" reportAttributeId="Filling_Degree_Bin" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Packaging" isExternal="false" type="java.lang.Long" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Packaging~MBA|typeInfoPlantFunctionalData.ptc_lng_10">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Packaging~MBA|typeInfoPlantFunctionalData.ptc_lng_10</column>
            </reportAttribute>
            <reportAttribute heading="Load Unit Length" reportAttributeId="Load_Unit_Length" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Packaging" isExternal="false" type="wt.units.FloatingPointWithUnits" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Packaging~MBA|typeInfoPlantFunctionalData.ptc_dbl_1">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Packaging~MBA|typeInfoPlantFunctionalData.ptc_dbl_1</column>
            </reportAttribute>
            <reportAttribute heading="Load Unit Width" reportAttributeId="Load_Unit_Width" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Packaging" isExternal="false" type="wt.units.FloatingPointWithUnits" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Packaging~MBA|typeInfoPlantFunctionalData.ptc_dbl_2">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Packaging~MBA|typeInfoPlantFunctionalData.ptc_dbl_2</column>
            </reportAttribute>
            <reportAttribute heading="Load Unit Height" reportAttributeId="Load_Unit_Height" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Packaging" isExternal="false" type="wt.units.FloatingPointWithUnits" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Packaging~MBA|typeInfoPlantFunctionalData.ptc_dbl_3">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Packaging~MBA|typeInfoPlantFunctionalData.ptc_dbl_3</column>
            </reportAttribute>
        </selectOrConstrain>
        <from>
            <table alias="MMG Position Variant" isExternal="false" xposition="0px" yposition="40px">WCTYPE|wt.part.WTPart|com.bmw.psmg.sbb.PositionVariant|com.bmw.psmg.sbb.PositionVariantMMG</table>
            <table alias="View Specific Enterprise Data" isExternal="false" xposition="344px" yposition="0px">WCTYPE|com.ptc.windchill.enterprise.data.EnterpriseData|com.ptc.windchill.enterprise.data.enterpriseData.PlantSpecificEnterpriseData</table>
            <table alias="View" isExternal="false" xposition="50px" yposition="143px">wt.vc.views.View</table>
            <table alias="Plant Specific Functional Data Master" isExternal="false" xposition="799px" yposition="0px">com.ptc.windchill.enterprise.data.PlantFunctionalDataMaster</table>
            <table alias="Packaging" isExternal="false" xposition="829px" yposition="154px">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.Packaging</table>
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
                <toAliasTarget alias="Packaging"/>
            </join>
        </linkJoin>
        <referenceJoin>
            <join name="view">
                <fromAliasTarget alias="View Specific Enterprise Data"/>
                <toAliasTarget alias="View"/>
            </join>
        </referenceJoin>
        <descriptionForExport>This report is used for Related Packaging Search of Position Variants.</descriptionForExport>
    </query>
</qml>