package lk.ijse.hostel.bo.custom;

import com.sun.xml.internal.stream.Entity;
import lk.ijse.hostel.bo.SuperBo;
import lk.ijse.hostel.dto.ReservationDTO;

import java.util.List;

public interface ReservationBO extends SuperBo {
    String getNextID();

    boolean saveReservation(ReservationDTO dto);

    List<ReservationDTO> getAll();

    boolean updateReservation(ReservationDTO dto);

    ReservationDTO findReservation(String id);

    Long getReservationCount();

    Long getReservationCountPending();

    Long getReservationCountByDate(String s);
}
