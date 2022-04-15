package ru.itmo.kotikilab3.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotikilab3.entity.Colors;
import ru.itmo.kotikilab3.entity.KotikiEntity;
import ru.itmo.kotikilab3.entity.OwnersEntity;
import ru.itmo.kotikilab3.repository.OwnerRepository;

import java.sql.Date;
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

    public void delete(int id) {
        ownerRepository.delete(findById(id));
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

}
