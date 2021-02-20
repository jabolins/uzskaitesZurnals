package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage pamataSkatuve;
  private static BorderPane basicBorderPane;

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.pamataSkatuve = primaryStage;
        this.pamataSkatuve.setTitle("Uzskaites žurnāls");
        this.pamataSkatuve.setHeight(800);
        this.pamataSkatuve.setWidth(800);

        showPamataLogu();
        showLapu("/loginPage.fxml", basicBorderPane);
    }


    private void showPamataLogu() throws IOException {
        FXMLLoader  loading= new FXMLLoader();
        loading.setLocation(Main.class.getResource("/basicFrame.fxml"));
         basicBorderPane =loading.load();
        Scene scene= new Scene(basicBorderPane,800,700);
        pamataSkatuve.setScene(scene);
        pamataSkatuve.show();

    } // tas ir sākumam


    public static void showLapu(String lapasAdrese, BorderPane basicBorderPane) throws IOException {
        FXMLLoader ielade= new FXMLLoader();
        ielade.setLocation(Main.class.getResource(lapasAdrese));
        BorderPane bpPievienot = ielade.load();
        basicBorderPane.setCenter(bpPievienot);
    } // no jebkura Controller var
    // aktivizēt nokļūšanu uz vajadzīgo lapu


    public static void main(String[] args) {
        launch(args);
    }
}
