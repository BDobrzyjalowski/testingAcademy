package com.bmw.psmg.sbb.utilities;

import static java.util.stream.Collectors.toSet;

import com.ptc.windchill.option.expression.ExpressionHelper;
import com.ptc.windchill.option.model.ChoiceMaster;
import com.ptc.windchill.option.model.ExpressionAlias;
import com.ptc.windchill.option.model.ExpressionAliasMaster;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import wt.fc.Persistable;
import wt.fc.PersistenceHelper;
import wt.fc.QueryResult;
import wt.fc.WTReference;
import wt.inf.container.WTContainerRef;
import wt.option.ComplexExpressionData;
import wt.option.ExpressionMemberLink;
import wt.part.WTPart;
import wt.query.QuerySpec;
import wt.query.SearchCondition;
import wt.util.WTException;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Expression Utils Class
 *
 * @author Karol Poliszkiewicz
 */
public class ExpressionUtils {

    private static final Logger LOGGER = Logger.getLogger(ExpressionUtils.class.getName());
    private static final JSONParser JSON_PARSER = new JSONParser();
    private static final String EXPRESSION_DELIMITER = " || ";
    private static final String EXPRESSION_KEY = "expression";
    private static final String AND_OPERATOR = " AND ";
    private static final String OR_OPERATOR = " OR ";

    private ExpressionUtils() {
    }

    /**
     * Get expression member map (member id, name) from WTPart
     *
     * @param part WTPart with Expression Data
     * @return map of expression member id and member name
     */
    public static Map<String, String> getExpressionMembers(WTPart part) {
        Set<ExpressionMemberLink> links = getExpressionMemberLinks(part);

        Map<String, String> map = new HashMap<>();
        for (ExpressionMemberLink link : links) {
            WTReference reference = link.getRoleBObjectRef();
            Persistable persistable = reference.getObject();
            if (persistable instanceof ExpressionAliasMaster) {
                ExpressionAliasMaster alias = (ExpressionAliasMaster) persistable;
                LOGGER.debug(String.format("Put alias with ID = %s and NAME = %s", link.getMemberId(), alias.getName()));
                map.put(link.getMemberId(), alias.getName());
            } else if (persistable instanceof ChoiceMaster) {
                ChoiceMaster choice = (ChoiceMaster) persistable;
                LOGGER.debug(String.format("Put choice with ID = %s and NAME = %s", link.getMemberId(), choice.getName()));
                map.put(link.getMemberId(), choice.getName());
            }
        }
        return map;
    }

    /**
     * Get all expression choices names from WTPart
     *
     * @param part WTPart with Expression Data
     * @return map of expression member id and member name
     */
    public static Set<String> getChoices(WTPart part) {
        Set<ExpressionMemberLink> links = getExpressionMemberLinks(part);

        Set<String> choices = new HashSet<>();
        for (ExpressionMemberLink link : links) {
            WTReference reference = link.getRoleBObjectRef();
            Persistable persistable = reference.getObject();
            if (persistable instanceof ExpressionAliasMaster) {
                ExpressionAliasMaster aliasMaster = (ExpressionAliasMaster) persistable;
                Optional<ExpressionAlias> alias = findExpressionAlias(aliasMaster);
                alias.ifPresent(a -> choices.addAll(getChoicesFromAlias(a)));
            } else if (persistable instanceof ChoiceMaster) {
                ChoiceMaster choice = (ChoiceMaster) persistable;
                LOGGER.debug(String.format("Add choice with ID = %s and NAME = %s", link.getMemberId(), choice.getName()));
                choices.add(choice.getName());
            }
        }
        return choices;
    }

    @SuppressWarnings("unchecked")
    private static Optional<ExpressionAlias> findExpressionAlias(ExpressionAliasMaster aliasMaster) {
        Optional<ExpressionAlias> alias = Optional.empty();

        try {
            QuerySpec querySpec = new QuerySpec(ExpressionAlias.class);
            querySpec.appendWhere(new SearchCondition(ExpressionAlias.class, ExpressionAlias.NUMBER, SearchCondition.EQUAL, aliasMaster.getNumber()), new int[]{0});
            QueryResult queryResult = PersistenceHelper.manager.find(querySpec);
            alias = queryResult.getObjectVectorIfc().getVector().stream().filter(ExpressionAlias.class::isInstance).map(ExpressionAlias.class::cast).findFirst();
        } catch (WTException e) {
            LOGGER.error("Cannot execute query spec", e);
        }

        return alias;
    }

    @SuppressWarnings("unchecked")
    private static Set<String> getChoicesFromAlias(ExpressionAlias alias) {
        Set<String> choices = new HashSet<>();

        try {
            QuerySpec querySpec = new QuerySpec(ExpressionMemberLink.class);
            querySpec.appendWhere(new SearchCondition(ExpressionMemberLink.class, "roleAObjectRef.key", SearchCondition.EQUAL, alias.getPersistInfo().getObjectIdentifier()), new int[]{0});
            QueryResult queryResult = PersistenceHelper.manager.find(querySpec);
            Stream<ExpressionMemberLink> stream = queryResult.getObjectVectorIfc().getVector().stream().filter(ExpressionMemberLink.class::isInstance).map(ExpressionMemberLink.class::cast);
            choices = stream.map(ExpressionMemberLink::getRoleBObject).filter(ChoiceMaster.class::isInstance).map(ChoiceMaster.class::cast).map(ChoiceMaster::getName).collect(toSet());
        } catch (WTException e) {
            LOGGER.error("Cannot execute query spec", e);
        }

        return choices;
    }

    private static Set<ExpressionMemberLink> getExpressionMemberLinks(WTPart part) {
        Set<ExpressionMemberLink> links = new HashSet<>();
        try {
            links.addAll(ExpressionHelper.service.getExpressionMemberLinks(part));
        } catch (WTException e) {
            LOGGER.error(String.format("Cannot ger Expression Member Links from %s", part), e);
        }
        return links;
    }

    /**
     * Join all expressions to one Complex Expression Data.
     *
     * @param expressions list of Expression Data
     * @return Complex Expression Data
     */
    public static ComplexExpressionData join(Set<String> expressions) {
        Set<String> ids = new HashSet<>();
        JSONArray members = new JSONArray();

        int index = 1;
        try {
            for (JSONObject json : convertToJson(expressions)) {
                JSONArray array = json.getJSONArray(ComplexExpressionData.MEMBERSKEY);
                for (int i = 0; i < array.length(); i++) {
                    String id = ComplexExpressionData.PREFIX + index;
                    ids.add(id);

                    JSONObject member = (JSONObject) array.get(i);
                    member.put(ComplexExpressionData.IDKEY, id);

                    members.put(member);
                    index++;
                }
            }
        } catch (JSONException e) {
            LOGGER.error("Cannot do operate on JSON array", e);
        }

        return generateComplexExpression(ids, members);
    }

    private static Collection<JSONObject> convertToJson(Collection<String> strings) {
        Set<JSONObject> objects = new HashSet<>();
        try {
            for (String str : strings) {
                objects.add(new JSONObject(str));
            }
        } catch (JSONException e) {
            LOGGER.error(String.format("Cannot convert to JSON objects: '%s'", strings), e);
        }
        return objects;
    }

    private static ComplexExpressionData generateComplexExpression(Set<String> ids, JSONArray members) {
        ComplexExpressionData expressionData = new ComplexExpressionData();
        if (!ids.isEmpty()) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(ComplexExpressionData.EXPRESSIONKEY, String.join(EXPRESSION_DELIMITER, ids));
                jsonObject.put(ComplexExpressionData.MEMBERSKEY, members);

                String json = jsonObject.toString();
                LOGGER.debug(String.format("Merged Complex Expression Data: %s", json));

                expressionData = ComplexExpressionData.newExpressionData(json);
            } catch (Exception e) {
                LOGGER.error(String.format("Cannot create new Expression Data with members: '%s'", members), e);
            }
        }
        return expressionData;
    }

    /**
     * Method find and replace expression member id to member name
     *
     * @param expressionData origin expression with member ids
     * @param memberMap      map which contains expression members id and number
     * @return ComplexExpressionData with loaded member numbers
     */
    public static ComplexExpressionData loadMembers(ComplexExpressionData expressionData, Map<String, String> memberMap) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(expressionData.toString());
            JSONArray array = jsonObject.getJSONArray(ComplexExpressionData.MEMBERSKEY);
            for (int i = 0; i < array.length(); i++) {
                JSONObject member = (JSONObject) array.get(i);
                String expression = member.getString(EXPRESSION_KEY);

                for (Map.Entry<String, String> entry : memberMap.entrySet()) {
                    expression = expression.replaceAll(entry.getKey(), entry.getValue());
                }

                member.put(EXPRESSION_KEY, expression);
            }
            jsonObject.put(ComplexExpressionData.MEMBERSKEY, array);
            return ComplexExpressionData.newExpressionData(jsonObject.toString());
        } catch (JSONException e) {
            LOGGER.error(String.format("Cannot convert to JSON: '%s'", expressionData.toString()), e);
        } catch (WTException e) {
            LOGGER.error(String.format("Cannot create new Expression Data with members: '%s'", jsonObject), e);
        }
        return expressionData;
    }

    /**
     * Replace '/', '+' operators to AND, OR.
     *
     * @param expression expression with unsupported loadFile operators
     * @return adjusted expression
     */
    public static String adjust(String expression) {
        if (StringUtils.isNotEmpty(expression)) {
            expression = expression.replaceAll("[/]", OR_OPERATOR).replaceAll("[+]", AND_OPERATOR).trim();
        }
        return expression;
    }

    /**
     * Get Display Expression value of Expression Alias Definition in WTContainer.
     *
     * @param expressionAlias source Expression Alias
     * @param wtContainerRef  WTContainer reference
     * @return Expression definition.
     */
    public static Optional<String> getDisplayExpression(ExpressionAlias expressionAlias, WTContainerRef wtContainerRef) {
        String json = null;
        try {
            json = ExpressionHelper.getDisplayExpression(expressionAlias, wtContainerRef);
            json = toExpressionData(json);
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }
        return Optional.ofNullable(json);
    }

    /**
     * Convert json Expression Alias value to String Expression Data.
     *
     * @param json Expression Alias' json value
     * @return String Expression Data
     * @throws ParseException when cannot parse JSON.
     */
    @SuppressWarnings("unchecked")
    private static String toExpressionData(String json) throws ParseException {
        LOGGER.debug("Expression Json to convert: " + json);
        json = json.replace("display", "\"display\"").replace("invalid", "\"invalid\"");  // OOTB json result has invalid format
        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) JSON_PARSER.parse(json);
        LOGGER.debug("Converted json object: " + jsonObject);

        return (String) jsonObject.getOrDefault("display", "");
    }

}
