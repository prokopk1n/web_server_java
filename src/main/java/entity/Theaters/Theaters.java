package entity.Theaters;

import entity.Concert_halls.Concert_halls;
import entity.Performances.Performances;

import javax.persistence.*;
import java.util.List;

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
