package pl.kosieradzki.dw;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.awt.Desktop;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    public Label isFileExistsLabel;

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

    public void clearButtonOnClick() {
        thankfulness1.setText(null);
        thankfulness2.setText(null);
        thankfulness3.setText(null);
    }

    public void saveButtonOnClick() throws IOException {
        Entry entry = new Entry();
        List<String> thankfulnessesList = new ArrayList<>();
        thankfulnessesList.add(thankfulness1.getText() == null ? "" : thankfulness1.getText());
        if (baseTargetRadio.isSelected()) {
            thankfulnessesList.add(thankfulness2.getText() == null ? "" : thankfulness2.getText());
            thankfulnessesList.add(thankfulness3.getText() == null ? "" : thankfulness3.getText());
        }
        entry.setCollection(thankfulnessesList);
        entry.setDate(datePicker.getValue());

        PrintWriter printWriter = new PrintWriter(new FileWriter("DziennikWdziecznosci.txt", true));
        printWriter.println(entry.getEntry());
        printWriter.close();
        isFileExistsLabel.setVisible(false);
        clearButtonOnClick();
    }

    public void previewOnClick() throws IOException, InterruptedException {
        Desktop desktop = Desktop.getDesktop();
        File file = new File("DziennikWdziecznosci.txt");
        if (file.exists()) {
            isFileExistsLabel.setVisible(false);
            desktop.open(file);
        } else {
            isFileExistsLabel.setVisible(true);
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> isFileExistsLabel.setVisible(false));
            delay.play();
        }
    }

    public void exitButtonOnClick() {
        Platform.exit();
    }

    public void saveAndExitButtonOnClick() throws IOException {
        saveButtonOnClick();
        exitButtonOnClick();
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