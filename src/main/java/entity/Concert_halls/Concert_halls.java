package entity.Concert_halls;

import entity.Theaters.Theaters;
import entity.Tickets.Schedule;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Concert_halls", schema = "public")
public class Concert_halls {

    public long getHall_id() {
        return hall_id;
    }

    public void setHall_id(long hall_id) {
        this.hall_id = hall_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public Theaters getTheaters() {
        return theaters;
    }

    public void setTheaters(Theaters theaters) {
        this.theaters = theaters;
    }

    public List<Seats> getSeats() {
        return seats;
    }

    public void setSeats(List<Seats> seats) {
        this.seats = seats;
    }

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }

    public Concert_halls(long hall_id, String name, String scheme) {
        this.hall_id = hall_id;
        this.name = name;
        this.scheme = scheme;
    }

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
