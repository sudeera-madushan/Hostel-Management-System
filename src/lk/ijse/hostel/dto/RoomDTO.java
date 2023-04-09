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
    private String key_money;
    private int qty;
    private List<ReservationDTO> reservationDTOS=new ArrayList<>();
}
