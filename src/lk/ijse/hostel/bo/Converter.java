package lk.ijse.hostel.bo;

import lk.ijse.hostel.dto.ReservationDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.Reservation;
import lk.ijse.hostel.entity.Student;

import java.sql.Date;
import java.util.stream.Collectors;

public class Converter {
    public Student fromStudent(StudentDTO dto) {
        return new Student(dto.getStudent_id(), dto.getName(), dto.getAddress(), dto.getContact_no()
                , new Date(dto.getDob().getYear(),dto.getDob().getMonthValue(),dto.getDob().getDayOfMonth())
                , dto.getGender(), dto.getReservations().stream().map(dtos -> new Reservation(dtos.getRes_id()))
                .collect(Collectors.toList()));
    }
}
