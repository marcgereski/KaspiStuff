package kz.kaspi.stuff.dao;


import kz.kaspi.stuff.domain.Category;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDAO {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Category> getList() {
        @SuppressWarnings("unchecked")
        List<Category> list = (List<Category>) sessionFactory.openSession()/*getCurrentSession()*/
                .createCriteria(Category.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        return list;
    }

    @Override
    public Category get(Long id) {
        return (Category) sessionFactory.openSession().get(Category.class, id);
    }
}
