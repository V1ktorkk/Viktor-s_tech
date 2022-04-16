package ru.itmo.kotikilab3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotikilab3.entity.KotikiFriendsEntity;

import java.util.List;

@Repository
public interface CatFriendsRepository extends JpaRepository<KotikiFriendsEntity, Integer> {

    KotikiFriendsEntity saveAndFlush(KotikiFriendsEntity entity);

    KotikiFriendsEntity save(KotikiFriendsEntity entity);

    KotikiFriendsEntity findById(int id);

    @Override
    void delete(KotikiFriendsEntity entity);

    @Override
    List<KotikiFriendsEntity> findAll();

}
