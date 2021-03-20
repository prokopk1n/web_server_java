package entity.Performances;

import entity.Theaters.Theaters;
import entity.Tickets.Schedule;

import javax.persistence.*;
import java.sql.*;
import java.util.List;

@Entity
@Table(name="entity.Performances", schema = "public")
public class Performances {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }


    public long getPerformance_id() {
        return performance_id;
    }

    public void setPerformance_id(long performance_id) {
        this.performance_id = performance_id;
    }

    public Theaters getTheater() {
        return theater;
    }

    public void setTheater(Theaters theater) {
        this.theater = theater;
    }

    public List<Perf_persons> getPerf_persons() {
        return perf_persons;
    }

    public void setPerf_persons(List<Perf_persons> perf_persons) {
        this.perf_persons = perf_persons;
    }

    public Performances() {
    }


    @Id
    @SequenceGenerator(name = "performances_seq", sequenceName = "performances_performance_id_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "performances_seq")
    private long performance_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    private Theaters theater;

    @Column(name = "name")
    private String name;

    @Column(name = "duration")
    private Time duration;

    @Column(name="start")
    private Date start;

    @Column(name="finish")
    private Date finish;

    @Column(name="description")
    private String description;

    @Column(name="poster")
    private String poster;

    @OneToMany(mappedBy = "performances", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Perf_persons> perf_persons;

    @OneToMany(mappedBy = "performances", cascade = CascadeType.ALL, orphanRemoval = true) // имя поля на стороне владельца
    private List<Schedule> schedule;

}
