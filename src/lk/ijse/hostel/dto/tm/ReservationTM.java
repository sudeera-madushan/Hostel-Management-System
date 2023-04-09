package lk.ijse.hostel.dto.tm;

import com.jfoenix.controls.JFXButton;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationTM {
    private String res_id;
    private LocalDate date;
    private String student;
    private String room;
    private String status;
    private JFXButton edit;

    public ReservationTM(String res_id, LocalDate date, String student, String room, String status) {
        this.res_id = res_id;
        this.date = date;
        this.student = student;
        this.room = room;
        this.status = status;
        this.edit=new JFXButton("Edit");
    }
}
