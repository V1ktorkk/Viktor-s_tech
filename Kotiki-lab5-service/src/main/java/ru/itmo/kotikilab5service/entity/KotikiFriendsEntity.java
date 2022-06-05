package ru.itmo.kotikilab5service.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "kotikifriends", schema = "public", catalog = "kotiki_java")
public class KotikiFriendsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "idkotika")
    private Integer idkotika;
    @Basic
    @Column(name = "friendid")
    private Integer friendid;

    @ManyToMany(mappedBy = "friends")
    private List<KotikiEntity> kotiki;

    public KotikiFriendsEntity(Integer idkotika, Integer friendid) {
        this.idkotika = idkotika;
        this.friendid = friendid;
    }

    public KotikiFriendsEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdkotika() {
        return idkotika;
    }

    public void setIdkotika(Integer idkotika) {
        this.idkotika = idkotika;
    }

    public Integer getFriendid() {
        return friendid;
    }

    public void setFriendid(Integer friendid) {
        this.friendid = friendid;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KotikiFriendsEntity that = (KotikiFriendsEntity) o;
        return id == that.id && Objects.equals(idkotika, that.idkotika) && Objects.equals(friendid, that.friendid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idkotika, friendid);
    }
}
