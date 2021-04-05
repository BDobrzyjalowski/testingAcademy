<?xml version="1.0" encoding="UTF-8" standalone='yes'?>
<qml bypassAccessControl="false" caseInsensitive="false" addTimeToDateFields="true" mainType="Workflow Process" joinModel="false" xsi:noNamespaceSchemaLocation="qml.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <query>
    <selectOrConstrain distinct="false" group="false">
      <reportAttribute heading="Generic Part" reportAttributeId="Generic_Part" userCanSelect="true" userCanConstrain="false" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <object alias="Generic Part" propertyName=""/>
      </reportAttribute>
      <reportAttribute heading="Generic Part Number" reportAttributeId="Number_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <column alias="Generic Part" isExternal="false" type="java.lang.String" propertyName="number">master&gt;number</column>
      </reportAttribute>
      <reportAttribute heading="Generic Part Name" reportAttributeId="Name" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <column alias="Generic Part" isExternal="false" type="java.lang.String" propertyName="name">master&gt;name</column>
      </reportAttribute>
      <reportAttribute heading="Generic Part View" reportAttributeId="Name_2" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <column alias="Generic Part - View" isExternal="false" type="java.lang.String" propertyName="name">name</column>
      </reportAttribute>
      <reportAttribute heading="Generic Part Created" reportAttributeId="Created_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <column alias="Generic Part" isExternal="false" type="java.sql.Timestamp" propertyName="createTimestamp">thePersistInfo.createStamp</column>
      </reportAttribute>
      <reportAttribute heading="Generic Part Last Modified" reportAttributeId="Last_Modified" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <column alias="Generic Part" isExternal="false" type="java.sql.Timestamp" propertyName="modifyTimestamp">thePersistInfo.modifyStamp</column>
      </reportAttribute>
      <reportAttribute heading="Workflow Name" reportAttributeId="Name_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <column alias="Workflow Process" isExternal="false" type="java.lang.String" propertyName="name">name</column>
      </reportAttribute>
      <reportAttribute heading="Workflow State" reportAttributeId="State" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <column alias="Workflow Process" isExternal="false" type="wt.workflow.engine.WfState" propertyName="state">state</column>
      </reportAttribute>
      <reportAttribute heading="Actual Start" reportAttributeId="Actual_Start" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <column alias="Workflow Process" isExternal="false" type="java.sql.Timestamp" propertyName="startTime">startTime</column>
      </reportAttribute>
      <reportAttribute heading="Actual Finish" reportAttributeId="Actual_Finish" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <column alias="Workflow Process" isExternal="false" type="java.sql.Timestamp" propertyName="endTime">endTime</column>
      </reportAttribute>
    </selectOrConstrain>
    <from>
      <table alias="Workflow Process" isExternal="false" xposition="2px" yposition="25px">wt.workflow.engine.WfProcess</table>
      <table alias="Control Branch" isExternal="false" xposition="0px" yposition="120px">wt.vc.ControlBranch</table>
      <table alias="Generic Part" isExternal="false" xposition="411px" yposition="366px">WCTYPE|wt.part.WTPart|com.bmw.psmg.sbb.GenericPart</table>
      <table alias="Part Master" isExternal="false" xposition="425px" yposition="126px">wt.part.WTPartMaster</table>
      <table alias="Generic Part - View" isExternal="false" xposition="641px" yposition="371px">wt.vc.views.View</table>
    </from>
    <where>
      <compositeCondition type="and">
        <condition>
          <standardCondition>
            <operand>
              <column alias="Workflow Process" isExternal="false" type="java.lang.String" propertyName="businessObjReference">businessObjReference</column>
            </operand>
            <operator type="equal"/>
            <operand>
              <function name="CONCAT" type="java.lang.String">
                <constant type="java.lang.String" isMacro="false" xml:space="preserve">VR:wt.part.WTPart:</constant>
                <column alias="Control Branch" isExternal="false" type="long" propertyName="persistInfo.objectIdentifier.id">thePersistInfo.theObjectIdentifier.id</column>
              </function>
            </operand>
          </standardCondition>
        </condition>
        <condition>
          <standardCondition>
            <operand>
              <column alias="Generic Part" isExternal="false" type="boolean" propertyName="latestIteration">iterationInfo.latest</column>
            </operand>
            <operator type="equal"/>
            <operand>
              <constant type="boolean" isMacro="false" xml:space="preserve">1</constant>
            </operand>
          </standardCondition>
        </condition>
      </compositeCondition>
    </where>
    <referenceJoin>
      <join name="masterReference">
        <fromAliasTarget alias="Control Branch"/>
        <toAliasTarget alias="Part Master"/>
      </join>
      <join name="masterReference">
        <fromAliasTarget alias="Generic Part"/>
        <toAliasTarget alias="Part Master"/>
      </join>
      <join name="view">
        <fromAliasTarget alias="Generic Part"/>
        <toAliasTarget alias="Generic Part - View"/>
      </join>
    </referenceJoin>
  </query>
</qml>
