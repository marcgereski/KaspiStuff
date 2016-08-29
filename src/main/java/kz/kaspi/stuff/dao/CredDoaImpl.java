package kz.kaspi.stuff.dao;


import kz.kaspi.stuff.domain.Credential;
import kz.kaspi.stuff.domain.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class CredDoaImpl implements CredDAO {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public String getPass(Long userId) {
        Credential c = (Credential) sessionFactory.openSession()
                .get(Credential.class, userId);
        return c.getPass();
    }

    @Override
    public Credential getUserByToken(Long userId) {
        return (Credential) sessionFactory.openSession()
                .get(Credential.class, userId);
    }

    @Override
    public User getUserByToken(String token) {
        String hql = "FROM kz.kaspi.stuff.domain.Credential c WHERE c.token = :token";
        Query query = sessionFactory.openSession().createQuery(hql);
        query.setString("token", token);
        Credential cred = (Credential) query.uniqueResult();
        return cred.getUser();
    }

    @Override
    public void add(Credential credential) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(credential);
        session.getTransaction().commit();
        session.close();
    }
}
