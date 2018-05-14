package db;

import models.Golfer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Queue;

public class DBGolfer {

    private static Session session;
    private static Transaction transaction;

    public static void save(Golfer golfer){
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(golfer);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<Golfer> getGolfers(){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Golfer> results = null;
        try {
            String hql = "from Golfer";
            results = session.createQuery(hql).list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

//    public static void update(Golfer golfer){
//        session = HibernateUtil.getSessionFactory().openSession();
//        try {
//            String hql = "update Golfer set age = :age where id = :id";
//            session.update("id");
//        } catch (HibernateException e) {
//            transaction.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//    }
}
