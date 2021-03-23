package DAO.BaseDAO;

import java.util.List;

public interface baseDAO<T> {
    void save(T object);
    void update(T object);
    void delete(T object);
    T getObjectById(Long objectId);
    List<T> getAll();
}
