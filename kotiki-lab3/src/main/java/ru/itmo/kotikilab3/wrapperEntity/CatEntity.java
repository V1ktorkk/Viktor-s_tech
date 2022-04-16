package ru.itmo.kotikilab3.wrapperEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.itmo.kotikilab3.entity.Colors;

import java.awt.*;
import java.sql.Date;

@Data
@AllArgsConstructor
public class CatEntity {
    //private int id;
    private int ownerId;
    private String name;
    private String breed;
    private Colors color;
    private Date birthdate;
//    public CatEntity(int ownerId, String name, String breed, Color color, Date birthdate){
//        this.ownerId = ownerId;
//        this.name = name;
//        this.breed = breed;
//        this.color = color.toString();
//        this.birthdate = birthdate;
//
//    }

}
