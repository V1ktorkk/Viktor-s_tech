package ru.itmo.kotikilab3.transformation;

import ru.itmo.kotikilab3.entity.Colors;
import ru.itmo.kotikilab3.entity.KotikiEntity;
import ru.itmo.kotikilab3.wrapperEntity.CatEntity;

public class TransformCat {
    public static CatEntity transformEntityToWrappedEntity(KotikiEntity kotikiEntity) {
        CatEntity catEntity = new CatEntity(kotikiEntity.getOwnerId(),
                kotikiEntity.getName(), kotikiEntity.getBreed(),
                Colors.valueOf(kotikiEntity.getColor()), kotikiEntity.getBirthdate());
        return catEntity;
    }

    public static KotikiEntity transformWrapperdEntityToEntity(CatEntity catEntity) {
        KotikiEntity kotikiEntity = new KotikiEntity(catEntity.getName(), catEntity.getBreed(),
                catEntity.getColor(), catEntity.getBirthdate(), catEntity.getOwnerId());
        return kotikiEntity;

    }
}
