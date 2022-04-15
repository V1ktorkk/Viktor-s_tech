package ru.itmo.kotikilab3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotikilab3.entity.KotikiFriendsEntity;
import ru.itmo.kotikilab3.repository.CatFriendsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class KotikiFriendsService {
    @Autowired
    private CatFriendsRepository catFriendsRepository;


    public void delete(Integer id) {
        Optional<KotikiFriendsEntity> kotik = catFriendsRepository.findById(id);
        catFriendsRepository.delete(kotik.get());
    }

    public void saveAndFlush(KotikiFriendsEntity entity) {
        catFriendsRepository.saveAndFlush(entity);
    }

    public void save(KotikiFriendsEntity entity) {
        catFriendsRepository.save(entity);
    }

    public Optional<KotikiFriendsEntity> findById(Integer id) {
        Optional<KotikiFriendsEntity> kotik = catFriendsRepository.findById(id);
        return kotik;
    }

    public List<KotikiFriendsEntity> findAll() {
        List<KotikiFriendsEntity> allKoiki = catFriendsRepository.findAll();
        return allKoiki;
    }
}
