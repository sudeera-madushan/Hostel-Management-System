package lk.ijse.hostel.controller.dashboard;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.custom.ReservationBO;
import lk.ijse.hostel.bo.custom.RoomBO;
import lk.ijse.hostel.bo.custom.StudentBO;
import lk.ijse.hostel.dto.ReservationDTO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.dto.tm.ReservationTM;
import org.controlsfx.control.textfield.TextFields;

import java.util.stream.Collectors;

public class ReservationFormController {
    public AnchorPane workingContext;
    public TableColumn clmReservationID;
    public TableColumn clmDate;
    public TableColumn clmStudentID;
    public TableColumn clmRoomTypeID;
    public TableColumn clmStatus;
    public TableColumn clmEdit;
    public JFXDatePicker dtpDate;
    public ToggleGroup status;
    public JFXTextField txtRoom;
    public JFXTextField txtStudent;
    public Label lblReservationID;
    public AnchorPane reservationContext;
    public Label lblRoomDetails;
    public JFXTextField txtFindRoom;
    public JFXDatePicker dtpDOB;
    public ToggleGroup gender;
    public JFXTextField txtContactNo;
    public JFXTextField txtAddress;
    public JFXTextField txtName;
    public Label lblStudentID;
    public AnchorPane studentContext;
    public TableView tblReservation;
    private ReservationBO reservationBO;
    private StudentBO studentBO;
    private RoomBO roomBO;
    private ObservableList<ReservationTM> reservationTMS= FXCollections.observableArrayList();
    public void initialize(){
        reservationBO= (ReservationBO) BOFactory.getBoFactory().getBo(BOFactory.BOType.RESERVATION);
        studentBO= (StudentBO) BOFactory.getBoFactory().getBo(BOFactory.BOType.STUDENT);
        roomBO= (RoomBO) BOFactory.getBoFactory().getBo(BOFactory.BOType.ROOM);
        setOther();
        setTable();
    }
    private void setTable() {
        clmReservationID.setCellValueFactory(new PropertyValueFactory<>("res_id"));
        clmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmStudentID.setCellValueFactory(new PropertyValueFactory<>("student"));
        clmRoomTypeID.setCellValueFactory(new PropertyValueFactory<>("room"));
        clmStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        clmEdit.setCellValueFactory(new PropertyValueFactory<>("edit"));

        reloadTable();

    }

    private void reloadTable() {
        reservationTMS.clear();
        reservationTMS.addAll(reservationBO.getAll().stream().map(dto -> new ReservationTM(dto.getRes_id()
                ,dto.getDate(),dto.getStudent().getStudent_id(),dto.getRoom().getRoom_type_id(), dto.getStatus()))
                .collect(Collectors.toList()));
        tblReservation.setItems(reservationTMS);
    }

    private void setOther() {
        TextFields.bindAutoCompletion(txtStudent,studentBO.getAll().stream().map(dto ->
                ("#"+dto.getStudent_id()+"   "+dto.getName())).collect(Collectors.toList()));
        TextFields.bindAutoCompletion(txtRoom,roomBO.getAll().stream().map(dto ->
                ("#"+dto.getRoom_type_id()+"   "+dto.getType())).collect(Collectors.toList()));
    }

    public void btnNewReservationOnAction(ActionEvent actionEvent) {
        reservationContext.setVisible(true);
        lblReservationID.setText("Reservation ID :"+reservationBO.getNextID());
    }

    public void btnCancelReservationOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveReservationOnAction(ActionEvent actionEvent) {
        if(roomBO.checkQTY(txtRoom.getText().split(" ")[0].split("#")[1])){
            if(reservationBO.saveReservation(new ReservationDTO(lblReservationID.getText().split(":")[1], dtpDate.getValue()
                    ,new StudentDTO(txtStudent.getText().split(" ")[0].split("#")[1])
                    ,new RoomDTO(txtRoom.getText().split(" ")[0].split("#")[1])
                    ,status.getToggles().get(0).isSelected() ? "Payed" : "Not Payed"))){
                reservationContext.setVisible(false);
                new Alert(Alert.AlertType.CONFIRMATION, "Reservation Added !!!").show();
                reloadTable();
            }else {
            new Alert(Alert.AlertType.ERROR, "Reservation Cannot Success !!!").show();}
        }else {new Alert(Alert.AlertType.ERROR, "Room Not Yet !!!").show();}
    }

    public void btnCancelStudentOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveStudentOnAction(ActionEvent actionEvent) {
    }
}
