package lk.ijse.hostel.bo.custom.impl;

import lk.ijse.hostel.bo.Converter;
import lk.ijse.hostel.bo.custom.UserBO;
import lk.ijse.hostel.dao.custom.StudentDAO;
import lk.ijse.hostel.dao.custom.UserDAO;
import lk.ijse.hostel.dao.util.DAOFactory;
import lk.ijse.hostel.dto.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserBOImpl implements UserBO {
    UserDAO userDAO;
    Converter converter;

    public UserBOImpl() {
    userDAO= (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.USER);
    converter=new Converter();
    }
    @Override
    public boolean saveUser(UserDTO dto) {
        return userDAO.save(converter.fromUser(dto));
    }

    @Override
    public List<UserDTO> getAll() {
        return userDAO.getAll().stream().map(entity -> converter.toUser(entity)).collect(Collectors.toList());
    }

    @Override
    public boolean updateUser(UserDTO dto) {
        return userDAO.update(converter.fromUser(dto));
    }
}
