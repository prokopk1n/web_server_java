package entity.Concert_halls;

import entity.Theaters.Theaters;
import entity.Tickets.Schedule;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Concert_halls", schema = "public")
public class Concert_halls {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long hall_id;

    @Column(name = "name")
    private String name;

    @Column(name = "scheme")
    private String scheme;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    private Theaters theaters;

    @OneToMany(mappedBy = "concert_halls", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seats> seats;

    @OneToMany(mappedBy = "concert_halls", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schedule> schedule;
}
