package entity.Tickets;

import entity.Concert_halls.Seats;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="Tickets", schema="public")
public class Tickets {
    public long getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(long ticket_id) {
        this.ticket_id = ticket_id;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Seats getSeats() {
        return seats;
    }

    public void setSeats(Seats seats) {
        this.seats = seats;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public boolean isIn_stock() {
        return in_stock;
    }

    public void setIn_stock(boolean in_stock) {
        this.in_stock = in_stock;
    }

    public Tickets() {
    }

    public Tickets(Schedule schedule, Seats seats, float cost, boolean in_stock) {
        this.schedule = schedule;
        this.seats = seats;
        this.cost = cost;
        this.in_stock = in_stock;
    }

    @Override
    public String toString() {
        return "Tickets{" +
                "ticket_id=" + ticket_id +
                ", schedule=" + schedule +
                ", seats=" + seats +
                ", cost=" + cost +
                ", in_stock=" + in_stock +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Tickets tickets = (Tickets) o;
        return ticket_id == tickets.getTicket_id() && Float.compare(tickets.getCost(), cost) == 0
                && in_stock == tickets.isIn_stock() && schedule.equals(tickets.getSchedule()) && seats.equals(tickets.getSeats());
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticket_id, schedule, seats, cost, in_stock);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ticket_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id")
    private Seats seats;

    @Column(name="cost")
    private float cost;

    @Column(name="in_stock")
    private boolean in_stock;
}
