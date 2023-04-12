package lk.ijse.hostel.controller.dashboard;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.sun.prism.paint.Paint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.custom.RoomBO;
import lk.ijse.hostel.bo.custom.StudentBO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.dto.tm.StudentTM;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.util.FactoryConfiguration;
import lk.ijse.hostel.util.Regex;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.awt.*;
import java.sql.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StudentFormController {
    public AnchorPane workingContext;
    public TableColumn clmStudentID;
    public TableColumn clmName;
    public TableColumn clmAddress;
    public TableColumn clmContactNo;
    public TableColumn clmDOB;
    public TableColumn clmGender;
    public TableColumn clmEdit;
    public AnchorPane studentContext;
    public Label lblStudentID;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNo;
    public ToggleGroup gender;
    public JFXDatePicker dtpDOB;
    public JFXTextField txtFindRoom;
    public TableView tblStudent;
    public Label lblRoomDetails;
    public Label lblTextFindRoom;
    public Label lblTitle;
    private StudentBO studentBO;
    private RoomBO roomBO;
    private ObservableList<StudentTM> studentTMS= FXCollections.observableArrayList();
    private boolean isUpdate;

    public void initialize(){
        studentBO= (StudentBO) BOFactory.getBoFactory().getBo(BOFactory.BOType.STUDENT);
        roomBO= (RoomBO) BOFactory.getBoFactory().getBo(BOFactory.BOType.ROOM);
        otherDataSet();
        setTable();
        setTextField();

    }

    private void setTextField() {
        txtName.setOnKeyReleased(event -> checkTextField());
        txtAddress.setOnKeyReleased(event -> checkTextField());
        txtContactNo.setOnKeyReleased(event -> checkTextField());
    }

    private boolean checkTextField(){
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
    private void reloadTable() {
        studentTMS.clear();
        tblStudent.getItems().clear();
//        studentTMS.addAll(studentBO.getAll().stream().map(dto -> new StudentTM(dto.getStudent_id()
//                        , dto.getName(), dto.getAddress(), dto.getContact_no(), dto.getDob(), dto.getGender()))
//                .collect(Collectors.toList()));
        int i=0;
        for (StudentDTO dto : studentBO.getAll()) {
            StudentTM tm = new StudentTM(dto.getStudent_id()
                    , dto.getName(), dto.getAddress(), dto.getContact_no(), dto.getDob(), dto.getGender());
            tm.getEdit().setId(String.valueOf(i));
            tm.getEdit().setOnAction(event -> editButtonOnAction(Integer.parseInt(tm.getEdit().getId())));
            studentTMS.add(tm);
            i++;
        }
        tblStudent.setItems(studentTMS);
    }

    private void editButtonOnAction(int i) {
        isUpdate=true;
        studentContext.setVisible(true);
        lblTitle.setText("Update Student");
        lblTextFindRoom.setVisible(false);
        lblRoomDetails.setVisible(false);
        txtFindRoom.setVisible(false);
        StudentTM tm = studentTMS.get(i);
        lblStudentID.setText("Student ID :"+tm.getStudent_Id());
        txtName.setText(tm.getName());
        txtAddress.setText(tm.getAddress());
        txtContactNo.setText(tm.getContact_no());
        dtpDOB.setValue(tm.getDob());
        if (tm.getGender().equals("Male")) {
            gender.getToggles().get(0).setSelected(true);
        }else if (tm.getGender().equals("Female")){
            gender.getToggles().get(1).setSelected(true);
        }
    }

    private void setTable() {
        clmStudentID.setCellValueFactory(new PropertyValueFactory<>("student_Id"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clmContactNo.setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        clmDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        clmGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        clmEdit.setCellValueFactory(new PropertyValueFactory<>("edit"));

        reloadTable();

    }

    private void otherDataSet() {
        studentContext.setVisible(false);
        gender.getToggles().get(0).setSelected(true);
        List<RoomDTO> rooms=roomBO.getAll();
        AutoCompletionBinding<String> binding = TextFields.bindAutoCompletion(txtFindRoom, rooms.stream().map(dto ->
                new String("#" + dto.getRoom_type_id() + " " + dto.getType())).collect(Collectors.toList()));
        binding.setOnAutoCompleted(event -> setRoomLable(roomBO.findRoom(txtFindRoom.getText().split(" ")[0]
                .split("#")[1])));
        txtFindRoom.setOnAction(event -> setRoomLable(roomBO.findRoom(txtFindRoom.getText().split(" ")[0]
                .split("#")[1])));
    }

    private void setRoomLable(RoomDTO room) {
        lblRoomDetails.setText("Room Type ID :"+room.getRoom_type_id()+"\t\tType :"+room.getType()
                +"\nKey Money :(Rs)"+room.getKey_money()+"\t\tQTY :"+room.getQty());
    }

    public void btnNewStudentOnAction(ActionEvent actionEvent) {
        isUpdate=false;
        studentContext.setVisible(true);
        lblTitle.setText("Add Student");
        lblTextFindRoom.setVisible(true);
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

    public void btnSaveStudentOnAction(ActionEvent actionEvent) {
        if (dtpDOB.getValue()!=null){
            if (checkTextField()) {
                if (isUpdate){
                    if (studentBO.updateStudent(new StudentDTO(
                            lblStudentID.getText().split(":")[1]
                            , txtName.getText()
                            , txtAddress.getText()
                            , txtContactNo.getText()
                            , dtpDOB.getValue()
                            , gender.getToggles().get(0).isSelected() ? "Male" : "Female"
                    ))) {
                        studentContext.setVisible(false);
                        new Alert(Alert.AlertType.CONFIRMATION, "Student Updated !!!!!!!!!!!").show();
                    }
                }else {
                    if (studentBO.saveStudent(new StudentDTO(
                            lblStudentID.getText().split(":")[1]
                            , txtName.getText()
                            , txtAddress.getText()
                            , txtContactNo.getText()
                            , dtpDOB.getValue()
                            , gender.getToggles().get(0).isSelected() ? "Male" : "Female"
                    ))) {
                        studentContext.setVisible(false);
                        new Alert(Alert.AlertType.CONFIRMATION, "Student Added !!!!!!!!!!!").show();
                    }
                }
                reloadTable();
            }else {
                new Alert(Alert.AlertType.ERROR, "Invalid Data !!!!!!!!!!!").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid DOB !!!!!!!!!!!").show();
        }

    }

    public void btnCancelStudentOnAction(ActionEvent actionEvent) {
        studentContext.setVisible(false);
    }
}
