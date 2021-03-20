package DAO.Performances;

import entity.Performances.Performances;

import java.sql.SQLException;
import java.util.List;

interface PerformancesDAO {
    Performances getObjectById(Long objectId) throws SQLException;
    List<Performances> getAll() throws SQLException;
}
