<?xml version="1.0" encoding="UTF-8" standalone='yes'?>
<qml bypassAccessControl="false" caseInsensitive="true" addTimeToDateFields="false" mainType="Effectivity Context (com.bmw.psmg.sbb.EffectivityContext)" joinModel="false" xsi:noNamespaceSchemaLocation="qml.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <query>
    <selectOrConstrain distinct="false" group="false">
      <reportAttribute heading="SBB Effectivity Context (Type)" reportAttributeId="Effectivity_Context_com_bmw_psmg_sbb_EffectivityContext_" userCanSelect="true" userCanConstrain="false" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <object alias="Effectivity Context (com.bmw.psmg.sbb.EffectivityContext)" propertyName=""/>
      </reportAttribute>
      <reportAttribute heading="Type Name" reportAttributeId="Number" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <column alias="Effectivity Context (com.bmw.psmg.sbb.EffectivityContext)" isExternal="false" type="java.lang.String" propertyName="number">master&gt;number</column>
      </reportAttribute>
      <reportAttribute heading="End Date" reportAttributeId="End_Date" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <column alias="Date Effectivity (wt.effectivity.WTDatedEffectivity) 1" isExternal="false" type="java.sql.Timestamp" propertyName="endDate">range.end</column>
      </reportAttribute>
      <reportAttribute heading="Start Date" reportAttributeId="Start_Date" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <column alias="Date Effectivity (wt.effectivity.WTDatedEffectivity) 1" isExternal="false" type="java.sql.Timestamp" propertyName="startDate">range.start</column>
      </reportAttribute>
      <reportAttribute heading="Plant" reportAttributeId="Eff_Context" userCanSelect="true" userCanConstrain="false" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <object alias="Date Effectivity (wt.effectivity.WTDatedEffectivity) 1" propertyName="effContext"/>
      </reportAttribute>
    </selectOrConstrain>
    <from>
      <table alias="Effectivity Context (com.bmw.psmg.sbb.EffectivityContext)" isExternal="false" xposition="0px" yposition="40px">WCTYPE|wt.part.WTPart|com.bmw.psmg.sbb.EffectivityContext</table>
      <table alias="Date Effectivity (wt.effectivity.WTDatedEffectivity) 1" isExternal="false" xposition="0" yposition="120">wt.effectivity.WTDatedEffectivity</table>
    </from>
    <referenceJoin>
      <join name="versionedTarget">
        <fromAliasTarget alias="Date Effectivity (wt.effectivity.WTDatedEffectivity) 1"/>
        <toAliasTarget alias="Effectivity Context (com.bmw.psmg.sbb.EffectivityContext)"/>
      </join>
    </referenceJoin>
  </query>
</qml>
