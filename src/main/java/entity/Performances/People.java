package entity.Performances;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="People", schema="public")
public class People {
    public long getPeople_id() {
        return people_id;
    }

    public void setPeople_id(long people_id) {
        this.people_id = people_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<Perf_persons> getPerf_persons() {
        return perf_persons;
    }

    public void setPerf_persons(List<Perf_persons> perf_persons) {
        this.perf_persons = perf_persons;
    }

    public People() {
    }

    public People(String name, String description, String photo) {
        this.name = name;
        this.description = description;
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        People that = (People) o;
        return that!=null && people_id==that.getPeople_id() && name.equals(that.getName())
                && description.equals(that.getDescription()) && photo.equals(that.getPhoto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(people_id, name, description, photo, perf_persons);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "people_id")
    private long people_id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="photo")
    private String photo;

    @OneToMany(mappedBy = "people", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Perf_persons> perf_persons;
}
