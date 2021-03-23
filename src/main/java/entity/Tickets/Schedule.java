package entity.Tickets;

import entity.Concert_halls.Concert_halls;
import entity.Performances.Performances;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="Schedule", schema = "public")
public class Schedule {
    public long getEvent_id() {
        return event_id;
    }

    public void setEvent_id(long event_id) {
        this.event_id = event_id;
    }


    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setConcert_halls(Concert_halls concert_halls) {
        this.concert_halls = concert_halls;
    }

    public void setPerformances(Performances performances) {
        this.performances = performances;
    }

    public List<Tickets> getTickets() {
        return tickets;
    }

    public void setTickets(List<Tickets> tickets) {
        this.tickets = tickets;
    }

    public Schedule() {
    }

    public Schedule(long event_id, Timestamp date) {
        this.event_id = event_id;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "event_id=" + event_id +
                ", concert_halls=" + concert_halls +
                ", performances=" + performances +
                ", tickets=" + tickets +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return event_id == schedule.event_id && Objects.equals(concert_halls, schedule.concert_halls) && Objects.equals(performances, schedule.performances) && Objects.equals(tickets, schedule.tickets) && Objects.equals(date, schedule.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(event_id, concert_halls, performances, tickets, date);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private long event_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hall_id")
    private Concert_halls concert_halls;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perf_id")
    private Performances performances;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tickets> tickets;

    @Column(name="date")
    private Timestamp date;
}
