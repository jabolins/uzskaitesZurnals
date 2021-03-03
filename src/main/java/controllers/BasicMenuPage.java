package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BasicMenuPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane basicBorderPane;

    @FXML
    private Button butHome;

    @FXML
    private ToggleButton butDb;

    @FXML
    private Button butRetCompany;

    @FXML
    private Button butRegDocument;

    @FXML
    void initialize() {

        butHome.setOnAction(event -> {
            butHome.getScene().getWindow().hide();
            goToPage("/basicPage.fxml");
        });
        butRetCompany.setOnAction(event -> {
            butRetCompany.getScene().getWindow().hide();
            goToPage("/addCompany.fxml");
        });
    }

    private void goToPage(String page) {
        FXMLLoader load = new FXMLLoader();
        load.setLocation(getClass().getResource(page));
        try {
            load.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent run = load.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Uzskaites žurnāls");
        stage.setScene(new Scene(run));
        stage.show();
    }
}
