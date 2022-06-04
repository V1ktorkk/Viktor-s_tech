package ru.itmo.kotikilab5controllers.wrapperEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.itmo.kotikilab5controllers.entity.Colors;

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

}
