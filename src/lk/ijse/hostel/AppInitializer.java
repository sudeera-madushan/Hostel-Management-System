package lk.ijse.hostel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/hostel/view/fxml/sign/LoginForm.fxml"))));
        primaryStage.centerOnScreen();
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.show();
    }
}
