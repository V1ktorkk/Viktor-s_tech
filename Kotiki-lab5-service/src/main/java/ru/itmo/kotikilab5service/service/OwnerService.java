package ru.itmo.kotikilab5service.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.itmo.kotikilab5service.KafkaCommunication.Message;
import ru.itmo.kotikilab5service.entity.OwnersEntity;
import ru.itmo.kotikilab5service.repository.OwnerRepository;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;


    public OwnersEntity findById(int id) {
        return ownerRepository.findById(id);
    }

    public void save(OwnersEntity owner) {
        ownerRepository.save(owner);
    }

    public boolean delete(int id) {
        ownerRepository.delete(findById(id));
        return true;
    }

    public void saveAndFlush(OwnersEntity owner) {

        ownerRepository.saveAndFlush(owner);
    }

    public OwnersEntity saveNewOwner(String name, Date birthday) {
        OwnersEntity ownersEntity = new OwnersEntity();
        ownersEntity.setName(name);
        ownersEntity.setBirthdate(birthday);
        ownerRepository.save(ownersEntity);
        return ownersEntity;
    }


    public List<OwnersEntity> findAllOwners() {
        return ownerRepository.findAll();
    }
    @KafkaListener(id = "findOwner", topics = {"owner.get"}, containerFactory = "singleFactory")
    public void getById(String obj) {
        HashMap<String, String> parametrs = new HashMap<>();
        parametrs = Message.serializeFromJson(obj, parametrs.getClass());
        System.out.println(findById(Integer.parseInt(parametrs.get("id"))));
    }
    @KafkaListener(id = "addNewOwner", topics = {"owner.post"}, containerFactory = "singleFactory")
    public void addNewOwn(String obj) {
        HashMap<String, String> parametrs = new HashMap<>();
        parametrs = Message.serializeFromJson(obj, parametrs.getClass());
        System.out.println(saveNewOwner(parametrs.get("name"),
                Date.valueOf(parametrs.get("birthdate"))));
    }

    @KafkaListener(id = "deleteOwner", topics = {"owner.delete"}, containerFactory = "singleFactory")
    public void deleteOwner(String obj) {
        HashMap<String, String> parametrs = new HashMap<>();
        parametrs = Message.serializeFromJson(obj, parametrs.getClass());
        System.out.println(delete(Integer.parseInt((parametrs.get("id").toString()))));
    }

}
