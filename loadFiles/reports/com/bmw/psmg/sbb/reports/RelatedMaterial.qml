<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<qml bypassAccessControl="false" caseInsensitive="true" addTimeToDateFields="false" forAdvancedSearch="true" mainType="MMG Position Variant" joinModel="false" xsi:noNamespaceSchemaLocation="qml.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <query>
        <selectOrConstrain distinct="false" group="false">
            <layoutSectionHeader layoutSectionId="Material" text="Material"/>
            <reportAttribute heading="Part-no" reportAttributeId="Number" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Material" isExternal="false" type="java.lang.String" propertyName="number">master&gt;number</column>
            </reportAttribute>
			<reportAttribute heading="AI_ZI" reportAttributeId="AI_ZI" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Material" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPart|com.bmw.psmg.sbb.Material~MBA|typeInfoWTPart.ptc_str_13">WCTYPE|wt.part.WTPart|com.bmw.psmg.sbb.Material~MBA|typeInfoWTPart.ptc_str_13</column>
            </reportAttribute>
            <reportAttribute heading="Descript." reportAttributeId="Descript__1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
            				<column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="name">name</column>
            </reportAttribute>
			<reportAttribute heading="Ab. Desc." reportAttributeId="Ab_Desc_" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
				<column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_ABBREV_DESCRIPTION">WCTYPE|wt.part.WTPartMaster~IBA|BMW_ABBREV_DESCRIPTION</column>
			</reportAttribute> 
            <reportAttribute heading="DOG" reportAttributeId="DOG_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.sql.Timestamp" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_DATE_OF_CREATION">WCTYPE|wt.part.WTPartMaster~IBA|BMW_DATE_OF_CREATION</column>
            </reportAttribute>
            <reportAttribute heading="Engin." reportAttributeId="Engin__1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_DESIGNER">WCTYPE|wt.part.WTPartMaster~IBA|BMW_DESIGNER</column>
            </reportAttribute>
            <reportAttribute heading="Status" reportAttributeId="Status_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_PART_NUMBER_STATUS">WCTYPE|wt.part.WTPartMaster~IBA|BMW_PART_NUMBER_STATUS</column>
            </reportAttribute>
            <reportAttribute heading="1. Type" reportAttributeId="Z_Type_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_INIT_USE_MODEL">WCTYPE|wt.part.WTPartMaster~IBA|BMW_INIT_USE_MODEL</column>
            </reportAttribute>
			<reportAttribute heading="Subst. f." reportAttributeId="Subst_f__1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_PREDECESSOR_PART_NUMBER">WCTYPE|wt.part.WTPartMaster~IBA|BMW_PREDECESSOR_PART_NUMBER</column>
            </reportAttribute>
            <reportAttribute heading="Unit of measure" reportAttributeId="Unit_of_measure_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                            <column alias="Material" isExternal="false" type="wt.part.QuantityUnit" propertyName="defaultUnit">master&gt;defaultUnit</column>
            </reportAttribute>
            <reportAttribute heading="Source Part" reportAttributeId="Source_Part_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_ORIGINAL_PART_NUMBER">WCTYPE|wt.part.WTPartMaster~IBA|BMW_ORIGINAL_PART_NUMBER</column>
            </reportAttribute>
            <reportAttribute heading="Wgt-Typ" reportAttributeId="Wgt_Typ_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Material" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPart|com.bmw.psmg.sbb.Material~MBA|typeInfoWTPart.ptc_str_17">WCTYPE|wt.part.WTPart|com.bmw.psmg.sbb.Material~MBA|typeInfoWTPart.ptc_str_17</column>
            </reportAttribute> 
            <reportAttribute heading="Weight" reportAttributeId="Weight_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Material" isExternal="false" type="wt.units.FloatingPointWithUnits" propertyName="WCTYPE|wt.part.WTPart|com.bmw.psmg.sbb.Material~MBA|typeInfoWTPart.ptc_dbl_1">WCTYPE|wt.part.WTPart|com.bmw.psmg.sbb.Material~MBA|typeInfoWTPart.ptc_dbl_1</column>
            </reportAttribute>
            <reportAttribute heading="NRCL" reportAttributeId="NAEL_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Material" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPart|com.bmw.psmg.sbb.Material~MBA|typeInfoWTPart.ptc_str_11">WCTYPE|wt.part.WTPart|com.bmw.psmg.sbb.Material~MBA|typeInfoWTPart.ptc_str_11</column>
            </reportAttribute>			
            <reportAttribute heading="Issu." reportAttributeId="Issu__1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_EDITOR">WCTYPE|wt.part.WTPartMaster~IBA|BMW_EDITOR</column>
            </reportAttribute>			
            <reportAttribute heading="DLC" reportAttributeId="DLC_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.sql.Timestamp" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_LAST_UPDATED">WCTYPE|wt.part.WTPartMaster~IBA|BMW_LAST_UPDATED</column>
            </reportAttribute>
            <reportAttribute heading="PNo-Dpt" reportAttributeId="PNo_Dpt_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_PART_NUMBER_DEPARTMENT">WCTYPE|wt.part.WTPartMaster~IBA|BMW_PART_NUMBER_DEPARTMENT</column>
            </reportAttribute>
            <reportAttribute heading="PNo-Cla" reportAttributeId="PNo_Cla_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_PART_NUMBER_TYPE">WCTYPE|wt.part.WTPartMaster~IBA|BMW_PART_NUMBER_TYPE</column>
            </reportAttribute>
            <reportAttribute heading="PNo-Gr" reportAttributeId="PNo_Gr_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_PART_NUMBER_GROUP">WCTYPE|wt.part.WTPartMaster~IBA|BMW_PART_NUMBER_GROUP</column>
            </reportAttribute>
            <reportAttribute heading="Standard" reportAttributeId="Standard_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_STD_DESC">WCTYPE|wt.part.WTPartMaster~IBA|BMW_STD_DESC</column>
            </reportAttribute>
            <reportAttribute heading="L-Flag" reportAttributeId="L_Flag_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_L_CODE">WCTYPE|wt.part.WTPartMaster~IBA|BMW_L_CODE</column>
            </reportAttribute>
           <reportAttribute heading="Spare Pt." reportAttributeId="Spare_Pt__1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_SPARE_PART_CODE">WCTYPE|wt.part.WTPartMaster~IBA|BMW_SPARE_PART_CODE</column>
            </reportAttribute>
            <reportAttribute heading="Supply type" reportAttributeId="Supply_type_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_SOURCING_TYPE">WCTYPE|wt.part.WTPartMaster~IBA|BMW_SOURCING_TYPE</column>
            </reportAttribute>
           <reportAttribute heading="Part Type" reportAttributeId="Part_Type_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_PART_TYPE">WCTYPE|wt.part.WTPartMaster~IBA|BMW_PART_TYPE</column>
            </reportAttribute>
            <reportAttribute heading="Character key" reportAttributeId="Character_key_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_CHARACTER_CODE">WCTYPE|wt.part.WTPartMaster~IBA|BMW_CHARACTER_CODE</column>
            </reportAttribute>
            <reportAttribute heading="Material" reportAttributeId="Material_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Material" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPart|com.bmw.psmg.sbb.Material~MBA|typeInfoWTPart.ptc_str_12">WCTYPE|wt.part.WTPart|com.bmw.psmg.sbb.Material~MBA|typeInfoWTPart.ptc_str_12</column>
            </reportAttribute>
			<reportAttribute heading="Surface" reportAttributeId="Surface_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Material" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPart|com.bmw.psmg.sbb.Material~MBA|typeInfoWTPart.ptc_str_14">WCTYPE|wt.part.WTPart|com.bmw.psmg.sbb.Material~MBA|typeInfoWTPart.ptc_str_14</column>
            </reportAttribute>
            <reportAttribute heading="PNo. All Colors" reportAttributeId="PNo_All_Colors_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_ALL_COLOR_NO">WCTYPE|wt.part.WTPartMaster~IBA|BMW_ALL_COLOR_NO</column>
            </reportAttribute>
            <reportAttribute heading="Color/Paint" reportAttributeId="Color_Paint_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_MATERIAL_COLOR_CODE">WCTYPE|wt.part.WTPartMaster~IBA|BMW_MATERIAL_COLOR_CODE</column>
            </reportAttribute>
            <reportAttribute heading="DF" reportAttributeId="DF_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_DRAWING_FORMAT">WCTYPE|wt.part.WTPartMaster~IBA|BMW_DRAWING_FORMAT</column>
            </reportAttribute>
            <reportAttribute heading="Draw. Ref." reportAttributeId="Draw_Ref__1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.Long" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_DRAWING_NOTE">WCTYPE|wt.part.WTPartMaster~IBA|BMW_DRAWING_NOTE</column>
            </reportAttribute>
            <reportAttribute heading="L/R-Kz." reportAttributeId="L_R_Kz__1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_LEFT_RIGHT_CODE">WCTYPE|wt.part.WTPartMaster~IBA|BMW_LEFT_RIGHT_CODE</column>
            </reportAttribute>
            <reportAttribute heading="Mir.-Co." reportAttributeId="Mir_Co__1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_MIRROR_INVERTED_ID">WCTYPE|wt.part.WTPartMaster~IBA|BMW_MIRROR_INVERTED_ID</column>
            </reportAttribute>
            <reportAttribute heading="VCR" reportAttributeId="VCR_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_VCR">WCTYPE|wt.part.WTPartMaster~IBA|BMW_VCR</column>
            </reportAttribute>
            <reportAttribute heading="DMU-Modell" reportAttributeId="DMU_Modell_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_DMU_MODELL">WCTYPE|wt.part.WTPartMaster~IBA|BMW_DMU_MODELL</column>
            </reportAttribute>
            <reportAttribute heading="JIT-Var." reportAttributeId="JIT_Var__1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_JIT_VARIANT">WCTYPE|wt.part.WTPartMaster~IBA|BMW_JIT_VARIANT</column>
            </reportAttribute>
            <reportAttribute heading="JIT-Co." reportAttributeId="JIT_Co__1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_JIT_CODE">WCTYPE|wt.part.WTPartMaster~IBA|BMW_JIT_CODE</column>
            </reportAttribute>
            <reportAttribute heading="HW/SW-Kz." reportAttributeId="HW_SW_Kz__1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_HW_SW_CODE">WCTYPE|wt.part.WTPartMaster~IBA|BMW_HW_SW_CODE</column>
            </reportAttribute>
            <reportAttribute heading="ESD-Co." reportAttributeId="ESD_Co__1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_ESD_MARKING">WCTYPE|wt.part.WTPartMaster~IBA|BMW_ESD_MARKING</column>
            </reportAttribute>
            <reportAttribute heading="Hazard" reportAttributeId="Hazard_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.Boolean" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_HAZARDOUS_GOODS_MARKING">WCTYPE|wt.part.WTPartMaster~IBA|BMW_HAZARDOUS_GOODS_MARKING</column>
            </reportAttribute>
            <reportAttribute heading="TecSa" reportAttributeId="TecSa_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_TEC_CLEANLINESS">WCTYPE|wt.part.WTPartMaster~IBA|BMW_TEC_CLEANLINESS</column>
            </reportAttribute>
            <reportAttribute heading="Recyclingkl." reportAttributeId="Recyclingkl__1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                <column alias="Part Master" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPartMaster~IBA|BMW_RECYCLING_CLASS">WCTYPE|wt.part.WTPartMaster~IBA|BMW_RECYCLING_CLASS</column>
            </reportAttribute>
            <reportAttribute heading="Getriebecode" reportAttributeId="Getriebecode_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
                            <column alias="Material" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.part.WTPart|com.bmw.psmg.sbb.Material~IBA|BMW_TRANSMISSION_CODE">WCTYPE|wt.part.WTPart|com.bmw.psmg.sbb.Material~IBA|BMW_TRANSMISSION_CODE</column>
            </reportAttribute>
        </selectOrConstrain>
        <from>
            <table alias="MMG Position Variant" isExternal="false" xposition="0px" yposition="40px">WCTYPE|wt.part.WTPart|com.bmw.psmg.sbb.PositionVariant|com.bmw.psmg.sbb.PositionVariantMMG</table>
            <table alias="Part Master" isExternal="false" xposition="254px" yposition="136px">wt.part.WTPartMaster</table>
            <table alias="Material" isExternal="false" xposition="514px" yposition="216px">WCTYPE|wt.part.WTPart|com.bmw.psmg.sbb.Material</table>
        </from>
        <linkJoin>
            <join name="wt.part.WTPartUsageLink">
                <fromAliasTarget alias="MMG Position Variant"/>
                <toAliasTarget alias="Part Master"/>
            </join>
            <join name="wt.part.MasterIteration">
                <fromAliasTarget alias="Part Master"/>
                <toAliasTarget alias="Material"/>
            </join>
        </linkJoin>
        <descriptionForExport>This report is used for Related Material Object Search of Position Variants.</descriptionForExport>
    </query>
</qml>