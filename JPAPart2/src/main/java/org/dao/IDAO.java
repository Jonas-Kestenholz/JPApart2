package org.dao;

import java.util.List;

public interface IDAO <T,ID>{
    T find(ID id);
    List<T> findAll();
    T save(T t);
    void delete(ID id);
    T update(T t);



}
