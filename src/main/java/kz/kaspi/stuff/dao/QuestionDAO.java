package kz.kaspi.stuff.dao;


import kz.kaspi.stuff.domain.Question;

import java.util.List;

public interface QuestionDAO {
    List<Question> getList();
    Question get(Long id);
    void add(Question user);
}
