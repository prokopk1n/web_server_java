package DAO.Performances;

import entity.Performances.People;

import java.sql.SQLException;
import java.util.List;

interface PeopleDAO {
    People getObjectById(Long objectId) throws SQLException;
    List<People> getAll() throws SQLException;
}
