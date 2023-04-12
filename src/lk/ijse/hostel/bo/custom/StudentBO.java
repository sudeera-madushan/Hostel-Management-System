package lk.ijse.hostel.bo.custom;

import lk.ijse.hostel.bo.SuperBo;
import lk.ijse.hostel.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBo {
    boolean saveStudent(StudentDTO dto);

    List<StudentDTO> getAll();

    String getNextID();

    boolean updateStudent(StudentDTO studentDTO);

    StudentDTO findStudent(String s);
}
