package entity.Concert_halls;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Type_of_seats", schema = "public")
public class Type_of_seats {

    public long getType_id() {
        return type_id;
    }

    public void setType_id(long type_id) {
        this.type_id = type_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Seats> getSeats() {
        return seats;
    }

    public void setSeats(List<Seats> seats) {
        this.seats = seats;
    }

    public Type_of_seats() {
    }

    public Type_of_seats(long type_id, String name) {
        this.type_id = type_id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long type_id;

    private String name;

    @OneToMany(mappedBy = "type_of_seats", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seats> seats;
}
