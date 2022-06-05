package ru.itmo.kotikilab5service.wrapperEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class OwnerEntity {
    private int id;
    private String name;
    private Date birthdate;
}
