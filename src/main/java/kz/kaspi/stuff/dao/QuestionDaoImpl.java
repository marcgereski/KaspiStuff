package kz.kaspi.stuff.dao;


import kz.kaspi.stuff.domain.Question;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class QuestionDaoImpl implements QuestionDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Question question) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(question);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    @Transactional
    public List<Question> getList() {
        @SuppressWarnings("unchecked")
        List<Question> list = (List<Question>) sessionFactory.openSession()
                .createCriteria(Question.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        return list;
    }

    @Override
    public Question get(Long id) {
        return (Question) sessionFactory.openSession().get(Question.class, id);
    }
}
