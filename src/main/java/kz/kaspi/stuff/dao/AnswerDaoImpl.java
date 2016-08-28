package kz.kaspi.stuff.dao;


import kz.kaspi.stuff.domain.Answer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by MM on 19.06.2016.
 */
@Repository
public class AnswerDaoImpl implements AnswerDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Answer> getList() {
        @SuppressWarnings("unchecked")
        List<Answer> list = sessionFactory.openSession().createCriteria(Answer.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return list;
    }

    @Override
    @Transactional
    public void add(Answer answer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(answer);
        session.getTransaction().commit();
        session.close();
    }
}
