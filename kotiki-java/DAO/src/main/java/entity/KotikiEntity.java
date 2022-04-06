package entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "kotiki", schema = "public", catalog = "kotiki_java")
public class KotikiEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "ownerId", nullable = false)
    private Integer ownerId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "breed")
    private String breed;
    @Basic
    @Column(name = "color")
    private String color;
    @Basic
    @Column(name = "birthdate")
    private Date birthdate;

    public List<KotikiEntity> getAllFriends() {
        return friends;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "kotikifriends",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "friendid")
    )
    private List<KotikiEntity> friends;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ownerId", referencedColumnName = "id", insertable = false, updatable = false)
    private OwnersEntity owner;

    public KotikiEntity(String name, String breed, Colors color, Date birthdate, Integer ownerId) {
        this.name = name;
        this.breed = breed;
        this.color = color.toString();
        this.birthdate = birthdate;
        this.ownerId = ownerId;
    }

    public KotikiEntity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color.toString();
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KotikiEntity kotiki = (KotikiEntity) o;
        return id == kotiki.id && Objects.equals(ownerId, kotiki.ownerId) && Objects.equals(name, kotiki.name) && Objects.equals(breed, kotiki.breed) && Objects.equals(color, kotiki.color) && Objects.equals(birthdate, kotiki.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerId, name, breed, color, birthdate);
    }
}
