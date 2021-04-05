package com.bmw.psmg.sbb.utilities.loaders.variantspecification.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Model class created in purpose for easier parsing variant specification BMW attributes excel input
 *
 * @author Piotr Otapowicz
 */
public class AttributeModel {

    private String sop;
    private String eop;
    private List<String> plants;
    private String typeCategory;
    private String state;

    /**
     * Constructor initializes only plants field, rest are done by setters
     */
    public AttributeModel() {
        plants = new ArrayList<>();
    }

    public void setSop(String sop) {
        this.sop = sop;
    }

    public void setEop(String eop) {
        this.eop = eop;
    }

    public void setTypeCategory(String typeCategory) {
        this.typeCategory = typeCategory;
    }

    @Override
    public String toString() {
        return "AttributeModel{" + "sop='" + sop + '\'' + ", eop='" + eop + '\'' + ", plants=" + plants + ", typeCategory='" + typeCategory + '\'' + ", state='" + state + '\'' + '}';
    }

    /**
     * Maps all attributes into string,
     *
     * @return parsed attributes to string, eq. BMW_TYP_SOP=11/1/16;BMW_TYP_EOP=6/30/20;BMW_TYPE_CATEGORY=Serientyp;BMW_PLANT=02.40;
     */
    public String mapAttributesToString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.sop != null) {
            stringBuilder.append("BMW_TYP_SOP=").append(sop).append(";");
        }
        if (this.eop != null) {
            stringBuilder.append("BMW_TYP_EOP=").append(eop).append(";");
        }
        if (this.typeCategory != null) {
            stringBuilder.append("BMW_TYPE_CATEGORY=").append(typeCategory).append(";");
        }
        if (this.plants != null) {
            stringBuilder.append("BMW_PLANT=").append(String.join(",", plants)).append(";");
        }
        return stringBuilder.toString();
    }

    /**
     * adds new plant string value to plants field
     *
     * @param value string value of plant
     */
    public void addPlant(String value) {
        plants.add(value);
    }

    /**
     * Set Type Filter state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Get Type Filter state
     *
     * @return state
     */
    public String getState() {
        return state;
    }
}
