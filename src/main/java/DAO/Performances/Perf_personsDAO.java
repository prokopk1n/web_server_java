package DAO.Performances;

import entity.Performances.Perf_persons;

import java.sql.SQLException;
import java.util.List;

interface Perf_personsDAO {
    Perf_persons getObjectById(Long objectId) throws SQLException;
    List<Perf_persons> getAll() throws SQLException;
}
