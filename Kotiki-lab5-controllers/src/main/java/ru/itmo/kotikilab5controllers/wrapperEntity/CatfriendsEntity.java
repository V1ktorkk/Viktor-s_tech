package ru.itmo.kotikilab5controllers.wrapperEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CatfriendsEntity {
    private int id;
    private Integer idkotika;
    private Integer friendid;
}
