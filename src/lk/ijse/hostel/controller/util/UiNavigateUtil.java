package lk.ijse.hostel.controller.util;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class UiNavigateUtil {
    public static void navigationForm(AnchorPane anchorPane, String url){

        anchorPane.getChildren().clear();
        Parent parent = null;
        try {
            parent = FXMLLoader.load(UiNavigateUtil.class.getResource("/lk/ijse/hostel/view/fxml/" + url + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        anchorPane.getChildren().add(parent);
    }

    public static void newUi(AnchorPane anchorPane, String url)  {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(UiNavigateUtil.class.getResource("/lk/ijse/hostel/view/fxml/"+url+".fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.centerOnScreen();
        stage.centerOnScreen();
        stage.show();
    }

    public static void closeForm(AnchorPane anchorPane) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }

}
