package entity.Concert_halls;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Type_of_seats", schema = "public")
public class Type_of_seats {
    @Id
    @SequenceGenerator(name = "type_of_seats_seq", sequenceName = "type_of_seats_type_id_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "type_of_seats_seq")
    private long type_id;

    private String name;

    @OneToMany(mappedBy = "type_of_seats", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seats> seats;
}
