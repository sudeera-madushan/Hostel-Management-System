package lk.ijse.hostel.controller.dashboard;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

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

    public void btnNewReservationOnAction(ActionEvent actionEvent) {
    }

    public void btnCancelReservationOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveReservationOnAction(ActionEvent actionEvent) {
    }

    public void btnCancelStudentOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveStudentOnAction(ActionEvent actionEvent) {
    }
}
