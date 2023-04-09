package lk.ijse.hostel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomDTO {
    private String room_type_id;
    private String type;
    private Double key_money;
    private int qty;
    private List<ReservationDTO> reservationDTOS=new ArrayList<>();

    public RoomDTO(String room_type_id, String type, Double key_money, int qty) {
        this.room_type_id = room_type_id;
        this.type = type;
        this.key_money = key_money;
        this.qty = qty;
    }

    public RoomDTO(String room_type_id) {
        this.room_type_id = room_type_id;
    }
}
