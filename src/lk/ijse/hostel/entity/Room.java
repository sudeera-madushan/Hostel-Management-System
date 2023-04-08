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
    private String key_money;
    private int qty;

    @OneToMany(mappedBy = "res_id", cascade = CascadeType.ALL)
    private List<Reservation> rooms=new ArrayList<>();
}
