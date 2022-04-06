package kotiki_java.service;

import dao.KotikiFriendsDao;
import entity.KotikiFriendsEntity;


import java.util.List;

public class KotikiFriendsService {
    private KotikiFriendsDao kotikiFriendsDao = new KotikiFriendsDao();

    public void delete(Integer id) {
        KotikiFriendsEntity kotik = kotikiFriendsDao.getById(id);
        kotikiFriendsDao.delete(kotik);
    }

    public void update(KotikiFriendsEntity entity) {
        kotikiFriendsDao.update(entity);
    }

    public void redeem(KotikiFriendsEntity entity) {
        kotikiFriendsDao.redeem(entity);
    }

    public KotikiFriendsEntity getById(Integer id) {
        KotikiFriendsEntity kotik = kotikiFriendsDao.getById(id);
        return kotik;
    }

    public List<KotikiFriendsEntity> getAll() {
        List<KotikiFriendsEntity> allKoiki = kotikiFriendsDao.getAll();
        return allKoiki;
    }
}
