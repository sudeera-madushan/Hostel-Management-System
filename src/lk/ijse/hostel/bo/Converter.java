package lk.ijse.hostel.bo;

import lk.ijse.hostel.dto.ReservationDTO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.dto.UserDTO;
import lk.ijse.hostel.entity.Reservation;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.entity.User;
import sun.util.resources.LocaleData;

import java.sql.Date;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class Converter {
    public Student fromStudent(StudentDTO dto) {
        return new Student(dto.getStudent_id(), dto.getName(), dto.getAddress(), dto.getContact_no()
                , new Date(dto.getDob().getYear(),dto.getDob().getMonthValue(),dto.getDob().getDayOfMonth())
                , dto.getGender(), dto.getReservations().stream().map(dtos -> new Reservation(dtos.getRes_id()))
                .collect(Collectors.toList()));
    }

    public StudentDTO toStudent(Student entity) {
        return new StudentDTO(entity.getStudent_id(),entity.getName(), entity.getAddress(), entity.getContact_no()
                , LocalDate.parse(entity.getDob().toString())
                , entity.getGender());
    }

    public RoomDTO toRoom(Room entity) {
        return new RoomDTO(entity.getRoom_type_id(), entity.getType(), entity.getKey_money(), entity.getQty());
    }

    public Room fromRoom(RoomDTO dto) {
        return new Room(dto.getRoom_type_id(), dto.getType(), dto.getKey_money(), dto.getQty());
    }

    public Reservation fromReservation(ReservationDTO dto) {
        System.out.println(new Date(dto.getDate().getYear(),dto.getDate().getMonthValue(),dto.getDate().getDayOfMonth()));
        return new Reservation(dto.getRes_id()
                ,new Date(dto.getDate().getYear()-1900,dto.getDate().getMonthValue()-1,dto.getDate().getDayOfMonth())
                ,new Student(dto.getStudent().getStudent_id())
                ,new Room(dto.getRoom().getRoom_type_id())
                , dto.getStatus());
    }

    public ReservationDTO toReservation(Reservation entity) {
        return new ReservationDTO(entity.getRes_id(), entity.getDate().toLocalDate(),
                new StudentDTO(entity.getStudent().getStudent_id()),new RoomDTO(entity.getRoom().getRoom_type_id()),
                entity.getStatus());
    }

    public User fromUser(UserDTO dto) {
        return new User(dto.getId(), dto.getUser_name(), dto.getPassword());
    }

    public UserDTO toUser(User entity) {
        return new UserDTO(entity.getId(),entity.getUser_name(), entity.getPassword());
    }
}
