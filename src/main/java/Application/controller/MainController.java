package Application.controller;

import DAO.Concert_halls.SeatsDAO;
import DAO.Concert_halls.SeatsDAOImpl;
import DAO.Concert_halls.Type_of_seatsDAO;
import DAO.Concert_halls.Type_of_seatsDAOImpl;
import DAO.Performances.*;
import DAO.Theaters.TheatersDAO;
import DAO.Theaters.TheatersDAOImpl;
import DAO.Tickets.ScheduleDAO;
import DAO.Tickets.ScheduleDAOImpl;
import DAO.Tickets.TicketsDAO;
import DAO.Tickets.TicketsDAOImpl;
import entity.Concert_halls.Concert_halls;
import entity.Concert_halls.Type_of_seats;
import entity.Performances.People;
import entity.Performances.Perf_persons;
import entity.Performances.Performances;
import entity.Theaters.Theaters;
import entity.Tickets.Schedule;
import entity.Tickets.Tickets;
import org.apache.catalina.webresources.TomcatJarInputStream;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
        return "redirect:index";
    }

    @RequestMapping(value = "/deletePerformance", method = RequestMethod.POST)
    public String createPerformance(@RequestParam Long perf_id) throws SQLException {
        PerformancesDAO performancesDAO = new PerformancesDAOImpl();
        Performances performances = performancesDAO.getObjectById(perf_id);
        performancesDAO.delete(performances);
        System.out.println("HERE I AM");
        return "redirect:performances";
    }

    @RequestMapping(value =  "/performance", method = RequestMethod.GET)
    public String getPerformance(@RequestParam Long id, Model model) throws SQLException {
        PerformancesDAO performancesDAO = new PerformancesDAOImpl();
        Performances performances = performancesDAO.getObjectById(id);
        model.addAttribute("performances", performances);
        //System.out.println(performances.toString());
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

    @RequestMapping(value =  "/deleteMember", method = RequestMethod.POST)
    public String deleteMember(@RequestParam Long member_id) throws SQLException {
        Perf_personsDAO perf_personsDAO = new Perf_personsDAOImpl();
        Perf_persons perf_persons = perf_personsDAO.getObjectById(member_id);
        long id = perf_persons.getPerformances().getPerformance_id();
        perf_personsDAO.delete(perf_persons);
        return "redirect:changePerformance?perf_id=" + id;
    }

    @RequestMapping(value = {"theaters"}, method = RequestMethod.GET)
    public String theaters(Model model) throws SQLException {
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        Collection<Theaters> theaters = theatersDAO.getAll();
        model.addAttribute("theaters", theaters);
        return "theaters";
    }

    @RequestMapping(value = {"addTheater"}, method = RequestMethod.GET)
    public String addTheater(Model model) throws SQLException {
        return "addingTheater";
    }

    @RequestMapping(value = "addTheater", method = RequestMethod.POST)
    public String createTheater(WebRequest webRequest) throws SQLException
    {
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        Theaters theaters = new Theaters(webRequest.getParameter("name"), webRequest.getParameter("site"), webRequest.getParameter("address"),
                webRequest.getParameter("phone"), null, null);
        theatersDAO.save(theaters);
        return "redirect:theaters";
    }

    @RequestMapping(value="/theater", method = RequestMethod.GET)
    public String getTheater(@RequestParam Long theater_id, Model model) throws SQLException
    {
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        Theaters theaters = theatersDAO.getObjectById(theater_id);
        model.addAttribute("theater", theaters);
        return "theater";
    }

    @RequestMapping(value =  "/deleteTheater", method = RequestMethod.POST)
    public String deleteTheater(@RequestParam Long theater_id) throws SQLException {
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        theatersDAO.delete(theatersDAO.getObjectById(theater_id));
        return "redirect:theaters";
    }

    @RequestMapping(value = "/changeTheater", method = RequestMethod.GET)
    public String changeTheater(@RequestParam Long theater_id, Model model) throws SQLException
    {
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        Theaters theaters = theatersDAO.getObjectById(theater_id);
        model.addAttribute("theater", theaters);
        return "changeTheater";
    }

    @RequestMapping(value = "/changeTheater", method = RequestMethod.POST)
    public String updateTheater(@RequestParam Long theater_id, WebRequest webRequest, Model model) throws SQLException
    {
        TheatersDAO theatersDAO = new TheatersDAOImpl();
        Theaters theaters = theatersDAO.getObjectById(theater_id);
        theaters.setName(webRequest.getParameter("name"));
        theaters.setPhone(webRequest.getParameter("phone"));
        theaters.setEmail(webRequest.getParameter("site"));
        theaters.setAddress(webRequest.getParameter("address"));
        theatersDAO.update(theaters);
        return "redirect:/theater?theater_id="+theater_id;
    }

    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    public String getEvent(@RequestParam Long id, Model model) throws SQLException
    {
        ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
        Schedule schedule = scheduleDAO.getObjectById(id);
        model.addAttribute("event", schedule);
        return "event";
    }

    @RequestMapping(value = "/tickets", method = RequestMethod.GET)
    public String getTickets(@RequestParam Long event_id, Model model) throws SQLException
    {
        ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
        Schedule schedule = scheduleDAO.getObjectById(event_id);
        model.addAttribute("event", schedule);

        Type_of_seatsDAO type_of_seatsDAO = new Type_of_seatsDAOImpl();
        Collection<Type_of_seats> type_of_seats = type_of_seatsDAO.getAll();
        int max = 0;
        for (Type_of_seats type: type_of_seats)
        {
            if (type.getType_id()>max)
                max=(int)type.getType_id();
        }

        List<Tickets> types[] = new ArrayList[max+1];
        for (int i=0;i<=max;i++)
            types[i] = new ArrayList<Tickets>();

        for (Tickets ticket: schedule.getTickets())
        {
            types[(int)ticket.getSeats().getType_of_seats().getType_id()].add(ticket);
        }
        model.addAttribute("types", types);
        model.addAttribute("type_of_seats", type_of_seats);
        return "tickets";
    }

    @RequestMapping(value="/buyTicket", method = RequestMethod.POST)
    public String buyTicket(@RequestParam Long ticket_id) throws SQLException
    {
        TicketsDAO ticketsDAO = new TicketsDAOImpl();
        Tickets tickets= ticketsDAO.getObjectById(ticket_id);
        tickets.setIn_stock(false);
        Long event_id = tickets.getSchedule().getEvent_id();
        ticketsDAO.update(tickets);
        return "redirect:/tickets?event_id=" + event_id;
    }

    @RequestMapping(value = "changeTickets", method = RequestMethod.GET)
    public String changeTickets(@RequestParam Long event_id, Model model) throws SQLException
    {
        ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
        Schedule schedule = scheduleDAO.getObjectById(event_id);

        model.addAttribute("seats", schedule.getConcert_halls().getSeats());
        model.addAttribute("event", schedule);
        return "changeTicket";
    }

    @RequestMapping(value = "changeTicket", method = RequestMethod.POST)
    public String changeTicket(@RequestParam Map<String, String> parametrs, WebRequest webRequest) throws SQLException
    {
        Long event_id = Long.parseLong(parametrs.get("event_id"));
        long seat_id = Long.parseLong(parametrs.get("seat_id"));
        ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
        Schedule schedule = scheduleDAO.getObjectById(event_id);
        boolean ticket_exist = false;
        System.out.println("HELLO");
        TicketsDAO ticketsDAO = new TicketsDAOImpl();
        String cost = webRequest.getParameter("cost");
        if (cost==null) {
            System.out.println("ABC");
            cost = "1000";
        }
        String in_stock = webRequest.getParameter("in_stock");
        if (in_stock==null)
            in_stock = "Нет";
        System.out.println(in_stock + cost);

        for(Tickets ticket: schedule.getTickets())
        {
            if (ticket.getSeats().getSeat_id()==seat_id)
            {
                ticket_exist = true;
                ticket.setCost(Float.parseFloat(cost));
                ticket.setIn_stock(in_stock.equals("Да"));
                ticketsDAO.update(ticket);
            }
        }

        if (!ticket_exist)
        {
            SeatsDAO seatsDAO = new SeatsDAOImpl();
            if (webRequest.getParameter("cost")!=null && webRequest.getParameter("in_cost")!=null) {
                Tickets tickets = new Tickets(schedule, seatsDAO.getObjectById(seat_id), Float.parseFloat(cost),
                        in_stock.equals("Да"));
                ticketsDAO.save(tickets);
            }
        }

        return "redirect:changeTickets?event_id="+event_id;
    }

    @RequestMapping(value = "addEvent", method = RequestMethod.GET)
    public String addEvent(@RequestParam Long perf_id, Model model) throws SQLException
    {
        PerformancesDAO performancesDAO = new PerformancesDAOImpl();
        Performances performances = performancesDAO.getObjectById(perf_id);

        model.addAttribute("performance", performances);
        model.addAttribute("theater", performances.getTheater());
        return "addEvent";
    }

    @RequestMapping(value = "addEvent", method = RequestMethod.POST)
    public String newEvent(@RequestParam Long perf_id, WebRequest webRequest) throws SQLException
    {
        PerformancesDAO performancesDAO = new PerformancesDAOImpl();
        Performances performances = performancesDAO.getObjectById(perf_id);

        LocalDate localDate = LocalDate.parse(webRequest.getParameter("date"));

        if (localDate.compareTo(performances.getStart())>=0 && localDate.compareTo(performances.getFinish())<=0)
            for (Concert_halls concert_halls: performances.getTheater().getConcert_halls())
            {
                if (concert_halls.getName().equals(webRequest.getParameter("hall_name")))
                {
                    ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
                    Schedule schedule = new Schedule(concert_halls, performances, LocalDateTime.of(localDate,
                            LocalTime.parse(webRequest.getParameter("time"))));
                    scheduleDAO.save(schedule);
                }
            }

        return "redirect:/performance?id="+perf_id;
    }

    @RequestMapping(value = "changeEvent", method = RequestMethod.GET)
    public String changingEvent(@RequestParam Long event_id, Model model) throws SQLException
    {
        ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
        Schedule schedule = scheduleDAO.getObjectById(event_id);
        model.addAttribute("event", schedule);
        return "changeEvent";
    }

    @RequestMapping(value = "changeEvent", method = RequestMethod.POST)
    public String changeEvent(@RequestParam Long event_id, WebRequest webRequest) throws SQLException
    {
        ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
        Schedule schedule = scheduleDAO.getObjectById(event_id);

        Performances performances = schedule.getPerformances();
        LocalDate localDate = LocalDate.parse(webRequest.getParameter("date"));

        if (localDate.compareTo(performances.getStart())>=0 && localDate.compareTo(performances.getFinish())<=0)
            for (Concert_halls concert_halls: performances.getTheater().getConcert_halls())
            {
                if (concert_halls.getName().equals(webRequest.getParameter("hall_name")))
                {
                    if (!concert_halls.getName().equals(schedule.getConcert_halls().getName()))
                    {
                        Schedule newSchedule = new Schedule(concert_halls, performances, LocalDateTime.of(localDate,
                                LocalTime.parse(webRequest.getParameter("time"))));
                        scheduleDAO.delete(schedule);
                        scheduleDAO.save(newSchedule);
                    }
                    else
                    {
                        schedule.setDate(LocalDateTime.of(localDate,
                                LocalTime.parse(webRequest.getParameter("time"))));
                        scheduleDAO.save(schedule);
                    }
                }
            }

        return "redirect:/event?event_id="+event_id;
    }

    @RequestMapping(value =  "/deleteEvent", method = RequestMethod.POST)
    public String deleteEvent(@RequestParam Long event_id) throws SQLException {
        ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
        Schedule schedule= scheduleDAO.getObjectById(event_id);
        Long perf_id = schedule.getPerformances().getPerformance_id();
        scheduleDAO.delete(scheduleDAO.getObjectById(event_id));
        return "redirect:performance?id=" + perf_id;
    }

    @RequestMapping(value =  "/contacts", method = RequestMethod.GET)
    public String getContacts() throws SQLException {
        return "contacts";
    }

    @RequestMapping(value = "addPerson", method = RequestMethod.GET)
    public String addPeople() throws SQLException
    {
        return "addPerson";
    }

    @RequestMapping(value = "addPerson", method = RequestMethod.POST)
    public String newPerson(WebRequest webRequest) throws SQLException
    {
        PeopleDAO peopleDAO = new PeopleDAOImpl();
        People people = new People(webRequest.getParameter("name"), webRequest.getParameter("description"), null);
        peopleDAO.save(people);
        return "redirect:/index";
    }

    @RequestMapping(value = "members", method = RequestMethod.GET)
    public String getMembers(Model model) throws SQLException
    {
        PeopleDAO peopleDAO = new PeopleDAOImpl();
        model.addAttribute("members", peopleDAO.getAll());
        return "actors";
    }

    @RequestMapping(value = "deleteMan", method = RequestMethod.POST)
    public String deleteMan(@RequestParam Long member_id) throws SQLException
    {
        PeopleDAO peopleDAO = new PeopleDAOImpl();
        peopleDAO.delete(peopleDAO.getObjectById(member_id));
        return "redirect:/members";
    }

    @RequestMapping(value = "changeMan", method = RequestMethod.GET)
    public String changeMan(@RequestParam Long member_id, Model model) throws SQLException
    {
        PeopleDAO peopleDAO = new PeopleDAOImpl();
        model.addAttribute("member", peopleDAO.getObjectById(member_id));
        return "changeMember";
    }

    @RequestMapping(value = "changeMan", method = RequestMethod.POST)
    public String changeManPost(@RequestParam Long member_id, WebRequest webRequest) throws SQLException
    {
        PeopleDAO peopleDAO = new PeopleDAOImpl();
        People people = peopleDAO.getObjectById(member_id);
        people.setDescription(webRequest.getParameter("description"));
        people.setName(webRequest.getParameter("name"));
        peopleDAO.update(people);
        return "redirect:members";
    }


}
