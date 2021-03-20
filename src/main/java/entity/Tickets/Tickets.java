package entity.Tickets;

import entity.Concert_halls.Seats;

import javax.persistence.*;

@Entity
@Table(name="entity.Tickets", schema="public")
public class Tickets {
    @Id
    @SequenceGenerator(name = "tickets_seq", sequenceName = "tickets_ticket_id_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "tickets_seq")
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
