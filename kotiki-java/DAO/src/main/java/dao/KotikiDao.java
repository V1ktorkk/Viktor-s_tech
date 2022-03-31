package dao;

import HibernateSessionFactory.HibernateSessionFactory;
import dao.daointerface.Dao;
import entity.KotikiEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class KotikiDao implements Dao<KotikiEntity> {
    @Override
    public void redeem(KotikiEntity entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(entity);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(KotikiEntity entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(entity);
        tx1.commit();
        session.close();
    }

    @Override
    public KotikiEntity getById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        KotikiEntity catEntity = session.get(KotikiEntity.class, id);
        tx1.commit();
        session.close();
        return catEntity;
    }

    @Override
    public void delete(KotikiEntity entity) {
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
        List<KotikiEntity> cats = (List<KotikiEntity>) session.createQuery("From KotikiEntity ").list();
        tx1.commit();
        session.close();
        return cats;
    }
}