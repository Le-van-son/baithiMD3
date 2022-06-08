package service;

import java.sql.SQLException;
import java.util.List;

public interface ProductService<T> {
    void add(T t) ;

    T findById(int id);

    List<T> findAll();

    boolean delete(int id) ;

    boolean update(T t) ;
}
