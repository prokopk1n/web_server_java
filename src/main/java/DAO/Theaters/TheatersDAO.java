package DAO.Theaters;

import DAO.BaseDAO.baseDAO;
import entity.Theaters.Theaters;

import java.sql.SQLException;
import java.util.Collection;


public interface TheatersDAO extends baseDAO<Theaters> {
    Collection<Theaters> getTheaterByName(String name) throws SQLException;
}
