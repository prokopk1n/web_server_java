package entity.Concert_halls;

import entity.Tickets.Tickets;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


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

    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
    }

    public Character getSide() {
        return side;
    }

    public void setSide(Character side) {
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

    public Seats(Integer section, Character side, int row, int seat, Concert_halls concert_halls, Type_of_seats type_of_seats) {
        this.section = section;
        this.side = side;
        this.row = row;
        this.seat = seat;
        this.type_of_seats = type_of_seats;
        this.concert_halls = concert_halls;
    }

    public boolean myEquals(Seats that){
        return that!=null && seat_id==that.getSeat_id() && section==that.getSection() && side == that.getSide()
                && row == that.getRow() && seat == that.getSeat() && concert_halls.myEquals(that.getConcert_halls()) && type_of_seats.myEquals(that.getType_of_seats());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Seats that = (Seats) o;
        System.out.println(concert_halls.equals(that.getConcert_halls()));
        System.out.println(type_of_seats.equals(that.getType_of_seats()));
        return seat_id==that.getSeat_id() && (section==null && that.getSection()==null || section!=null && section.equals(that.getSection()))
                && (side==null && that.getSide()==null || side!=null && side.equals(that.getSide()))
                && row == that.getRow() && seat == that.getSeat() && concert_halls.equals(that.getConcert_halls()) && type_of_seats.equals(that.getType_of_seats());
    }

    @Override
    public int hashCode() {
        return Objects.hash(seat_id, section, side, row, seat, concert_halls, type_of_seats, tickets);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seat_id;

    private Integer section;
    private Character side;
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
