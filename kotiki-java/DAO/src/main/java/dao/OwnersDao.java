package dao;

import HibernateSessionFactory.HibernateSessionFactory;
import entity.OwnersEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import dao.daointerface.Dao;

import java.util.List;

public class OwnersDao implements Dao<OwnersEntity> {

    @Override
    public void redeem(OwnersEntity entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(entity);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(OwnersEntity entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(entity);
        tx1.commit();
        session.close();
    }

    @Override
    public OwnersEntity getById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        OwnersEntity ownersEntity = session.get(OwnersEntity.class, id);
        tx1.commit();
        session.close();
        return ownersEntity;
    }

    @Override
    public void delete(OwnersEntity entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(entity);
        tx1.commit();
        session.close();
    }

    @Override
    public List getAll() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List<OwnersEntity> owners = (List<OwnersEntity>) session.createQuery("From OwnersEntity ").list();
        tx1.commit();
        session.close();
        return owners;
    }
}
