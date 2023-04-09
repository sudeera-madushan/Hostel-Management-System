package lk.ijse.hostel.controller.dashboard;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.custom.RoomBO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.tm.RoomTM;

import java.util.stream.Collectors;

public class RoomFormController {
    public AnchorPane workingContext;
    public TableColumn clmRoomTypeID;
    public TableColumn clmType;
    public TableColumn clmKeyMoney;
    public TableColumn clmRoomQTY;
    public TableColumn clmEdit;
    public JFXTextField txtRoomQTY;
    public JFXTextField txtKeyMoney;
    public AnchorPane roomContext;
    public Label lblRoomID;
    public JFXTextField txtRoomType;
    public TableView tblRoom;
    private RoomBO roomBO;
    private ObservableList<RoomTM> roomTMS= FXCollections.observableArrayList();
    public void initialize(){
        roomBO= (RoomBO) BOFactory.getBoFactory().getBo(BOFactory.BOType.ROOM);
        setOther();
        setTable();
    }

    private void setTable() {
        clmRoomTypeID.setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        clmType.setCellValueFactory(new PropertyValueFactory<>("type"));
        clmKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        clmRoomQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        clmEdit.setCellValueFactory(new PropertyValueFactory<>("edit"));;

        reloadTable();
    }

    private void reloadTable() {
        roomTMS.clear();
        roomTMS.addAll(roomBO.getAll().stream().map(roomDTO -> new RoomTM(
                roomDTO.getRoom_type_id(), roomDTO.getType(),roomDTO.getKey_money().toString(),roomDTO.getQty()
        )).collect(Collectors.toList()));
        tblRoom.setItems(roomTMS);
    }

    private void setOther() {
        roomContext.setVisible(false);
    }

    public void btnNewRoomOnAction(ActionEvent actionEvent) {
        roomContext.setVisible(true);
        lblRoomID.setText("Room ID :"+roomBO.getNextID());
    }

    public void btnCancelRoomOnAction(ActionEvent actionEvent) {
        roomContext.setVisible(false);
    }

    public void btnSaveRoomOnAction(ActionEvent actionEvent) {
        if(roomBO.saveRoom(new RoomDTO(
                lblRoomID.getText().split(":")[1]
                ,txtRoomType.getText()
                ,Double.parseDouble(txtKeyMoney.getText())
                ,Integer.parseInt(txtRoomQTY.getText())
        ))) {
            new Alert(Alert.AlertType.CONFIRMATION,"Room Added !!!!!!").show();
        roomContext.setVisible(false);
        reloadTable();
        }

    }
}
