package DAO.Tickets;

import entity.Tickets.Tickets;

import java.sql.SQLException;
import java.util.List;

interface TicketsDAO {
    Tickets getObjectById(Long objectId) throws SQLException;
    List<Tickets> getAll() throws SQLException;
}

