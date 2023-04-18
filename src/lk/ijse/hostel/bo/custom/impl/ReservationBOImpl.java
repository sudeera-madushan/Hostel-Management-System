package lk.ijse.hostel.bo.custom.impl;

import lk.ijse.hostel.bo.Converter;
import lk.ijse.hostel.bo.custom.ReservationBO;
import lk.ijse.hostel.dao.custom.ReservationDAO;
import lk.ijse.hostel.dao.custom.RoomDAO;
import lk.ijse.hostel.dao.util.DAOFactory;
import lk.ijse.hostel.dto.ReservationDTO;
import lk.ijse.hostel.entity.Room;

import java.util.List;
import java.util.stream.Collectors;

public class ReservationBOImpl implements ReservationBO {
    ReservationDAO reservationDAO;
    RoomDAO roomDAO;
    Converter converter;

    public ReservationBOImpl() {
        reservationDAO= (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.RESERVATION);
        roomDAO= (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ROOM);
        converter=new Converter();
    }

    @Override
    public String getNextID() {
        return reservationDAO.getLastId()==null ? "RS-0000" : "RS-"+String.format("%04d",Integer.parseInt(reservationDAO.getLastId().split("-")[1])+1);
    }

    @Override
    public boolean saveReservation(ReservationDTO dto) {

        return reservationDAO.save(converter.fromReservation(dto));
    }

    @Override
    public List<ReservationDTO> getAll() {
        return reservationDAO.getAll().stream().map(entity -> converter.toReservation(entity)).collect(Collectors.toList());
    }

    @Override
    public boolean updateReservation(ReservationDTO dto) {
        return reservationDAO.update(converter.fromReservation(dto));
    }

    @Override
    public ReservationDTO findReservation(String id) {
        return converter.toReservation(reservationDAO.find(id));
    }

    @Override
    public Long getReservationCount() {
        return reservationDAO.getCount();
    }

    @Override
    public Long getReservationCountPending() {
        return reservationDAO.getNoPayCount();
    }
}
