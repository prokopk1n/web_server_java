package DAO.BaseDAO;

import java.sql.SQLException;
import java.util.List;

public interface baseDAO<T> {
    void save(T object) throws SQLException;
    void update(T object) throws SQLException;
    void delete(T object) throws SQLException;
    T getObjectById(Long objectId) throws SQLException;
    List<T> getAll() throws SQLException;
}
