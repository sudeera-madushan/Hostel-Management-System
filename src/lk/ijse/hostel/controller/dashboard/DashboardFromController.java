package lk.ijse.hostel.controller.dashboard;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.custom.ReservationBO;
import lk.ijse.hostel.bo.custom.RoomBO;
import lk.ijse.hostel.bo.custom.StudentBO;
import lk.ijse.hostel.bo.custom.UserBO;
import lk.ijse.hostel.controller.util.UiNavigateUtil;
import lk.ijse.hostel.dto.UserDTO;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class DashboardFromController {
    public AnchorPane paneProfile, paneStudent, paneRoom, paneHome,paneReservation;
    public AnchorPane rootContext;
    public AnchorPane workingContext;
    public AnchorPane profileContext;
    public JFXPasswordField pxtPassword;
    public JFXTextField txtUserName;
    public JFXTextField txtPassword;
    public ImageView imgShowPassword;
    public ImageView imgHidePassword;
    private StudentBO studentBO;
    private RoomBO roomBO;
    private ReservationBO reservationBO;
    private UserBO userBO;

    public void initialize(){
        studentBO= (StudentBO) BOFactory.getBoFactory().getBo(BOFactory.BOType.STUDENT);
        roomBO= (RoomBO) BOFactory.getBoFactory().getBo(BOFactory.BOType.ROOM);
        reservationBO= (ReservationBO) BOFactory.getBoFactory().getBo(BOFactory.BOType.RESERVATION);
        setBarChart();
        profileContext.setVisible(false);
        setUserContext();

        paneProfile.setStyle("-fx-background-color: none");
        paneStudent.setStyle("-fx-background-color: none");
        paneRoom.setStyle("-fx-background-color: none");
        paneReservation.setStyle("-fx-background-color: none");
        paneHome.setStyle("-fx-background-color: #adb5bd");
    }

    private void setUserContext() {
        userBO= (UserBO) BOFactory.getBoFactory().getBo(BOFactory.BOType.USER);
        txtPassword.setVisible(false);
        imgHidePassword.setVisible(false);
        pxtPassword.setOnKeyReleased(event -> txtPassword.setText(pxtPassword.getText()));
        txtPassword.setOnKeyReleased(event -> pxtPassword.setText(txtPassword.getText()));
    }


    private void setBarChart() {
        UiNavigateUtil.navigationForm(workingContext,"dashboard/HomeForm");
    }

    public void lblOpenHomeOnAction(MouseEvent mouseEvent) {
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
//        paneStudent.setStyle("-fx-background-color: none");
//        paneRoom.setStyle("-fx-background-color: none");
//        paneHome.setStyle("-fx-background-color: none");
//        paneReservation.setStyle("-fx-background-color: none");
//        paneProfile.setStyle("-fx-background-color: #adb5bd");
        profileContext.setVisible(true);
        txtUserName.setText(userBO.getAll().get(0).getUser_name());
        txtPassword.setText(userBO.getAll().get(0).getPassword());
        pxtPassword.setText(userBO.getAll().get(0).getPassword());
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

    public void btnUpdateUserOnAction(ActionEvent actionEvent) {
        UserDTO userDTO = userBO.getAll().get(0);
        userDTO.setUser_name(txtUserName.getText());
        userDTO.setPassword(pxtPassword.getText());
        if (userBO.updateUser(userDTO)){
            new Alert(Alert.AlertType.CONFIRMATION, "Want Create Account !!!").show();
            UiNavigateUtil.newUi(rootContext,"dashboard/DashboardFrom");
        }else new Alert(Alert.AlertType.ERROR, "Account Cannot Create !!!").show();
    }

    public void btnCancelUserOnAction(ActionEvent actionEvent) {
        profileContext.setVisible(false);
    }

    public void imgShowPasswordOnAction(MouseEvent mouseEvent) {
        pxtPassword.setVisible(false);
        imgShowPassword.setVisible(false);
        txtPassword.setVisible(true);
        imgHidePassword.setVisible(true);
    }

    public void imgHidePasswordOnAction(MouseEvent mouseEvent) {
        txtPassword.setVisible(false);
        imgHidePassword.setVisible(false);
        pxtPassword.setVisible(true);
        imgShowPassword.setVisible(true);

    }

    public void logoutOnAction(MouseEvent mouseEvent) {
        UiNavigateUtil.newUi(rootContext,"sign/LoginForm");
    }
}
