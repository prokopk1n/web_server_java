package entity.Theaters;

import com.sun.istack.Nullable;
import entity.Concert_halls.Concert_halls;
import entity.Performances.Performances;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="Theaters", schema = "public")
public class Theaters {
    public long getTheater_id() {
        return theater_id;
    }

    public void setTheater_id(long theater_id) {
        this.theater_id = theater_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String myName(Theaters theater)
    {
        return name+theater.getName();
    }

    public Theaters() {
    }

    public List<Performances> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Performances> perfomances) {
        this.performances = perfomances;
    }

    public List<Concert_halls> getConcert_halls() {
        return concert_halls;
    }

    public void setConcert_halls(List<Concert_halls> concert_halls) {
        this.concert_halls = concert_halls;
    }

    @Override
    public String toString() {
        return "entity.Theaters.entity.Theaters{" +
                "theater_id=" + theater_id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", photo='" + photo + '\'' +
                ", map='" + map + '\'' +
                '}';
    }

    public Theaters(String name, String email, String address, String phone, String photo, String map) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.photo = photo;
        this.map = map;
    }

    public boolean myEquals(Theaters theaters){
        System.out.println("I AM HERE!!!");
        return theaters!=null && this.getTheater_id()== theaters.getTheater_id() && this.getName().equals(theaters.getName())
                && this.getEmail().equals(theaters.getEmail()) && this.getAddress().equals(theaters.getAddress())
                && this.getPhone().equals(theaters.getPhone()) && this.getPhoto().equals(theaters.getPhoto())
                && this.getMap().equals(theaters.getMap());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Theaters theaters = (Theaters) o;
        return this.getTheater_id() == theaters.getTheater_id() && this.getName().equals(theaters.getName())
                && this.getEmail().equals(theaters.getEmail()) && this.getAddress().equals(theaters.getAddress())
                && this.getPhone().equals(theaters.getPhone()) && this.getPhoto().equals(theaters.getPhoto())
                && this.getMap().equals(theaters.getMap());
    }

    @Override
    public int hashCode() {
        return Objects.hash(theater_id, name, email, address, phone, photo, map, performances, concert_halls);
    }

    @Id
    @Column(name="theater_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long theater_id;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="address")
    private String address;

    @Column(name="phone")
    private String phone;

    @Column(name="photo")
    private String photo;

    @Column(name="map")
    private String map;

    @OneToMany(mappedBy = "theaters", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Performances> performances;

    @OneToMany(mappedBy = "theaters", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Concert_halls> concert_halls;

}
