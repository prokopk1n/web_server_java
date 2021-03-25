package entity.Performances;

import entity.Theaters.Theaters;
import entity.Tickets.Schedule;

import javax.persistence.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="Performances", schema = "public")
public class Performances {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getFinish() {
        return finish;
    }

    public void setFinish(LocalDate finish) {
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
        return theaters;
    }

    public void setTheater(Theaters theater) {
        this.theaters = theater;
    }

    public List<Perf_persons> getPerf_persons() {
        return perf_persons;
    }

    public void setPerf_persons(List<Perf_persons> perf_persons) {
        this.perf_persons = perf_persons;
    }

    public Performances() {
    }

    public Performances(String name, LocalTime duration, LocalDate start, LocalDate finish, String description, String poster, Theaters theaters) {
        this.name = name;
        this.duration = duration;
        this.start = start;
        this.finish = finish;
        this.description = description;
        this.poster = poster;
        this.theaters = theaters;
    }

    public boolean myEquals(Performances that) {
        return that!=null && performance_id == that.getPerformance_id() && name.equals(that.getName()) && duration.equals(that.getDuration())
                && start.equals(that.getStart()) && finish.equals(that.getFinish()) && description.equals(that.getDescription())
                && poster.equals(that.getPoster()) && theaters.myEquals(that.getTheater());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Performances that = (Performances) o;
        return performance_id == that.getPerformance_id() && name.equals(that.getName()) && duration.equals(that.getDuration())
                && start.equals(that.getStart()) && finish.equals(that.getFinish()) && description.equals(that.getDescription())
                && poster.equals(that.getPoster()) && theaters.equals(that.getTheater());
    }

    @Override
    public int hashCode() {
        return Objects.hash(performance_id, theaters, name, duration, start, finish, description, poster, perf_persons, schedule);
    }

    @Override
    public String toString() {
        return "Performances{" +
                "performance_id=" + performance_id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", start=" + start +
                ", finish=" + finish +
                ", description='" + description + '\'' +
                ", poster='" + poster + '\'' +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long performance_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    private Theaters theaters;

    @Column(name = "name")
    private String name;

    @Column(name = "duration")
    private LocalTime duration;

    @Column(name="start")
    private LocalDate start;

    @Column(name="finish")
    private LocalDate finish;

    @Column(name="description")
    private String description;

    @Column(name="poster")
    private String poster;

    @OneToMany(mappedBy = "performances", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Perf_persons> perf_persons;

    @OneToMany(mappedBy = "performances", cascade = CascadeType.ALL, orphanRemoval = true) // имя поля на стороне владельца
    private List<Schedule> schedule;

}
