package lk.ijse.hostel.controller.dashboard;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostel.controller.util.UiNavigateUtil;

public class DashboardFromController {
    public Label lblProfile,lblStudent,lblRoom,lblHome;
    public AnchorPane rootContext;
    public AnchorPane workingContext;
    public Label lblReservation;

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
        UiNavigateUtil.navigationForm(workingContext,"dashboard/RoomForm");
    }

    public void lblOpenStudentOnAction(MouseEvent mouseEvent) {
        lblProfile.setStyle("-fx-text-fill: #5153f5");
        lblRoom.setStyle("-fx-text-fill: #5153f5");
        lblHome.setStyle("-fx-text-fill: #5153f5");
        lblStudent.setStyle("-fx-text-fill: white");
        UiNavigateUtil.navigationForm(workingContext,"dashboard/StudentForm");
    }

    public void lblOpenProfileOnAction(MouseEvent mouseEvent) {
        lblStudent.setStyle("-fx-text-fill: #5153f5");
        lblRoom.setStyle("-fx-text-fill: #5153f5");
        lblHome.setStyle("-fx-text-fill: #5153f5");
        lblProfile.setStyle("-fx-text-fill: white");
    }

    public void lblOpenReservationOnAction(MouseEvent mouseEvent) {

        UiNavigateUtil.navigationForm(workingContext,"dashboard/ReservationForm");
    }
}
