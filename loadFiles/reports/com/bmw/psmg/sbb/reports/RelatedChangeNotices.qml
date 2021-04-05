<?xml version="1.0" encoding="UTF-8" standalone='yes'?>
<qml bypassAccessControl="false" caseInsensitive="true" addTimeToDateFields="true" mainType="Workflow Process" joinModel="false" xsi:noNamespaceSchemaLocation="qml.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <query>
    <selectOrConstrain distinct="false" group="false">
      <reportAttribute heading="BMW Change Notice" reportAttributeId="BMW_Change_Notice" userCanSelect="true" userCanConstrain="false" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <object alias="BMW Change Notice" propertyName=""/>
      </reportAttribute>
      <reportAttribute heading="BMW Change Notice.Name" reportAttributeId="Name_1_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <column alias="BMW Change Notice" isExternal="false" type="java.lang.String" propertyName="name">master&gt;name</column>
      </reportAttribute>
      <reportAttribute heading="BMW Change Notice.Number" reportAttributeId="Number" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <column alias="BMW Change Notice" isExternal="false" type="java.lang.String" propertyName="number">master&gt;number</column>
      </reportAttribute>
      <reportAttribute heading="BMW Change Notice.Life Cycle.State" reportAttributeId="Life_Cycle_State" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <column alias="BMW Change Notice" isExternal="false" type="wt.lifecycle.State" propertyName="state.state">state.state</column>
      </reportAttribute>
      <reportAttribute heading="Event ID" reportAttributeId="Event_ID" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <column alias="BMW Change Notice" isExternal="false" type="java.lang.String" propertyName="WCTYPE|wt.change2.WTChangeOrder2|com.bmw.psmg.sbb.BMWChangeNotice~IBA|BMW_EVENT_ID">WCTYPE|wt.change2.WTChangeOrder2|com.bmw.psmg.sbb.BMWChangeNotice~IBA|BMW_EVENT_ID</column>
      </reportAttribute>
      <reportAttribute heading="Total Number Messages" reportAttributeId="Total_Number_Messages" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <column alias="BMW Change Notice" isExternal="false" type="java.lang.Long" propertyName="WCTYPE|wt.change2.WTChangeOrder2|com.bmw.psmg.sbb.BMWChangeNotice~IBA|BMW_TOTAL_NUMBER_MESSAGES">WCTYPE|wt.change2.WTChangeOrder2|com.bmw.psmg.sbb.BMWChangeNotice~IBA|BMW_TOTAL_NUMBER_MESSAGES</column>
      </reportAttribute>
      <reportAttribute heading="Message Count" reportAttributeId="Message_Count" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <column alias="BMW Change Notice" isExternal="false" type="java.lang.Long" propertyName="WCTYPE|wt.change2.WTChangeOrder2|com.bmw.psmg.sbb.BMWChangeNotice~IBA|BMW_MESSAGE_COUNT">WCTYPE|wt.change2.WTChangeOrder2|com.bmw.psmg.sbb.BMWChangeNotice~IBA|BMW_MESSAGE_COUNT</column>
      </reportAttribute>
      <reportAttribute heading="BMW Change Notice.Created" reportAttributeId="Created" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <column alias="BMW Change Notice" isExternal="false" type="java.sql.Timestamp" propertyName="createTimestamp">thePersistInfo.createStamp</column>
      </reportAttribute>
      <reportAttribute heading="BMW Change Notice.Last Modified" reportAttributeId="Last_Modified_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <column alias="BMW Change Notice" isExternal="false" type="java.sql.Timestamp" propertyName="modifyTimestamp">thePersistInfo.modifyStamp</column>
      </reportAttribute>
      <reportAttribute heading="WF.Name" reportAttributeId="Name_1" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="ECN - Generate PSMG View" constantValue="" isMacro="false">
        <column alias="Workflow Process" isExternal="false" type="java.lang.String" propertyName="name">name</column>
      </reportAttribute>
      <reportAttribute heading="WF.Template.Name" reportAttributeId="Template_Name" userCanSelect="true" userCanConstrain="false" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <object alias="Workflow Process" propertyName="template.name"/>
      </reportAttribute>
      <reportAttribute heading="WF.State" reportAttributeId="State" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <column alias="Workflow Process" isExternal="false" type="wt.workflow.engine.WfState" propertyName="state">state</column>
      </reportAttribute>
      <reportAttribute heading="WF.Actual Start" reportAttributeId="Actual_Start" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <column alias="Workflow Process" isExternal="false" type="java.sql.Timestamp" propertyName="startTime">startTime</column>
      </reportAttribute>
      <reportAttribute heading="WF.Actual Finish" reportAttributeId="Actual_Finish" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <column alias="Workflow Process" isExternal="false" type="java.sql.Timestamp" propertyName="endTime">endTime</column>
      </reportAttribute>
      <reportAttribute heading="WF.Queue Name" reportAttributeId="Name" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <column alias="Wt Queue" isExternal="false" type="java.lang.String" propertyName="name">name</column>
      </reportAttribute>
    </selectOrConstrain>
    <from>
      <table alias="Workflow Process" isExternal="false" xposition="0px" yposition="40px">wt.workflow.engine.WfProcess</table>
      <table alias="Wt Queue" isExternal="false" xposition="0px" yposition="80px">wt.queue.WtQueue</table>
      <table alias="Control Branch" isExternal="false" xposition="0px" yposition="120px">wt.vc.ControlBranch</table>
      <table alias="WTChange Order2 Master" isExternal="false" xposition="229px" yposition="122px">wt.change2.WTChangeOrder2Master</table>
      <table alias="BMW Change Notice" isExternal="false" xposition="279px" yposition="266px">WCTYPE|wt.change2.WTChangeOrder2|com.bmw.psmg.sbb.BMWChangeNotice</table>
    </from>
    <where>
      <compositeCondition type="and">
        <condition>
          <standardCondition>
            <operand>
              <column alias="Workflow Process" isExternal="false" type="long" propertyName="userWorkQueueReference.key.id">userWorkQueueReference.key.id</column>
            </operand>
            <operator type="equal"/>
            <operand>
              <column alias="Wt Queue" isExternal="false" type="long" propertyName="persistInfo.objectIdentifier.id">thePersistInfo.theObjectIdentifier.id</column>
            </operand>
          </standardCondition>
        </condition>
        <condition>
          <standardCondition>
            <operand>
              <column alias="Workflow Process" isExternal="false" type="java.lang.String" propertyName="businessObjReference">businessObjReference</column>
            </operand>
            <operator type="equal"/>
            <operand>
              <function name="CONCAT" type="java.lang.String">
                <constant type="java.lang.String" isMacro="false" xml:space="preserve">VR:wt.change2.WTChangeOrder2:</constant>
                <column alias="Control Branch" isExternal="false" type="long" propertyName="persistInfo.objectIdentifier.id">thePersistInfo.theObjectIdentifier.id</column>
              </function>
            </operand>
          </standardCondition>
        </condition>
        <condition>
          <standardCondition>
            <operand>
              <column alias="BMW Change Notice" isExternal="false" type="boolean" propertyName="latestIteration">iterationInfo.latest</column>
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
        <toAliasTarget alias="WTChange Order2 Master"/>
      </join>
      <join name="masterReference">
        <fromAliasTarget alias="BMW Change Notice"/>
        <toAliasTarget alias="WTChange Order2 Master"/>
      </join>
    </referenceJoin>
  </query>
</qml>
