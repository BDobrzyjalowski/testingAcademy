package com.bmw.psmg.sbb.utilities.loaders.variantspecification.model;

/**
 * Model class created in purpose for easier parsing variant specification BMW options of choices excel input
 *
 * @author Piotr Otapowicz
 */
public class OptionModel {

    private String option;
    private String optionDescriptionDE;
    private String optionDescriptionEN;

    /**
     * Initializes the object with all of the fields
     *
     * @param option              string representation of option
     * @param optionDescriptionDE option description in German
     * @param optionDescriptionEN option description in English
     */
    public OptionModel(String option, String optionDescriptionDE, String optionDescriptionEN) {
        this.option = option;
        this.optionDescriptionDE = optionDescriptionDE;
        this.optionDescriptionEN = optionDescriptionEN;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "OptionModel{" + "option='" + option + '\'' + ", optionDescriptionDE='" + optionDescriptionDE + '\'' + ", optionDescriptionEN='" + optionDescriptionEN + '\'' + '}';
    }
}
