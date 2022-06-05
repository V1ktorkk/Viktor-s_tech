package ru.itmo.kotikilab5service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotikilab5service.entity.OwnersEntity;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<OwnersEntity, Integer> {

    OwnersEntity saveAndFlush(OwnersEntity entity);

    OwnersEntity save(OwnersEntity entity);

    OwnersEntity findById(int id);

    void delete(OwnersEntity entity);

    List<OwnersEntity> findAll();
}
