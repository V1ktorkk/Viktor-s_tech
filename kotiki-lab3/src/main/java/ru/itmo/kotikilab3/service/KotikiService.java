package ru.itmo.kotikilab3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itmo.kotikilab3.entity.Colors;
import ru.itmo.kotikilab3.entity.KotikiEntity;
import ru.itmo.kotikilab3.entity.KotikiFriendsEntity;
import ru.itmo.kotikilab3.repository.CatFriendsRepository;
import ru.itmo.kotikilab3.repository.CatRepository;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class KotikiService {

    @Autowired
    private CatRepository catRepository;
    @Autowired
    private CatFriendsRepository catFriendsRepository;

    public KotikiEntity findById(int id) {
        return catRepository.findById(id);
    }

    public void save(KotikiEntity cat) {
        catRepository.save(cat);
    }

    public void delete(int id) {
        catRepository.delete(findById(id));
    }

    public void saveAndFlush(KotikiEntity cat) {
        catRepository.saveAndFlush(cat);
    }

    public List<KotikiEntity> findAllByColor(String color) {
        return catRepository.findAllByColor(color);
    }

    public KotikiEntity saveNewCat(String name, String breed, Colors color, int ownerId, Date birthday) {
        KotikiEntity kotikiEntity = new KotikiEntity();
        kotikiEntity.setName(name);
        kotikiEntity.setBreed(breed);
        kotikiEntity.setColor(color.toString());
        kotikiEntity.setOwnerId(ownerId);
        kotikiEntity.setBirthdate(birthday);
        catRepository.save(kotikiEntity);
        return kotikiEntity;
    }

    public List<KotikiEntity> findAllKotiki() {
        return catRepository.findAll();
    }


    public List<KotikiEntity> GetAllFriends(int id) {
        return findById(id).getAllFriends();
    }

    public void addRelationship(int catId, int catFriendId) {
        catFriendsRepository.save(new KotikiFriendsEntity(catId, catFriendId));
        catFriendsRepository.save(new KotikiFriendsEntity(catFriendId, catId));
    }

    public void removeRelationship(int catId, int catFriendId) {
        ArrayList<KotikiFriendsEntity> relationshipEntities = new ArrayList<>(catFriendsRepository.findAll());
        KotikiFriendsEntity serchedEntity = relationshipEntities.stream().filter(relation -> relation.getId() == catId && relation.getId() == catFriendId).limit(1)
                .findFirst().orElse(null);
        if (serchedEntity != null) {
            catFriendsRepository.delete(serchedEntity);
        }
    }
}
