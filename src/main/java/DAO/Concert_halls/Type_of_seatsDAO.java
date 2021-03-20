package DAO.Concert_halls;

import entity.Concert_halls.Type_of_seats;

import java.sql.SQLException;
import java.util.List;

interface Type_of_seatsDAO {
    Type_of_seats getObjectById(Long objectId) throws SQLException;
    List<Type_of_seats> getAll() throws SQLException;
}
