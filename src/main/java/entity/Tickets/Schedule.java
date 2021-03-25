package entity.Tickets;

import entity.Concert_halls.Concert_halls;
import entity.Performances.Performances;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="Schedule", schema = "public")
public class Schedule {
    @Override
    public int hashCode() {
        return Objects.hash(event_id, concert_halls, performances, tickets, date);
    }

    public long getEvent_id() {
        return event_id;
    }

    public void setEvent_id(long event_id) {
        this.event_id = event_id;
    }

    public Concert_halls getConcert_halls() {
        return concert_halls;
    }

    public void setConcert_halls(Concert_halls concert_halls) {
        this.concert_halls = concert_halls;
    }

    public Performances getPerformances() {
        return performances;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Schedule() {
    }

    public Schedule(Concert_halls concert_halls, Performances performances, LocalDateTime date) {
        this.concert_halls = concert_halls;
        this.performances = performances;
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
        if (o == null) return false;
        Schedule schedule = (Schedule) o;
        return event_id == schedule.getEvent_id() && concert_halls.equals(schedule.getConcert_halls())
                && performances.equals(schedule.getPerformances()) && date.equals(schedule.getDate());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private long event_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hall_id")
    private Concert_halls concert_halls;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id")
    private Performances performances;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tickets> tickets;

    @Column(name="date")
    private LocalDateTime date;
}
