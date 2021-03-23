package entity.Tickets;

import entity.Concert_halls.Seats;

import javax.persistence.*;

@Entity
@Table(name="Tickets", schema="public")
public class Tickets {
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
