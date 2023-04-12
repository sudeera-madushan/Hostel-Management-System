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
import javafx.scene.paint.Color;
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
    private boolean isUpdate=false;

    public void initialize(){
        roomBO= (RoomBO) BOFactory.getBoFactory().getBo(BOFactory.BOType.ROOM);
        setOther();
        setTable();
        setTextField();
    }

    private void setTextField() {
        txtRoomType.setOnKeyReleased(event -> chechTextField());
        txtKeyMoney.setOnKeyReleased(event -> {
            try {
                Double.parseDouble(txtKeyMoney.getText());
            }catch (Exception e){
                txtKeyMoney.setText("");
            }
            chechTextField();
        });
        txtRoomQTY.setOnKeyReleased(event -> {
            try {
                Integer.parseInt(txtRoomQTY.getText());
            }catch (Exception e){
                txtRoomQTY.setText("");
            }
            chechTextField();
        });

    }

    private boolean chechTextField() {
        if (txtRoomType.getText().equals("")){
            txtRoomType.setUnFocusColor(Color.RED);
            txtRoomType.setFocusColor(Color.RED);
            return false;
        }else {
            txtRoomType.setUnFocusColor(Color.GREEN);
            txtRoomType.setFocusColor(Color.GREEN);
            if (txtKeyMoney.getText().equals("")){
                txtKeyMoney.setUnFocusColor(Color.RED);
                txtKeyMoney.setFocusColor(Color.RED);
                return false;
            }else {
                txtKeyMoney.setUnFocusColor(Color.GREEN);
                txtKeyMoney.setFocusColor(Color.GREEN);
                if (txtRoomQTY.getText().equals("")){
                    txtRoomQTY.setUnFocusColor(Color.RED);
                    txtRoomQTY.setFocusColor(Color.RED);
                    return false;
                }else {
                    txtRoomQTY.setUnFocusColor(Color.GREEN);
                    txtRoomQTY.setFocusColor(Color.GREEN);
                    return true;
                }
            }
        }
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
        int i=0;
        for (RoomDTO roomDTO : roomBO.getAll()) {
            RoomTM roomTM = new RoomTM(
                    roomDTO.getRoom_type_id(), roomDTO.getType(), roomDTO.getKey_money().toString(), roomDTO.getQty());
            roomTM.getEdit().setId(String.valueOf(i));
            roomTM.getEdit().setOnAction(event -> editRoomOnAction(Integer.parseInt(roomTM.getEdit().getId())));
            i++;
            roomTMS.add(roomTM);
        }

        tblRoom.setItems(roomTMS);
    }

    private void editRoomOnAction(int i) {
        roomContext.setVisible(true);
        isUpdate=true;
        RoomTM tm = roomTMS.get(i);
        lblRoomID.setText("Room ID :"+tm.getRoom_type_id());
        txtRoomType.setText(tm.getType());
        txtKeyMoney.setText(tm.getKey_money());
        txtRoomQTY.setText(String.valueOf(tm.getQty()));
    }

    private void setOther() {
        roomContext.setVisible(false);
    }

    public void btnNewRoomOnAction(ActionEvent actionEvent) {
        roomContext.setVisible(true);
        txtRoomQTY.setText("");
        txtKeyMoney.setText("");
        txtRoomType.setText("");
        lblRoomID.setText("Room ID :"+roomBO.getNextID());
        isUpdate=false;
    }

    public void btnCancelRoomOnAction(ActionEvent actionEvent) {
        roomContext.setVisible(false);
    }

    public void btnSaveRoomOnAction(ActionEvent actionEvent) {
        if (chechTextField()) {

            if(isUpdate){
                if (roomBO.updateRoom(new RoomDTO(
                        lblRoomID.getText().split(":")[1]
                        , txtRoomType.getText()
                        , Double.parseDouble(txtKeyMoney.getText())
                        , Integer.parseInt(txtRoomQTY.getText())
                ))) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Room Updated !!!!!!").show();
                    roomContext.setVisible(false);
                }
            }else {
                if (roomBO.saveRoom(new RoomDTO(
                        lblRoomID.getText().split(":")[1]
                        , txtRoomType.getText()
                        , Double.parseDouble(txtKeyMoney.getText())
                        , Integer.parseInt(txtRoomQTY.getText())
                ))) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Room Added !!!!!!").show();
                    roomContext.setVisible(false);
                }
            }
                    reloadTable();

        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid Data !!!!!!").show();
        }
    }
}
