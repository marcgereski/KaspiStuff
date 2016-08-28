package kz.kaspi.stuff.dao;


import kz.kaspi.stuff.domain.Answer;

import java.util.List;

public interface AnswerDAO {
    List<Answer> getList();
    void add(Answer answer);
}
