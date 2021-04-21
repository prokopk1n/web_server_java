import DAO.Theaters.TheatersDAO;
import DAO.Theaters.TheatersDAOImpl;
import entity.Theaters.Theaters;

import java.sql.SQLException;
import java.util.Collection;

public class Main {
    public static void main(String[] args){
        System.out.println("Welcome to web-application!");
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        Collection<Theaters> theaters = null;
        try {
            theaters = theatersDAO.getAll();
        } catch (SQLException throwables) {
            System.out.println("DOESN`T WORK");
        }
        for (Theaters object : theaters) {
            System.out.println(object.getName());
        }
    }
}
