package lk.ijse.hostel.bo.custom.impl;

import lk.ijse.hostel.bo.Converter;
import lk.ijse.hostel.bo.custom.RoomBO;
import lk.ijse.hostel.dao.custom.RoomDAO;
import lk.ijse.hostel.dao.util.DAOFactory;
import lk.ijse.hostel.dto.RoomDTO;

import java.util.List;
import java.util.stream.Collectors;

public class RoomBOImpl implements RoomBO {
    RoomDAO roomDAO;
    Converter converter;

    public RoomBOImpl() {
        roomDAO= (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ROOM);
        converter=new Converter();
    }

    @Override
    public List<RoomDTO> getAll() {
        return roomDAO.getAll().stream().map(entity -> converter.toRoom(entity)).collect(Collectors.toList());
    }

    @Override
    public RoomDTO findRoom(String s) {
        return converter.toRoom(roomDAO.find(s));
    }

    @Override
    public boolean saveRoom(RoomDTO roomDTO) {
        return roomDAO.save(converter.fromRoom(roomDTO));
    }

    @Override
    public String getNextID() {
        return roomDAO.getLastId()==null ? "RM-0000" : "RM-"+String.format("%04d",Integer.parseInt(roomDAO.getLastId().split("-")[1])+1);
    }

    @Override
    public boolean checkQTY(String id) {
        return roomDAO.find(id).getQty()>0;
    }
}
