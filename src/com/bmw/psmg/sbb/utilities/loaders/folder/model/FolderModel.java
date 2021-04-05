package com.bmw.psmg.sbb.utilities.loaders.folder.model;

/**
 * Class is used for representing data from Excel file containing information about folder structure
 *
 * @author Pawel Miron
 */
public class FolderModel {

    private String group;
    private String designationDE;
    private String designationEN;

    /**
     * Create object representing data from Excel file containing information about folder structure
     */
    public FolderModel() {
    }

    /**
     * Create object representing data from Excel file containing information about folder structure
     *
     * @param group         label
     * @param designationDE german locale designation label
     * @param designationEN english locale designation label
     */
    public FolderModel(String group, String designationDE, String designationEN) {
        this.group = group;
        this.designationDE = designationDE;
        this.designationEN = designationEN;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDesignationDE() {
        return designationDE;
    }

    public void setDesignationDE(String designationDE) {
        this.designationDE = designationDE;
    }

    public String getDesignationEN() {
        return designationEN;
    }

    public void setDesignationEN(String designationEN) {
        this.designationEN = designationEN;
    }

    @Override
    public String toString() {
        return "FolderModel{" + "group='" + group + '\'' + ", designationDE='" + designationDE + '\'' + ", designationEN='" + designationEN + '\'' + '}';
    }
}
