package lk.ijse.hostel.dao.custom;

import lk.ijse.hostel.dao.util.CrudDAO;
import lk.ijse.hostel.entity.Room;

public interface RoomDAO extends CrudDAO<Room,String> {
    Room find(String s);
}
