package lk.ijse.hostel.bo.custom;

import lk.ijse.hostel.bo.SuperBo;
import lk.ijse.hostel.dto.StudentDTO;

public interface StudentBO extends SuperBo {
    boolean saveStudent(StudentDTO dto);
}
