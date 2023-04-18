package lk.ijse.hostel.controller.dashboard;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.custom.ReservationBO;
import lk.ijse.hostel.bo.custom.RoomBO;
import lk.ijse.hostel.bo.custom.StudentBO;
import lk.ijse.hostel.controller.util.UiNavigateUtil;

public class DashboardFromController {
    public AnchorPane paneProfile, paneStudent, paneRoom, paneHome,paneReservation;
    public AnchorPane rootContext;
    public AnchorPane workingContext;
    public Label lblStudentCount;
    public Label lblRoomCount;
    public Label lblReservationCount;
    public Label lblPendingPayment;
    private StudentBO studentBO;
    private RoomBO roomBO;
    private ReservationBO reservationBO;

    public void initialize(){
        studentBO= (StudentBO) BOFactory.getBoFactory().getBo(BOFactory.BOType.STUDENT);
        roomBO= (RoomBO) BOFactory.getBoFactory().getBo(BOFactory.BOType.ROOM);
        reservationBO= (ReservationBO) BOFactory.getBoFactory().getBo(BOFactory.BOType.RESERVATION);
        setDataRowCount();
    }

    private void setDataRowCount() {
        lblStudentCount.setText(String.valueOf(studentBO.getStudentCount()));
        lblRoomCount.setText(String.valueOf(roomBO.getRoomCount()));
        lblReservationCount.setText(String.valueOf(reservationBO.getReservationCount()));
        lblPendingPayment.setText(String.valueOf(reservationBO.getReservationCountPending()));
    }

    public void lblOpenHomeOnAction(MouseEvent mouseEvent) {
        paneProfile.setStyle("-fx-background-color: none");
        paneStudent.setStyle("-fx-background-color: none");
        paneRoom.setStyle("-fx-background-color: none");
        paneReservation.setStyle("-fx-background-color: none");
        paneHome.setStyle("-fx-background-color: #adb5bd");
    }

    public void lblOpenRoomsOnAction(MouseEvent mouseEvent) {
        paneProfile.setStyle("-fx-background-color: none");
        paneStudent.setStyle("-fx-background-color: none");
        paneHome.setStyle("-fx-background-color: none");
        paneReservation.setStyle("-fx-background-color: none");
        paneRoom.setStyle("-fx-background-color: #adb5bd");
        UiNavigateUtil.navigationForm(workingContext,"dashboard/RoomForm");
    }

    public void lblOpenStudentOnAction(MouseEvent mouseEvent) {
        paneProfile.setStyle("-fx-background-color: none");
        paneRoom.setStyle("-fx-background-color: none");
        paneHome.setStyle("-fx-background-color: none");
        paneReservation.setStyle("-fx-background-color: none");
        paneStudent.setStyle("-fx-background-color: #adb5bd");
        UiNavigateUtil.navigationForm(workingContext,"dashboard/StudentForm");
    }

    public void lblOpenProfileOnAction(MouseEvent mouseEvent) {
        paneStudent.setStyle("-fx-background-color: none");
        paneRoom.setStyle("-fx-background-color: none");
        paneHome.setStyle("-fx-background-color: none");
        paneReservation.setStyle("-fx-background-color: none");
        paneProfile.setStyle("-fx-background-color: #adb5bd");
    }

    public void lblOpenReservationOnAction(MouseEvent mouseEvent) {
        paneStudent.setStyle("-fx-background-color: none");
        paneRoom.setStyle("-fx-background-color: none");
        paneHome.setStyle("-fx-background-color: none");
        paneReservation.setStyle("-fx-background-color: #adb5bd");
        paneProfile.setStyle("-fx-background-color: none");
        UiNavigateUtil.navigationForm(workingContext,"dashboard/ReservationForm");
    }

    public void minimizeOnAction(MouseEvent mouseEvent) {
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setIconified(true);
    }

    static boolean isRestorDown=true;
    public void maximizeOnAction(MouseEvent mouseEvent) {
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        if (isRestorDown) {
            window.setFullScreen(true);
            isRestorDown=false;
        }else {
            window.setFullScreen(false);
            isRestorDown=true;
        }
    }

    public void closeOnAction(MouseEvent mouseEvent) {
        Platform.exit();
    }
}
