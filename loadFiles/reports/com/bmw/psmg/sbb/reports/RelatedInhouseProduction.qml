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
            <layoutSectionHeader layoutSectionId="Inhouse_Production" text="Inhouse Production"/>
            <reportAttribute heading="Description" reportAttributeId="Description" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Inhouse Production" isExternal="false" type="java.lang.String" propertyName="description">description</column>
            </reportAttribute>
            <reportAttribute heading="BOM Text" reportAttributeId="BOM_Text" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Inhouse Production" isExternal="false" type="java.lang.String" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_str_19">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_str_19</column>
            </reportAttribute>
            <reportAttribute heading="Different Component Quantity" reportAttributeId="Different_Component_Quantity" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Inhouse Production" isExternal="false" type="java.lang.String" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_str_2">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_str_2</column>
            </reportAttribute>
            <reportAttribute heading="Alternative Item" reportAttributeId="Alternative_Item_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Inhouse Production" isExternal="false" type="java.lang.String" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_str_21">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_str_21</column>
            </reportAttribute>
            <reportAttribute heading="Explosion Type" reportAttributeId="Explosion_Type" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Inhouse Production" isExternal="false" type="java.lang.String" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_str_10">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_str_10</column>
            </reportAttribute>
            <reportAttribute heading="Indicator Bulk Material" reportAttributeId="Indicator_Bulk_Material" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Inhouse Production" isExternal="false" type="java.lang.String" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_str_16">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_str_16</column>
            </reportAttribute>
            <reportAttribute heading="Indicator Co-Product" reportAttributeId="Indicator_Co_Product" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Inhouse Production" isExternal="false" type="java.lang.String" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_str_17">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_str_17</column>
            </reportAttribute>
            <reportAttribute heading="Issue Location For Production Order" reportAttributeId="Issue_Location_For_Production_Order" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Inhouse Production" isExternal="false" type="java.lang.String" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_str_12">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_str_12</column>
            </reportAttribute>
            <reportAttribute heading="Indicator Recursiveness Allowed" reportAttributeId="Indicator_Recursiveness_Allowed" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Inhouse Production" isExternal="false" type="java.lang.String" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_str_11">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_str_11</column>
            </reportAttribute>
            <reportAttribute heading="Special Procurement Type For BOM Item" reportAttributeId="Special_Procurement_Type_For_BOM_Item" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Inhouse Production" isExternal="false" type="java.lang.String" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_str_13">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_str_13</column>
            </reportAttribute>
            <reportAttribute heading="Sort String" reportAttributeId="Sort_String" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Inhouse Production" isExternal="false" type="java.lang.String" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_str_18">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_str_18</column>
            </reportAttribute>
            <reportAttribute heading="BOM Item Text 1" reportAttributeId="BOM_Item_Text_1_1_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Inhouse Production" isExternal="false" type="java.lang.String" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_str_15">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_str_15</column>
            </reportAttribute>
            <reportAttribute heading="BOM Item Text 2" reportAttributeId="BOM_Item_Text_2_1_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Inhouse Production" isExternal="false" type="java.lang.String" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_str_14">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_str_14</column>
            </reportAttribute>
            <reportAttribute heading="BOM Item Number" reportAttributeId="BOM_Item_Number" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Inhouse Production" isExternal="false" type="java.lang.String" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_dbl_1">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_dbl_1</column>
            </reportAttribute>
            <reportAttribute heading="Alternative BOM" reportAttributeId="Alternative_BOM" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Inhouse Production" isExternal="false" type="java.lang.Long" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_str_20">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_str_20</column>
            </reportAttribute>
            <reportAttribute heading="Production Relevant" reportAttributeId="Production_Relevant" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Inhouse Production" isExternal="false" type="java.lang.String" propertyName="WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_str_1">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction~MBA|typeInfoPlantFunctionalData.ptc_str_1</column>
            </reportAttribute>
        </selectOrConstrain>
        <from>
            <table alias="MMG Position Variant" isExternal="false" xposition="0px" yposition="40px">WCTYPE|wt.part.WTPart|com.bmw.psmg.sbb.PositionVariant|com.bmw.psmg.sbb.PositionVariantMMG</table>
            <table alias="View Specific Enterprise Data" isExternal="false" xposition="344px" yposition="0px">WCTYPE|com.ptc.windchill.enterprise.data.EnterpriseData|com.ptc.windchill.enterprise.data.enterpriseData.PlantSpecificEnterpriseData</table>
            <table alias="Inhouse Production" isExternal="false" xposition="606px" yposition="257px">WCTYPE|com.ptc.windchill.enterprise.data.PlantFunctionalData|com.bmw.psmg.sbb.InhouseProduction</table>
            <table alias="View" isExternal="false" xposition="50px" yposition="143px">wt.vc.views.View</table>
            <table alias="Plant Specific Functional Data Master" isExternal="false" xposition="582px" yposition="133px">com.ptc.windchill.enterprise.data.PlantFunctionalDataMaster</table>
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
                <toAliasTarget alias="Inhouse Production"/>
            </join>
        </linkJoin>
        <referenceJoin>
            <join name="view">
                <fromAliasTarget alias="View Specific Enterprise Data"/>
                <toAliasTarget alias="View"/>
            </join>
        </referenceJoin>
        <descriptionForExport>This report is used for Related Inhouse Production Search of Position Variants.</descriptionForExport>
    </query>
</qml>