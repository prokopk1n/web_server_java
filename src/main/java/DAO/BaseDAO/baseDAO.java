package DAO.BaseDAO;

import java.sql.SQLException;

interface baseDAO<T> {
    void save(T object) throws SQLException;
    void update(T object) throws SQLException;
    void delete(T object) throws SQLException;
}
