package Application.controller;

import DAO.Performances.*;
import DAO.Theaters.TheatersDAO;
import DAO.Theaters.TheatersDAOImpl;
import entity.Performances.People;
import entity.Performances.Perf_persons;
import entity.Performances.Performances;
import entity.Theaters.Theaters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

@Controller
public class MainController
{
    @RequestMapping(value = {"/", "/index", "/performances"}, method = RequestMethod.GET)
    public String index(Model model) throws SQLException {
        PerformancesDAO performancesDAO = new PerformancesDAOImpl();
        List<Performances> performances = null;
        performances = performancesDAO.getAll();
        model.addAttribute("performances", performances);
        return "index";
    }

    @RequestMapping(value = "/addPerformance", method = RequestMethod.GET)
    public String addPerformance(Model model) throws SQLException {
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        List<Theaters> theaters = null;
        theaters = theatersDAO.getAll();
        model.addAttribute("theaters", theaters);
        return "addPerformance";
    }

    @RequestMapping(value = "/addPerformance", method = RequestMethod.POST)
    public String createPerformance(WebRequest request) throws SQLException {
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        Collection<Theaters> theater = theatersDAO.getTheaterByName(request.getParameter("theater_name"));
        Theaters theaters = theater.iterator().next();
        Performances performances = new Performances(request.getParameter("name"), LocalTime.parse(request.getParameter("duration")), LocalDate.parse(request.getParameter("start")),
                LocalDate.parse(request.getParameter("finish")), request.getParameter("description"), null, theaters);
        PerformancesDAO performancesDAO = new PerformancesDAOImpl();
        performancesDAO.save(performances);
        return "index";
    }

    @RequestMapping(value =  "/performance", method = RequestMethod.GET)
    public String gerPerformance(@RequestParam Long id, Model model) throws SQLException {
        PerformancesDAO performancesDAO = new PerformancesDAOImpl();
        Performances performances = performancesDAO.getObjectById(id);
        model.addAttribute("performances", performances);
        System.out.println(performances.getSchedule().iterator().next().getDate());
        return "performance";
    }

    @RequestMapping(value =  "/changePerformance", method = RequestMethod.GET)
    public String changePerformance(@RequestParam Long perf_id, Model model) throws SQLException {
        PerformancesDAO performancesDAO = new PerformancesDAOImpl();
        Performances performances = performancesDAO.getObjectById(perf_id);
        model.addAttribute("performances", performances);
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        List<Theaters> theaters = null;
        theaters = theatersDAO.getAll();
        model.addAttribute("theaters", theaters);
        PeopleDAO peopleDAO = new PeopleDAOImpl();
        List<People> people = null;
        people = peopleDAO.getAll();
        model.addAttribute("people", people);
        return "changePerf";
    }

    @RequestMapping(value =  "/changePerformance", method = RequestMethod.POST)
    public String changePerf(@RequestParam Long perf_id, WebRequest request, Model model) throws SQLException {
        PerformancesDAO performancesDAO = new PerformancesDAOImpl();
        Performances performances = performancesDAO.getObjectById(perf_id);
        performances.setDuration(LocalTime.parse(request.getParameter("duration")));
        performances.setStart(LocalDate.parse(request.getParameter("start")));
        performances.setFinish(LocalDate.parse(request.getParameter("finish")));
        performances.setName(request.getParameter("name"));
        performances.setDescription(request.getParameter("description"));

        TheatersDAO theatersDAO = new TheatersDAOImpl();
        Theaters theaters = theatersDAO.getTheaterByName(request.getParameter("theater_name")).iterator().next();
        performances.setTheater(theaters);


        String actorName = request.getParameter("member");
        boolean alreadyHasThisMan = false;

        if (actorName.equals("Пусто"))
            alreadyHasThisMan = true;
        else
        {
            for (Perf_persons perf_persons: performances.getPerf_persons())
            {
                if (perf_persons.getPeople().getName().equals(actorName))
                {
                    alreadyHasThisMan = true;
                    break;
                }
            }
        }
        if (!alreadyHasThisMan)
        {
            PeopleDAO peopleDAO = new PeopleDAOImpl();
            Collection<People> people = peopleDAO.getAll();
            People thisMan = null;

            for (People man : people) {
                if (man.getName().equals(actorName))
                {
                    thisMan = man;
                    break;
                }
            }

            Perf_persons perf_persons = new Perf_persons(request.getParameter("role"), performances, thisMan);
            Perf_personsDAO perf_personsDAO = new Perf_personsDAOImpl();
            perf_personsDAO.save(perf_persons);
        }

        performancesDAO.update(performances);
        return "redirect:performance?id=" + perf_id;
    }








}
