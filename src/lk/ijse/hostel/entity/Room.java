package lk.ijse.hostel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "room")
public class Room {
    @Id
    private String room_type_id;
    private String type;
    private Double key_money;
    private int qty;

    @OneToMany(mappedBy = "res_id", cascade = CascadeType.ALL)
    private List<Reservation> rooms=new ArrayList<>();

    public Room(String room_type_id, String type, Double key_money, int qty) {
        this.room_type_id = room_type_id;
        this.type = type;
        this.key_money = key_money;
        this.qty = qty;
    }

    public Room(String room_type_id) {
        this.room_type_id = room_type_id;
    }
}
