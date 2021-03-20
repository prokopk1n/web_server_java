package DAO.Tickets;

import entity.Tickets.Schedule;

import java.sql.SQLException;
import java.util.List;

interface ScheduleDAO {
    Schedule getObjectById(Long objectId) throws SQLException;
    List<Schedule> getAll() throws SQLException;
}
