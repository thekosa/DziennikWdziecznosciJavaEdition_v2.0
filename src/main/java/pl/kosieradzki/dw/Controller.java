package pl.kosieradzki.dw;

import javafx.application.Platform;
import javafx.scene.control.*;

import java.time.LocalDate;

public class Controller {
    public CheckBox inBulkCheckBox;
    public CheckBox anotherDateCheckBox;
    public DatePicker datePicker;
    public TextField thankfulness1;
    public TextField thankfulness2;
    public TextField thankfulness3;
    public Label thankfulness1Label;
    public Label thankfulness2Label;
    public Label thankfulness3Label;
    public Button saveAndExitButton;
    public Button exitButton;
    public Button saveButton;
    public Button clearButton;
    public Button previewButton;

    public RadioButton baseTargetRadio;
    public RadioButton travelingTargetRadio;
    private final ToggleGroup toggleGroup = new ToggleGroup();

    public void initialize() {
        baseTargetRadio.setToggleGroup(toggleGroup);
        travelingTargetRadio.setToggleGroup(toggleGroup);
        toggleGroup.selectedToggleProperty().addListener((target) ->
                setThankfulnessesStates(toggleGroup.getSelectedToggle().toString()
                        .split(",")[0].split("id=")[1]
                        .equals(baseTargetRadio.getId()))
        );
        inBulkCheckBox.selectedProperty().addListener((target) -> setInBulkStates(inBulkCheckBox.isSelected()));
        datePicker.setValue(LocalDate.now());
        datePicker.setPromptText(LocalDate.now().toString());
        anotherDateCheckBox.selectedProperty().addListener((target) -> datePicker.setDisable(!anotherDateCheckBox.isSelected()));
    }


    public void saveAndExitButtonOnClick() {
        saveButtonOnClick();
        exitButtonOnClick();
    }

    public void clearButtonOnClick() {
        thankfulness1.setText(null);
        thankfulness2.setText(null);
        thankfulness3.setText(null);
    }

    public void saveButtonOnClick() {

    }

    public void exitButtonOnClick() {
        Platform.exit();
    }

    public void previewOnClick() {
    }

    private void setInBulkStates(boolean state) {
        saveButton.setDisable(!state);
        saveButton.setVisible(state);
        exitButton.setDisable(!state);
        exitButton.setVisible(state);
        saveAndExitButton.setDisable(state);
        saveAndExitButton.setVisible(!state);
    }

    private void setThankfulnessesStates(boolean state) {
        thankfulness1Label.setVisible(state);
        thankfulness2Label.setVisible(state);
        thankfulness3Label.setVisible(state);
        thankfulness2.setVisible(state);
        thankfulness3.setVisible(state);
        thankfulness1Label.setDisable(!state);
        thankfulness2Label.setDisable(!state);
        thankfulness3Label.setDisable(!state);
        thankfulness2.setDisable(!state);
        thankfulness3.setDisable(!state);
    }
}