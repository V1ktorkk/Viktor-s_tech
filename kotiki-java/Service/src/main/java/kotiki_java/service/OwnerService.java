package kotiki_java.service;

import dao.OwnersDao;
import entity.KotikiEntity;
import entity.OwnersEntity;

import java.util.List;

public class OwnerService {

    private  OwnersDao ownersDao = new OwnersDao();


    public OwnersEntity getById(int id) {
        return ownersDao.getById(id);
    }

    public void save(OwnersEntity owner) {
        ownersDao.redeem(owner);
    }

    public void delete(int id) {
        ownersDao.delete(getById(id));
    }

    public void redeem(OwnersEntity owner) {

        ownersDao.update(owner);
    }

    public List<KotikiEntity> findAllOwners() {
        return ownersDao.getAll();
    }

}
