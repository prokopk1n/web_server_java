package entity.Concert_halls;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

    public Type_of_seats(String name) {
        this.name = name;
    }

    public boolean myEquals(Type_of_seats that){
        return that!=null && type_id == that.getType_id() && name.equals(that.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Type_of_seats that = (Type_of_seats) o;
        return type_id == that.getType_id() && name.equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(type_id, name, seats);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long type_id;

    private String name;

    @OneToMany(mappedBy = "type_of_seats", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seats> seats;
}
