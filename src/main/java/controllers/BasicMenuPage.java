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
    private Button butRegCompany;

    @FXML
    private Button butRegDocument;

    @FXML
    void initialize() {

        butHome.setOnAction(event -> {
            butHome.getScene().getWindow().hide();
            pageTransition.goToPage("/basicPage.fxml");
        });
        butRegCompany.setOnAction(event -> {
            butRegCompany.getScene().getWindow().hide();
            pageTransition.goToPage("/addCompany.fxml");
        });

        butRegDocument.setOnAction(event -> {
            butRegDocument.getScene().getWindow().hide();
            pageTransition.goToPage("/addPaymentDocument.fxml");
        });
    }

}
