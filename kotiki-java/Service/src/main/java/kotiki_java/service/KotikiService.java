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
        KotikiFriendsEntity serchedEntity = relationshipEntities.stream() .filter(relation -> relation.getId() == catId && relation.getId() == catFriendId) .limit(1)
                .findFirst() .orElse(null);
        if (serchedEntity != null) {
            kotikiFriendsDao.delete(serchedEntity);
        }
    }
}
