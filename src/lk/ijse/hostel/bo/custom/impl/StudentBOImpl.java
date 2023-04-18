package lk.ijse.hostel.bo.custom.impl;

import com.sun.javafx.binding.StringFormatter;
import lk.ijse.hostel.bo.Converter;
import lk.ijse.hostel.bo.custom.StudentBO;
import lk.ijse.hostel.dao.custom.StudentDAO;
import lk.ijse.hostel.dao.util.DAOFactory;
import lk.ijse.hostel.dto.StudentDTO;

import java.text.Format;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<StudentDTO> getAll() {
        return studentDAO.getAll().stream().map(entity -> converter.toStudent(entity)).collect(Collectors.toList());
    }

    @Override
    public String getNextID() {
        return studentDAO.getLastId()==null ? "ST-0000" : "ST-"+
                String.format("%04d",Integer.parseInt(studentDAO.getLastId().split("-")[1])+1);
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) {
        return studentDAO.update(converter.fromStudent(studentDTO));
    }

    @Override
    public StudentDTO findStudent(String s) {
        return converter.toStudent(studentDAO.find(s));
    }

    @Override
    public Long getStudentCount() {
        return studentDAO.getCount();
    }
}
