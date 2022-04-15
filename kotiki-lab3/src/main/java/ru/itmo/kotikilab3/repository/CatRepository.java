package ru.itmo.kotikilab3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotikilab3.entity.Colors;
import ru.itmo.kotikilab3.entity.KotikiEntity;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<KotikiEntity, Integer> {

    KotikiEntity saveAndFlush(KotikiEntity entity);

    KotikiEntity save(KotikiEntity entity);

    KotikiEntity findById(int id);

    void delete(KotikiEntity entity);

    List<KotikiEntity> findAll();

    List<KotikiEntity> findAllByColor(String color);
}
