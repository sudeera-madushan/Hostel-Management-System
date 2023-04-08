package lk.ijse.hostel.controller.dashboard;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
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

    public void initialize(){
        otherDataSet();
    }

    private void otherDataSet() {
        studentContext.setVisible(false);
    }

    public void btnNewStudentOnAction(ActionEvent actionEvent) {
        studentContext.setVisible(true);
        gender.selectToggle(gender.getToggles().get(1));
        System.out.println(gender.getToggles().get(1));

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(new Student("s001","","","",new Date(2000,00,00),"",null));
        transaction.commit();
        session.close();

    }

    public void btnSaveStudentOnAction(ActionEvent actionEvent) {
        studentContext.setVisible(false);
    }

    public void btnCancelStudentOnAction(ActionEvent actionEvent) {
        studentContext.setVisible(false);
    }
}
