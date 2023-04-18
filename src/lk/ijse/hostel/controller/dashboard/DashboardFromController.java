package lk.ijse.hostel.controller.dashboard;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.custom.ReservationBO;
import lk.ijse.hostel.bo.custom.RoomBO;
import lk.ijse.hostel.bo.custom.StudentBO;
import lk.ijse.hostel.controller.util.UiNavigateUtil;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class DashboardFromController {
    public AnchorPane paneProfile, paneStudent, paneRoom, paneHome,paneReservation;
    public AnchorPane rootContext;
    public AnchorPane workingContext;
    private StudentBO studentBO;
    private RoomBO roomBO;
    private ReservationBO reservationBO;

    public void initialize(){
        studentBO= (StudentBO) BOFactory.getBoFactory().getBo(BOFactory.BOType.STUDENT);
        roomBO= (RoomBO) BOFactory.getBoFactory().getBo(BOFactory.BOType.ROOM);
        reservationBO= (ReservationBO) BOFactory.getBoFactory().getBo(BOFactory.BOType.RESERVATION);
        setBarChart();
    }


    private void setBarChart() {
        UiNavigateUtil.navigationForm(workingContext,"dashboard/HomeForm");
    }

    public void lblOpenHomeOnAction(MouseEvent mouseEvent) {
        System.out.println("Yannethoo");
        paneProfile.setStyle("-fx-background-color: none");
        paneStudent.setStyle("-fx-background-color: none");
        paneRoom.setStyle("-fx-background-color: none");
        paneReservation.setStyle("-fx-background-color: none");
        paneHome.setStyle("-fx-background-color: #adb5bd");
        UiNavigateUtil.navigationForm(workingContext,"dashboard/HomeForm");
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
