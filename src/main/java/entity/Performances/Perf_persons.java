package entity.Performances;

import javax.persistence.*;

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

    public Perf_persons(long person_id, String role) {
        this.person_id = person_id;
        this.role = role;
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
