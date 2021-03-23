package entity.Theaters;

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

    public Theaters() {
    }

    public List<Performances> getPerfomances() {
        return perfomances;
    }

    public void setPerfomances(List<Performances> perfomances) {
        this.perfomances = perfomances;
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

    public Theaters(long theater_id, String name, String email, String address, String phone, String photo, String map) {
        this.theater_id = theater_id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.photo = photo;
        this.map = map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Theaters theaters = (Theaters) o;
        return theater_id == theaters.theater_id && Objects.equals(name, theaters.name) && Objects.equals(email, theaters.email) && Objects.equals(address, theaters.address) && Objects.equals(phone, theaters.phone) && Objects.equals(photo, theaters.photo) && Objects.equals(map, theaters.map) && Objects.equals(perfomances, theaters.perfomances) && Objects.equals(concert_halls, theaters.concert_halls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(theater_id, name, email, address, phone, photo, map, perfomances, concert_halls);
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
    private List<Performances> perfomances;

    @OneToMany(mappedBy = "theaters", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Concert_halls> concert_halls;

}
