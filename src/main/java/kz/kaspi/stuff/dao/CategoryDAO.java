package kz.kaspi.stuff.dao;


import kz.kaspi.stuff.domain.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> getList();
    Category get(Long id);
}
