package DAO.Concert_halls;

import entity.Concert_halls.Seats;

import java.sql.SQLException;
import java.util.List;

interface SeatsDAO {
    Seats getObjectById(Long objectId) throws SQLException;
    List<Seats> getAll() throws SQLException;
}
