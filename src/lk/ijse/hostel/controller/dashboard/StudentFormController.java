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
import lk.ijse.hostel.bo.custom.RoomBO;
import lk.ijse.hostel.bo.custom.StudentBO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.dto.tm.StudentTM;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.util.FactoryConfiguration;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.List;
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
    private StudentBO studentBO;
    private RoomBO roomBO;
    private ObservableList<StudentTM> studentTMS= FXCollections.observableArrayList();
    public void initialize(){
        studentBO= (StudentBO) BOFactory.getBoFactory().getBo(BOFactory.BOType.STUDENT);
        roomBO= (RoomBO) BOFactory.getBoFactory().getBo(BOFactory.BOType.ROOM);
        otherDataSet();
        setTable();
    }

    private void reloadTable() {
        studentTMS.clear();
        tblStudent.getItems().clear();
        studentTMS.addAll(studentBO.getAll().stream().map(dto -> new StudentTM(dto.getStudent_id()
                        , dto.getName(), dto.getAddress(), dto.getContact_no(), dto.getDob(), dto.getGender()))
                .collect(Collectors.toList()));

        tblStudent.setItems(studentTMS);
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
        List<RoomDTO> rooms=roomBO.getAll();
        AutoCompletionBinding<String> binding = TextFields.bindAutoCompletion(txtFindRoom, rooms.stream().map(dto ->
                new String("#" + dto.getRoom_type_id() + " " + dto.getType())).collect(Collectors.toList()));
        binding.setOnAutoCompleted(event -> setRoomLable(roomBO.findRoom(txtFindRoom.getText().split(" ")[0]
                .split("#")[1])));
            txtFindRoom.setOnAction(event -> System.out.println(txtFindRoom.getText().split(" ")[0]
                    .split("#")[1]));
        txtFindRoom.setOnAction(event -> setRoomLable(roomBO.findRoom(txtFindRoom.getText().split(" ")[0]
                .split("#")[1])));
    }

    private void setRoomLable(RoomDTO room) {
        lblRoomDetails.setText("Room Type ID :"+room.getRoom_type_id()+"   Type :"+room.getType()
                +"   Key Money :(Rs)"+room.getKey_money()+"   QTY :"+room.getQty());
    }

    public void btnNewStudentOnAction(ActionEvent actionEvent) {
        studentContext.setVisible(true);
        lblStudentID.setText("Student ID :"+studentBO.getNextID());


//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        session.save(new Student("s001","","","",new Date(2000,00,00),"",null));
//        transaction.commit();
//        session.close();

    }

    public void btnSaveStudentOnAction(ActionEvent actionEvent) {
        studentContext.setVisible(false);
        System.out.println(dtpDOB.getValue().toString());
        if(studentBO.saveStudent(new StudentDTO(
                lblStudentID.getText().split(":")[1]
                ,txtName.getText()
                ,txtAddress.getText()
                , txtContactNo.getText()
                ,dtpDOB.getValue()
                ,gender.getToggles().get(0).isSelected()? "Male" : "Female"
        ))){
            new Alert(Alert.AlertType.NONE,"Student Added !!!!!!!!!!!");
        }
        reloadTable();
    }

    public void btnCancelStudentOnAction(ActionEvent actionEvent) {
        studentContext.setVisible(false);
    }
}
