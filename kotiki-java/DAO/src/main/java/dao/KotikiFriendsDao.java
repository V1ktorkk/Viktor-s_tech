package dao;

import HibernateSessionFactory.HibernateSessionFactory;
import dao.daointerface.Dao;
import entity.KotikiEntity;
import entity.KotikiFriendsEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class KotikiFriendsDao implements Dao<KotikiFriendsEntity> {

    @Override
    public void redeem(KotikiFriendsEntity entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(entity);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(KotikiFriendsEntity entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(entity);
        tx1.commit();
        session.close();
    }

    @Override
    public KotikiFriendsEntity getById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        KotikiFriendsEntity catEntity = session.get(KotikiFriendsEntity.class, id);
        tx1.commit();
        session.close();
        return catEntity;
    }

    @Override
    public void delete(KotikiFriendsEntity entity) {
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
        List<KotikiEntity> cats = (List<KotikiEntity>) session.createQuery("From entity.KotikiFriendsEntity ").list();
        tx1.commit();
        session.close();
        return cats;
    }
}
