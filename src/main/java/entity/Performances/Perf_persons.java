package entity.Performances;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="Perf_persons", schema = "public")
public class Perf_persons {

    public long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(long person_id) {
        this.person_id = person_id;
    }

    public Performances getPerformances() {
        return performances;
    }

    public void setPerformances(Performances performances) {
        this.performances = performances;
    }

    public People getPeople() {
        return people;
    }


    public void setPeople(People people) {
        this.people = people;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Perf_persons() {
    }

    public Perf_persons(String role, Performances performances, People people) {
        this.role = role;
        this.performances = performances;
        this.people = people;
    }

    public boolean myEquals(Perf_persons that){
        return that!=null && person_id==that.getPerson_id() && role.equals(that.getRole())
                && performances.myEquals(that.getPerformances()) && people.myEquals(that.getPeople());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Perf_persons that = (Perf_persons) o;
        return that!=null && person_id==that.getPerson_id() && role.equals(that.getRole())
                && performances.equals(that.getPerformances()) && people.equals(that.getPeople());
    }

    @Override
    public int hashCode() {
        return Objects.hash(person_id, performances, people, role);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private long person_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id")
    private Performances performances;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "people_id")
    private People people;

    @Column(name="role")
    private String role;
}
