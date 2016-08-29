package kz.kaspi.stuff.dao;


import kz.kaspi.stuff.domain.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<User> getList() {
        @SuppressWarnings("unchecked")
        List<User> listUser = (List<User>) sessionFactory.getCurrentSession()
                .createCriteria(User.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        return listUser;
    }

    @Override
    @Transactional
    public void add(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public User get(Long id) {
        return (User) sessionFactory.openSession().get(User.class, id);
    }

    @Override
    public User get(String username) {
        String hql = "FROM kz.kaspi.stuff.domain.User u WHERE u.username = :name";
        Query query = sessionFactory.openSession().createQuery(hql);
        query.setString("name", username);
        return (User) query.uniqueResult();
    }

    @Override
    @Transactional
    public void update(User user) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.merge(user);
        session.getTransaction().commit();
        session.close();
    }


}
