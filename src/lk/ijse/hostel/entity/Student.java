package lk.ijse.hostel.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "student")
public class Student {
    @Id
    private String student_id;
    private String name;
    private String address;
    private String contact_no;
    private Date dob;
    private String gender;
    @OneToMany(mappedBy = "res_id", cascade = CascadeType.ALL)
    private List<Reservation> reservations=new ArrayList<>();

    public Student(String student_id) {
        this.student_id = student_id;
    }
}
