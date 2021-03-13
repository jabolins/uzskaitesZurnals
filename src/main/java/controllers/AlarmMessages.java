package controllers;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class AlarmMessages {
    public AlarmMessages() {
    }

    void alarmPopUpWindow(String alarmText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(alarmText);
        alert.setTitle("Brīdinājums");
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
    }
}