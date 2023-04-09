package lk.ijse.hostel.bo.custom;

import lk.ijse.hostel.bo.SuperBo;
import lk.ijse.hostel.dto.RoomDTO;

import java.util.List;

public interface RoomBO extends SuperBo {
    List<RoomDTO> getAll();

    RoomDTO findRoom(String s);
}