package lk.ijse.hostel.dao.custom;

import lk.ijse.hostel.dao.util.CrudDAO;
import lk.ijse.hostel.entity.Reservation;

import java.util.List;

public interface ReservationDAO extends CrudDAO<Reservation,String> {
    Reservation find(String id);
}
