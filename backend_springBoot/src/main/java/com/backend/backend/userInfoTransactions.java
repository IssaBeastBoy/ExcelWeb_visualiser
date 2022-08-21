package com.backend.backend;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class userInfoTransactions {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<userInformationEntity> findall() {
        List<userInformationEntity> Users = null;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Users = session.createQuery("from users").getResultList();
            transaction.commit();
        } catch (Exception e) {
            Users = null;
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.clear();
        }
        return Users;
    }

    public List<userInformationEntity> findUser(String primaryKey) {
        List<userInformationEntity> User = null;
        Session session = null;
        Transaction transaction = null;
        String sqlCommand = "from users where loginName=" + primaryKey;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            User = session.createQuery(sqlCommand).getResultList();
            transaction.commit();
        } catch (Exception e) {
            User = null;
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.clear();
        }
        return User;
    }

    public Boolean addUser(String password, String Name, String Surname, String email,
            String phoneNum, String LASnumber) {
        Boolean User = true;
        Session session = null;
        Transaction transaction = null;
        userInformationEntity userAdd = new userInformationEntity(password,Name,Surname,email,phoneNum,LASnumber);
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(userAdd);
            transaction.commit();
        } catch (Exception e) {
            User = false;
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.clear();
        }
        return User;
    }

    public Boolean deleteUser(userInformationEntity user) {
        Boolean User = true;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            User = false;
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.clear();
        }
        return User;
    }

    public Boolean updateUser(userInformationEntity user) {
        Boolean User = true;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            User = false;
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.clear();
        }
        return User;
    }
}
