package lk.ijse.hostel.bo.custom.impl;

import lk.ijse.hostel.bo.Converter;
import lk.ijse.hostel.bo.custom.StudentBO;
import lk.ijse.hostel.dao.custom.StudentDAO;
import lk.ijse.hostel.dao.util.DAOFactory;
import lk.ijse.hostel.dto.StudentDTO;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO;
    Converter converter;
    public StudentBOImpl() {
        studentDAO= (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.STUDENT);
        converter=new Converter();
    }

    @Override
    public boolean saveStudent(StudentDTO dto) {
        return studentDAO.save(converter.fromStudent(dto));
    }
}
