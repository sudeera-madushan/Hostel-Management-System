package lk.ijse.hostel.controller.dashboard;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class DashboardFromController {
    public Label lblProfile,lblStudent,lblRoom,lblHome;

    public void lblOpenHomeOnAction(MouseEvent mouseEvent) {
        lblProfile.setStyle("-fx-text-fill: #5153f5");
        lblStudent.setStyle("-fx-text-fill: #5153f5");
        lblRoom.setStyle("-fx-text-fill: #5153f5");
        lblHome.setStyle("-fx-text-fill: white");
    }

    public void lblOpenRoomsOnAction(MouseEvent mouseEvent) {
        lblProfile.setStyle("-fx-text-fill: #5153f5");
        lblStudent.setStyle("-fx-text-fill: #5153f5");
        lblHome.setStyle("-fx-text-fill: #5153f5");
        lblRoom.setStyle("-fx-text-fill: white");
    }

    public void lblOpenStudentOnAction(MouseEvent mouseEvent) {
        lblProfile.setStyle("-fx-text-fill: #5153f5");
        lblRoom.setStyle("-fx-text-fill: #5153f5");
        lblHome.setStyle("-fx-text-fill: #5153f5");
        lblStudent.setStyle("-fx-text-fill: white");
    }

    public void lblOpenProfileOnAction(MouseEvent mouseEvent) {
        lblStudent.setStyle("-fx-text-fill: #5153f5");
        lblRoom.setStyle("-fx-text-fill: #5153f5");
        lblHome.setStyle("-fx-text-fill: #5153f5");
        lblProfile.setStyle("-fx-text-fill: white");
    }
}
