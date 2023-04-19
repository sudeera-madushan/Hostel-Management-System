package lk.ijse.hostel.bo.custom;

import lk.ijse.hostel.bo.SuperBo;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.dto.UserDTO;

import java.util.List;

public interface UserBO extends SuperBo {
    boolean saveUser(UserDTO dto);

    List<UserDTO> getAll();

    boolean updateUser(UserDTO dto);

}
