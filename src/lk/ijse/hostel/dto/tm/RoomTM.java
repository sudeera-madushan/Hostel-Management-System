package lk.ijse.hostel.dto.tm;

import com.jfoenix.controls.JFXButton;
import lk.ijse.hostel.dto.ReservationDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class RoomTM {
    private String room_type_id;
    private String type;
    private String key_money;
    private int qty;
    private JFXButton edit;

    public RoomTM(String room_type_id, String type, String key_money, int qty) {
        this.room_type_id = room_type_id;
        this.type = type;
        this.key_money = key_money;
        this.qty = qty;
        this.edit=new JFXButton("Edit");
    }
}
