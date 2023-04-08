package lk.ijse.hostel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationDTO {
    private String res_id;
    private Date date;
    private StudentDTO student;
    private RoomDTO room;
    private String status;
}
