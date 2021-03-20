package DAO.Theaters;

import entity.Theaters.Theaters;

import java.sql.SQLException;
import java.util.List;


interface TheatersDAO{
    Theaters getObjectById(Long objectId) throws SQLException;
    List<Theaters> getAll() throws SQLException;
}
