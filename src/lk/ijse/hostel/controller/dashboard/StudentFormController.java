package lk.ijse.hostel.controller.dashboard;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.custom.StudentBO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;

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
    private StudentBO studentBO;
    public void initialize(){
        studentBO= (StudentBO) BOFactory.getBoFactory().getBo(BOFactory.BOType.STUDENT);
        otherDataSet();
    }

    private void otherDataSet() {
        studentContext.setVisible(false);
    }

    public void btnNewStudentOnAction(ActionEvent actionEvent) {
        studentContext.setVisible(true);
        lblStudentID.setText("ST-0001");

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
                lblStudentID.getText()
                ,txtName.getText()
                ,txtAddress.getText()
                , txtContactNo.getText()
                ,dtpDOB.getValue()
                ,gender.getToggles().get(0).isSelected()? "Male" : "Female"
        ))){
            new Alert(Alert.AlertType.NONE,"Student Added !!!!!!!!!!!");
        }

    }

    public void btnCancelStudentOnAction(ActionEvent actionEvent) {
        studentContext.setVisible(false);
    }
}
