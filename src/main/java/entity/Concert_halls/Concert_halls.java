package entity.Concert_halls;

import entity.Theaters.Theaters;
import entity.Tickets.Schedule;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

    public Concert_halls(String name, String scheme, Theaters theaters) {
        this.name = name;
        this.scheme = scheme;
        this.theaters = theaters;
    }

    public Concert_halls(){
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Concert_halls that = (Concert_halls) o;
        return hall_id == that.getHall_id() && name.equals(that.getName()) && scheme.equals(that.getScheme())
                && theaters.equals(that.getTheaters());
    }

    @Override
    public int hashCode() {
        return Objects.hash(hall_id, name, scheme, theaters, seats, schedule);
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
