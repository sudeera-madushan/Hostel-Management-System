package lk.ijse.hostel.controller.dashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.custom.ReservationBO;
import lk.ijse.hostel.bo.custom.RoomBO;
import lk.ijse.hostel.bo.custom.StudentBO;
import lk.ijse.hostel.dto.ReservationDTO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.dto.tm.ReservationTM;
import lk.ijse.hostel.util.Regex;
import org.controlsfx.control.textfield.TextFields;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
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
    public JFXCheckBox chbNoPay;
    public JFXButton btnNewStudent;
    public JFXButton btnNewRoom;
    public JFXButton btnCloseReservation;
    private ReservationBO reservationBO;
    private StudentBO studentBO;
    private RoomBO roomBO;
    private ObservableList<ReservationTM> reservationTMS= FXCollections.observableArrayList();
    private boolean isUpdate=false;

    public void initialize(){
        reservationBO= (ReservationBO) BOFactory.getBoFactory().getBo(BOFactory.BOType.RESERVATION);
        studentBO= (StudentBO) BOFactory.getBoFactory().getBo(BOFactory.BOType.STUDENT);
        roomBO= (RoomBO) BOFactory.getBoFactory().getBo(BOFactory.BOType.ROOM);
        setOther();
        setTable();
        setTextField();
        reservationContext.setVisible(false);
        status.getToggles().get(0).setSelected(true);
        gender.getToggles().get(0).setSelected(true);
    }

    private void setTextField() {
        txtStudent.setOnKeyReleased(event -> checkResTextField());
        txtRoom.setOnKeyReleased(event -> checkResTextField());
        txtName.setOnKeyReleased(event -> checkStdTextField());
        txtAddress.setOnKeyReleased(event -> checkStdTextField());
        txtContactNo.setOnKeyReleased(event -> checkStdTextField());

    }

    private boolean checkResTextField() {
        if (txtStudent.getText().equals("")) {
            txtStudent.setFocusColor(Color.RED);
            txtStudent.setUnFocusColor(Color.RED);
            return false;
        }else {
            txtStudent.setFocusColor(Color.GREEN);
            txtStudent.setUnFocusColor(Color.GREEN);
            if (txtRoom.getText().equals("")) {
                txtRoom.setFocusColor(Color.RED);
                txtRoom.setUnFocusColor(Color.RED);
                return false;
            }else {
                txtRoom.setFocusColor(Color.GREEN);
                txtRoom.setUnFocusColor(Color.GREEN);
                return true;
            }
        }
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
        List<ReservationDTO> dtos = reservationBO.getAll();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate currentDate=LocalDate.parse(new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime()),formatter);
        Collections.reverse(dtos);
        int i=0;
        for (ReservationDTO dto : dtos) {
            ReservationTM tm = new ReservationTM(dto.getRes_id()
                    , dto.getDate(), dto.getStudent().getStudent_id(), dto.getRoom().getRoom_type_id(), dto.getStatus());
                tm.getEdit().setId(String.valueOf(i));

            long days = ChronoUnit.DAYS.between(tm.getDate(), currentDate);

                if (days>365){
                    tm.getEdit().setStyle("-fx-background-color: red");
                }

                tm.getEdit().setOnAction(event -> editReservationOnAction(Integer.parseInt(tm.getEdit().getId())));
                i++;
                reservationTMS.add(tm);
        }
        tblReservation.setRowFactory(tv -> new TableRow<ReservationTM>() {
            @Override
            protected void updateItem(ReservationTM item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || ChronoUnit.DAYS.between(item.getDate(), currentDate)<365)
                    setStyle("");
                else
                    setStyle("-fx-background-color: #61a5c2;");
            }
        });
        tblReservation.setItems(reservationTMS);
    }

    private void setOther() {
        TextFields.bindAutoCompletion(txtStudent,studentBO.getAll().stream().map(dto ->
                ("#"+dto.getStudent_id()+"   "+dto.getName())).collect(Collectors.toList()));
        TextFields.bindAutoCompletion(txtRoom,roomBO.getAll().stream().map(dto ->
                ("#"+dto.getRoom_type_id()+"   "+dto.getType())).collect(Collectors.toList()));
    }

    public void btnNewReservationOnAction(ActionEvent actionEvent) {
        isUpdate=false;
        reservationContext.setVisible(true);
        btnNewStudent.setVisible(true);
        txtStudent.setText("");
        txtRoom.setText("");
        dtpDate.setValue(null);
        lblReservationID.setText("Reservation ID :"+reservationBO.getNextID());
    }

    public void btnCancelReservationOnAction(ActionEvent actionEvent) {
        reservationContext.setVisible(false);
    }

    public void btnSaveReservationOnAction(ActionEvent actionEvent) {

        if (checkResTextField() & dtpDate.getValue() != null) {
            if (!isUpdate) {
                if (checkRecentStudent()){
                    if (roomBO.checkQTY(txtRoom.getText().split(" ")[0].split("#")[1])) {
                        if (reservationBO.saveReservation(new ReservationDTO(lblReservationID.getText().split(":")[1], dtpDate.getValue()
                                , new StudentDTO(txtStudent.getText().split(" ")[0].split("#")[1])
                                , new RoomDTO(txtRoom.getText().split(" ")[0].split("#")[1])
                                , status.getToggles().get(0).isSelected() ? "Payed" : "Not Payed"))) {
                            reservationContext.setVisible(false);
                            new Alert(Alert.AlertType.CONFIRMATION, "Reservation Added !!!").show();
                            reloadTable();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Reservation Cannot Success !!!").show();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Room Not Yet !!!").show();
                    }
            }else new Alert(Alert.AlertType.ERROR, "Student Already Reserved !!!").show();
            }else {
                if (reservationBO.updateReservation(new ReservationDTO(lblReservationID.getText().split(":")[1], dtpDate.getValue()
                        , new StudentDTO(txtStudent.getText().split(" ")[0].split("#")[1])
                        , new RoomDTO(txtRoom.getText().split(" ")[0].split("#")[1])
                        , status.getToggles().get(0).isSelected() ? "Payed" : "Not Payed"))) {
                    reservationContext.setVisible(false);
                    new Alert(Alert.AlertType.CONFIRMATION, "Reservation Updated !!!").show();
                    reloadTable();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Reservation Cannot Updated !!!").show();
                }
            }
        }else {new Alert(Alert.AlertType.ERROR, "Invalid Data !!!").show();}
    }

    private boolean checkRecentStudent() {
        String id = txtStudent.getText().split(" ")[0].split("#")[1];
        for (ReservationTM tm : reservationTMS) {
            if (id.equals(tm.getStudent())) {
                if (!tm.getStatus().equals("Complete")){
                return false;
                }
            }
        }
        return true;
    }

    public void btnCancelStudentOnAction(ActionEvent actionEvent) {
        studentContext.setVisible(false);
        reservationContext.setVisible(true);

    }

    public void btnSaveStudentOnAction(ActionEvent actionEvent) {
        if (dtpDOB.getValue()!=null){
            if (checkStdTextField()) {
                    if (studentBO.saveStudent(new StudentDTO(
                            lblStudentID.getText().split(":")[1]
                            , txtName.getText()
                            , txtAddress.getText()
                            , txtContactNo.getText()
                            , dtpDOB.getValue()
                            , gender.getToggles().get(0).isSelected() ? "Male" : "Female"
                    ))) {
                        studentContext.setVisible(false);
                        setOther();
                        txtStudent.setText("#" + lblStudentID.getText().split(":")[1] +"   "+ txtName.getText());
                        reservationContext.setVisible(true);
                        new Alert(Alert.AlertType.CONFIRMATION, "Student Added !!!!!!!!!!!").show();
                    }
                }
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid DOB !!!!!!!!!!!").show();
        }
    }

    public void chbNoPayOnAction(ActionEvent actionEvent) {
            reservationTMS.clear();
        if (chbNoPay.isSelected()) {
            int i=0;
            for (ReservationDTO dto : reservationBO.getAll()) {
                ReservationTM tm = new ReservationTM(dto.getRes_id()
                        , dto.getDate(), dto.getStudent().getStudent_id(), dto.getRoom().getRoom_type_id(), dto.getStatus());
                if (tm.getStatus().equals("Not Payed")){
                    tm.getEdit().setId(String.valueOf(i));
                    tm.getEdit().setOnAction(event -> editReservationOnAction(Integer.parseInt(tm.getEdit().getId())));
                    reservationTMS.add(tm);
                    i++;
                }
            }
            tblReservation.setItems(reservationTMS);
        }else reloadTable();
    }

    private void editReservationOnAction(int i) {
        isUpdate=true;
        reservationContext.setVisible(true);
        btnNewStudent.setVisible(false);
        ReservationTM tm = reservationTMS.get(i);
        lblReservationID.setText("Reservation ID :"+tm.getRes_id());
        StudentDTO student = studentBO.findStudent(tm.getStudent());
        txtStudent.setText("#"+ student.getStudent_id()+"   "+student.getName());
        RoomDTO room = roomBO.findRoom(tm.getRoom());
        txtRoom.setText("#"+ room.getRoom_type_id()+"   "+room.getType());
        txtStudent.setEditable(false);
        txtRoom.setEditable(false);
        dtpDate.setValue(tm.getDate());
        status.getToggles().get(tm.getStatus().equals("Payed") ? 0 : 1).setSelected(true);
    }

    public void btnNewStudentOnAction(ActionEvent actionEvent) {
        studentContext.setVisible(true);
        reservationContext.setVisible(false);
        lblRoomDetails.setVisible(true);
        lblRoomDetails.setText("");
        txtFindRoom.setVisible(true);
        txtFindRoom.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtContactNo.setText("");
        dtpDOB.setValue(null);
        lblStudentID.setText("Student ID :"+studentBO.getNextID());
    }
    private boolean checkStdTextField(){
        if (Regex.name.matcher(txtName.getText()).matches()) {
            txtName.setFocusColor(Color.GREEN);
            txtName.setUnFocusColor(Color.GREEN);
            if (Regex.address.matcher(txtAddress.getText()).matches()) {
                txtAddress.setFocusColor(Color.GREEN);
                txtAddress.setUnFocusColor(Color.GREEN);
                if (Regex.contact.matcher(txtContactNo.getText()).matches()) {
                    txtContactNo.setFocusColor(Color.GREEN);
                    txtContactNo.setUnFocusColor(Color.GREEN);
                    return true;
                }else{
                    txtContactNo.setFocusColor(Color.RED);
                    txtContactNo.setUnFocusColor(Color.RED);
                    return false;
                }
            }else{
                txtAddress.setFocusColor(Color.RED);
                txtAddress.setUnFocusColor(Color.RED);
                return false;
            }
        }else{
            txtName.setFocusColor(Color.RED);
            txtName.setUnFocusColor(Color.RED);
            return false;
        }
    }

    public void btnCloseReservationOnAction(ActionEvent actionEvent) {
        if (studentBO.updateStudent(studentBO.findStudent(txtStudent.getText().split(" ")[0].split("#")[1]))){

        }
    }
}
