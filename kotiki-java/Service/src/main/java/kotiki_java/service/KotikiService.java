package kotiki_java.service;

import dao.KotikiDao;
import dao.KotikiFriendsDao;
import entity.KotikiEntity;
import entity.KotikiFriendsEntity;

import java.util.ArrayList;
import java.util.List;

public class KotikiService {
    private KotikiFriendsDao kotikiFriendsDao = new KotikiFriendsDao();
    private KotikiDao kotikiDao = new KotikiDao();

    public KotikiEntity getById(int id) {
        return kotikiDao.getById(id);
    }

    public void save(KotikiEntity cat) {
        kotikiDao.redeem(cat);
    }

    public void delete(int id) {
        kotikiDao.delete(getById(id));
    }

    public void redeem(KotikiEntity cat) {
        kotikiDao.update(cat);
    }

    public List<KotikiEntity> findAllKotiki() {
        return kotikiDao.getAll();
    }


    public List<KotikiEntity> GetAllFriends(int id) {
        return getById(id).getAllFriends();
    }

    public void addRelationship(int catId, int catFriendId) {
        kotikiFriendsDao.redeem(new KotikiFriendsEntity(catId, catFriendId));
        kotikiFriendsDao.redeem(new KotikiFriendsEntity(catFriendId, catId));
    }

    public void removeRelationship(int catId, int catFriendId) {
        ArrayList<KotikiFriendsEntity> relationshipEntities = new ArrayList<>(kotikiFriendsDao.getAll());
        KotikiFriendsEntity serchedEntity = relationshipEntities.stream()
                .filter(relation -> relation.getId() == catId && relation.getId() == catFriendId)
                .limit(1)
                .findFirst()
                .orElse(null);
        if (serchedEntity != null) {
            kotikiFriendsDao.delete(serchedEntity);
        }
    }
}

//    private static KotikiDao kotikiDao;
//
//    public KotikiService() {
//        kotikiDao = new KotikiDao();
//    }
//
//    public void delete(Integer id) {
//        kotikiDao.openCurrentSessionwithTransaction();
//        KotikiEntity kotik = kotikiDao.getById(id);
//        kotikiDao.delete(kotik);
//        kotikiDao.closeCurrentSessionwithTransaction();
//    }
//
//    public void update(KotikiEntity entity) {
//        kotikiDao.openCurrentSessionwithTransaction();
//        kotikiDao.update(entity);
//        kotikiDao.closeCurrentSessionwithTransaction();
//    }
//
//    public void redeem(KotikiEntity entity) {
//        kotikiDao.openCurrentSessionwithTransaction();
//        kotikiDao.redeem(entity);
//        kotikiDao.closeCurrentSessionwithTransaction();
//    }
//
//    public KotikiEntity getById(int id) {
//        kotikiDao.openCurrentSession();
//        KotikiEntity kotik = kotikiDao.getById(id);
//        kotikiDao.closeCurrentSession();
//        return kotik;
//    }
//
//    public List<KotikiEntity> getAllFriends(Integer id) {
//        kotikiDao.openCurrentSession();
//        List<KotikiEntity> allKotiki = kotikiDao.getById(id).getAllFriends();
//        kotikiDao.closeCurrentSession();
//        return allKotiki;
//    }
//
//    public List<KotikiEntity> getAll() {
//        kotikiDao.openCurrentSession();
//        List<KotikiEntity> allKoiki = kotikiDao.getAll();
//        kotikiDao.closeCurrentSession();
//        return allKoiki;
//    }
