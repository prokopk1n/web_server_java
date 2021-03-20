package entity.Tickets;

import entity.Concert_halls.Concert_halls;
import entity.Performances.Performances;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

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

    @Id
    @SequenceGenerator(name = "schedule_seq", sequenceName = "schedule_event_id_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "schedule_seq")
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
