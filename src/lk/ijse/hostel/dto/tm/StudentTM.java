package lk.ijse.hostel.dto.tm;

import com.jfoenix.controls.JFXButton;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentTM {
    String student_Id;
    String name;
    String address;
    String contact_no;
    LocalDate dob;
    String gender;
    JFXButton edit;

    public StudentTM(String student_Id, String name, String address, String contact_no, LocalDate dob, String gender) {
        this.student_Id = student_Id;
        this.name = name;
        this.address = address;
        this.contact_no = contact_no;
        this.dob = dob;
        this.gender = gender;
        this.edit=new JFXButton("Edit");
    }
}
