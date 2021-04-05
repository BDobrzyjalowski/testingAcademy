package com.bmw.psmg.sbb.utilities.loaders.variantspecification.model;

/**
 * Model class created in purpose for easier parsing variant specification BMW choices excel input
 *
 * @author Piotr Otapowicz
 */
public class ChoiceModel {

    private String choice;
    private String choiceDescriptionDE;
    private String choiceDescriptionEN;
    private OptionModel optionModel;

    /**
     * initializes object with the most necessary field values
     *
     * @param choice      string representation of choice object
     * @param optionModel model of options where the choice should exist
     */
    public ChoiceModel(String choice, OptionModel optionModel) {
        this.choice = choice;
        this.optionModel = optionModel;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public void setChoiceDescriptionDE(String choiceDescriptionDE) {
        this.choiceDescriptionDE = choiceDescriptionDE;
    }

    public void setChoiceDescriptionEN(String choiceDescriptionEN) {
        this.choiceDescriptionEN = choiceDescriptionEN;
    }

    @Override
    public String toString() {
        return "ChoiceModel{" + "choice='" + choice + '\'' + ", choiceDescriptionDE='" + choiceDescriptionDE + '\'' + ", choiceDescriptionEN='" + choiceDescriptionEN + '\'' + ", optionModel=" + optionModel + '}';
    }
}
