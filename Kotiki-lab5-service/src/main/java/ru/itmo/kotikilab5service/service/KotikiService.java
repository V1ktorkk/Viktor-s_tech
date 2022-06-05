package ru.itmo.kotikilab5service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.itmo.kotikilab5service.KafkaCommunication.Message;
import ru.itmo.kotikilab5service.entity.Colors;
import ru.itmo.kotikilab5service.entity.KotikiEntity;
import ru.itmo.kotikilab5service.entity.KotikiFriendsEntity;
import ru.itmo.kotikilab5service.repository.CatFriendsRepository;
import ru.itmo.kotikilab5service.repository.CatRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
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

    public boolean delete(int id) {
        catRepository.delete(findById(id));
        return false;
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

    public boolean addRelationship(int catId, int catFriendId) {
        catFriendsRepository.save(new KotikiFriendsEntity(catId, catFriendId));
        catFriendsRepository.save(new KotikiFriendsEntity(catFriendId, catId));
        return true;
    }

    public boolean removeRelationship(int catId, int catFriendId) {
        ArrayList<KotikiFriendsEntity> relationshipEntities = new ArrayList<>(catFriendsRepository.findAll());
        KotikiFriendsEntity serchedEntity = relationshipEntities.stream().filter(relation -> relation.getId() == catId && relation.getId() == catFriendId).limit(1)
                .findFirst().orElse(null);
        if (serchedEntity != null) {
            catFriendsRepository.delete(serchedEntity);
        }
        return true;
    }
    @KafkaListener(id = "findCat", topics = {"kotiki.get"}, containerFactory = "singleFactory")
    public void getById(String obj) {
        HashMap<String, String> parametrs = new HashMap<>();
        parametrs = Message.serializeFromJson(obj, parametrs.getClass());
        System.out.println(findById(Integer.parseInt(parametrs.get("id"))));
    }

    @KafkaListener(id = "addNewCat", topics = {"kotiki.post"}, containerFactory = "singleFactory")
    public void addNewCat(String obj) {
        HashMap<String, String> parametrs = new HashMap<>();
        parametrs = Message.serializeFromJson(obj, parametrs.getClass());
        System.out.println(saveNewCat(parametrs.get("name"),
                parametrs.get("breed"),
                Colors.valueOf(parametrs.get("color")),
                Integer.parseInt(parametrs.get("ownerId")),
                Date.valueOf(parametrs.get("birthdate"))));
    }
    @KafkaListener(id = "deleteCat", topics = {"kotiki.delete"}, containerFactory = "singleFactory")
    public void deleteCat(String obj) {
        HashMap<String, String> parametrs = new HashMap<>();
        parametrs = Message.serializeFromJson(obj, parametrs.getClass());
        System.out.println(delete(Integer.parseInt((parametrs.get("id").toString()))));
    }
    @KafkaListener(id = "findAll", topics = {"kotiki.findAll"}, containerFactory = "singleFactory")
    public void findAll() {
        System.out.println(findAllKotiki());
    }
    @KafkaListener(id = "addRelationship", topics = {"kotiki.addRelationship"}, containerFactory = "singleFactory")
    public void addRelation(String obj) {
        HashMap<String, String> parametrs = new HashMap<>();
        parametrs = Message.serializeFromJson(obj, parametrs.getClass());
        System.out.println(addRelationship(Integer.parseInt(parametrs.get("idKotika")),
                Integer.parseInt(parametrs.get("friendId"))));
    }

    @KafkaListener(id = "removeRelationship", topics = {"kotiki.removeRelationship"}, containerFactory = "singleFactory")
    public void removeRelation(String obj) {
        HashMap<String, String> parametrs = new HashMap<>();
        parametrs = Message.serializeFromJson(obj, parametrs.getClass());
        System.out.println(removeRelationship(Integer.parseInt(parametrs.get("idKotika")),
                Integer.parseInt(parametrs.get("friendId"))));
    }



}
