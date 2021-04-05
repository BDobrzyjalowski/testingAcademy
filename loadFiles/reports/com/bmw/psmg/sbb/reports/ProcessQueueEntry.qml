<?xml version="1.0" encoding="UTF-8" standalone='yes'?>
<qml bypassAccessControl="false" caseInsensitive="true" addTimeToDateFields="true" mainType="Wt Queue Entry" joinModel="false" xsi:noNamespaceSchemaLocation="qml.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <query>
    <selectOrConstrain distinct="false" group="false">
      <reportAttribute heading="Queue Entry" reportAttributeId="Queue_Entry" userCanSelect="true" userCanConstrain="false" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <object alias="Wt Queue Entry" propertyName=""/>
      </reportAttribute>
      <reportAttribute heading="Args" reportAttributeId="Args" userCanSelect="true" userCanConstrain="false" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <object alias="Wt Queue Entry" propertyName="args"/>
      </reportAttribute>
      <reportAttribute heading="Last Modified" reportAttributeId="Last_Modified" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <column alias="Wt Queue Entry" isExternal="false" type="java.sql.Timestamp" propertyName="modifyTimestamp">thePersistInfo.modifyStamp</column>
      </reportAttribute>
      <reportAttribute heading="Status Info.Message" reportAttributeId="Status_Info_Message" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="" constantValue="" isMacro="false">
        <column alias="Wt Queue Entry" isExternal="false" type="java.lang.String" propertyName="statusInfo.message">statusInfo.message</column>
      </reportAttribute>
      <reportAttribute heading="Status Info.Code" reportAttributeId="Status_Info_Code" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="SEVERE" defaultOperandValue="equal" constantValue="" isMacro="false">
        <column alias="Wt Queue Entry" isExternal="false" type="java.lang.String" propertyName="statusInfo.code">statusInfo.code</column>
      </reportAttribute>
      <reportAttribute heading="Queue Name" reportAttributeId="Queue_Name" userCanSelect="true" userCanConstrain="true" alwaysSelect="false" defaultValue="BMW IF-MMG-SBB-10" constantValue="" isMacro="false">
        <column alias="Wt Queue" isExternal="false" type="java.lang.String" propertyName="name">name</column>
      </reportAttribute>
    </selectOrConstrain>
    <from>
      <table alias="Wt Queue Entry" isExternal="false" xposition="0px" yposition="40px">wt.queue.WtQueueEntry</table>
      <table alias="Wt Queue" isExternal="false" xposition="106px" yposition="190px">wt.queue.WtQueue</table>
    </from>
    <referenceJoin>
      <join name="queueRef">
        <fromAliasTarget alias="Wt Queue Entry"/>
        <toAliasTarget alias="Wt Queue"/>
      </join>
    </referenceJoin>
  </query>
</qml>
