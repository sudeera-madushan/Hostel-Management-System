package lk.ijse.hostel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "reservation")
public class Reservation {
    @Id
    private String res_id;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "room_type_id", referencedColumnName = "room_type_id")
    private Room room;

    private String status;

    public Reservation(String res_id) {
        this.res_id = res_id;
    }
}
