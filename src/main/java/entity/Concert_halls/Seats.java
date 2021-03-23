package entity.Concert_halls;

import entity.Tickets.Tickets;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Seats", schema = "public")
public class Seats {

    public long getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(long seat_id) {
        this.seat_id = seat_id;
    }

    public Concert_halls getConcert_halls() {
        return concert_halls;
    }

    public void setConcert_halls(Concert_halls concert_halls) {
        this.concert_halls = concert_halls;
    }

    public Type_of_seats getType_of_seats() {
        return type_of_seats;
    }

    public void setType_of_seats(Type_of_seats type_of_seats) {
        this.type_of_seats = type_of_seats;
    }

    public List<Tickets> getTickets() {
        return tickets;
    }

    public void setTickets(List<Tickets> tickets) {
        this.tickets = tickets;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public char getSide() {
        return side;
    }

    public void setSide(char side) {
        this.side = side;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public Seats() {
    }

    public Seats(long seat_id, int section, char side, int row, int seat) {
        this.seat_id = seat_id;
        this.section = section;
        this.side = side;
        this.row = row;
        this.seat = seat;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seat_id;

    private int section;
    private char side;
    private int row;
    private int seat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hall_id")
    private Concert_halls concert_halls;

    @ManyToOne
    @JoinColumn(name = "seat_type")
    private Type_of_seats type_of_seats;

    @OneToMany(mappedBy = "seats", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tickets> tickets;
}
