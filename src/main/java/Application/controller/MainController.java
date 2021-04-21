package Application.controller;

import DAO.Performances.PerformancesDAO;
import DAO.Performances.PerformancesDAOImpl;
import DAO.Theaters.TheatersDAO;
import DAO.Theaters.TheatersDAOImpl;
import entity.Performances.Performances;
import entity.Theaters.Theaters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Controller
public class MainController
{
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) throws SQLException {
        String message = "Hello Sonya!";
        model.addAttribute("message", message);
        PerformancesDAO performancesDAO = new PerformancesDAOImpl();
        List<Performances> performances = null;
        performances = performancesDAO.getAll();
        model.addAttribute("performances", performances);
        return "index";
    }
}
