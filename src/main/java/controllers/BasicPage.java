package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BasicPage {
    private final PageTransition pageTransition = new PageTransition();

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
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button butLogin;

    @FXML
    void home(ActionEvent event) {

    }

    @FXML
    void initialize() {
        butLogin.setOnAction(event -> {
            butLogin.getScene().getWindow().hide();
            pageTransition.goToPage("/basicMenuPage.fxml");
        });
    }

}
