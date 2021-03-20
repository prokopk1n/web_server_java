package DAO.Concert_halls;

import entity.Concert_halls.Concert_halls;

import java.sql.SQLException;
import java.util.List;

interface Concert_hallsDAO {
    Concert_halls getObjectById(Long objectId) throws SQLException;
    List<Concert_halls> getAll() throws SQLException;
}
