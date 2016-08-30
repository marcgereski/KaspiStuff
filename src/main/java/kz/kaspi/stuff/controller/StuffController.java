package kz.kaspi.stuff.controller;

import kz.kaspi.stuff.dao.*;
import kz.kaspi.stuff.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping("/")
public class StuffController {
    public static final String REDIRECT_LOGIN = "redirect: login";
    public static final String HEADING = "where's my stuff???";
    public static final String NOT_ENOUGH_RIGHTS = "Not enough rights";
    public static final String REDIRECT_TO_MAIN = "redirect:/";

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private CredDAO credDAO;

    @Autowired
    private QuestionDAO questionDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private AnswerDAO answerDAO;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return REDIRECT_TO_MAIN;
    }

    @RequestMapping(value = "profile", method = RequestMethod.GET)
    public String showProfile(ModelMap model,
                              @RequestParam(value = "id", required = false) Long id) throws SQLException {

        String name = getAuthenticatedUsername();
        if (name == null) return REDIRECT_LOGIN;

        User u;
        if (id == null) {
            u = userDAO.get(name);
        } else u = userDAO.get(id);

        if (u == null) return REDIRECT_LOGIN;

        if (u.getUsername().equals(name)) {
            model.addAttribute("user", u);
            model.addAttribute("heading", HEADING);
            return "edit-user";
        } else {
            model.addAttribute("user", u);
            model.addAttribute("heading", HEADING);
            return "profile";
        }
    }

    @RequestMapping(value = {"all-users"}, method = RequestMethod.GET)
    public String getAllUsers(ModelMap model) throws SQLException {
        model.addAttribute("users", userDAO.getList());
        model.addAttribute("heading", HEADING);
        return "all-users";
    }

    @RequestMapping(value = "add-user-form", method = RequestMethod.GET)
    public String showAddUserForm() {
        return "add-user";
    }

    @RequestMapping(value = "add-user", method = RequestMethod.POST)
    @ResponseBody
    public Response addUser(@RequestBody Profile profile) throws SQLException, IOException {

        Role role = roleDAO.getRole(profile.getRole());
        User u = new User(profile.getUsername(), profile.getEmail(), role);
        userDAO.add(u);
        Credential c = new Credential(u.getUserId(), passwordEncoder.encode(profile.getPassword()), "");
        credDAO.add(c);
        return new Response(Response.Status.OK, "Saved");
    }

    @RequestMapping(value = "edit-user", method = RequestMethod.POST)
    @ResponseBody
    public Response editUser(@RequestBody Profile profile) throws SQLException, IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        Response response;
        if (name.isEmpty() || !name.equals(profile.getUsername())) {
            response = new Response(Response.Code.PreconditionFailed,
                    new UsernameNotFoundException("Incorrect username or password"));
            response.sendRedirect("error");
        }

        User u = userDAO.get(profile.getUserid());
        if (profile.hasUsername()) u.setUsername(profile.getUsername());
        if (profile.hasEmail()) u.setEmail(profile.getEmail());
        if (profile.hasPic()) u.setPic(profile.getPic());
        userDAO.update(u);
        return new Response(Response.Status.OK, "Saved");
    }

    @RequestMapping(value = {"/", "all-questions"}, method = RequestMethod.GET)
    public String getAllQuestions(ModelMap model) {
        List<Question> questions = questionDAO.getList();
        List<Category> categories = categoryDAO.getList();
        model.addAttribute("quests", questions);
        model.addAttribute("catgs", categories);
        model.addAttribute("heading", HEADING);
        return "all-questions";
    }

    @RequestMapping(value = "question", method = RequestMethod.GET)
    public String getQuestion(ModelMap model, @RequestParam(value = "id") Long id) {
        Question questions = questionDAO.get(id);
        model.addAttribute("quest", questions);
        model.addAttribute("heading", HEADING);
        return "question";
    }

    @RequestMapping(value = "add-question-form", method = RequestMethod.GET)
    public String addQuestionForm(ModelMap model) {
        String username = getAuthenticatedUsername();
        if (username == null) return REDIRECT_TO_MAIN;

        List<Category> catgs = categoryDAO.getList();
        model.addAttribute("catgs", catgs);
        model.addAttribute("heading", HEADING);
        return "add-question";
    }

    @RequestMapping(value = "add-question", method = RequestMethod.POST)
    public Response addQuestion(@RequestBody QuestionTeplate questionTemp) {
        String u = getAuthenticatedUsername();
        if (u == null) return new Response(Response.Status.ERROR, NOT_ENOUGH_RIGHTS);

        User user = userDAO.get(u);
        if (user == null) return new Response(Response.Status.ERROR, NOT_ENOUGH_RIGHTS);

        Category catg = categoryDAO.get(questionTemp.getCategoryId());
        Question question = new Question(questionTemp.getDescription(),
                questionTemp.getText(),
                catg, user,
                new java.sql.Date(new java.util.Date().getTime()));
        questionDAO.add(question);
        return new Response(Response.Status.OK, "Succeed");
    }

    @RequestMapping(value = "add-answer", method = RequestMethod.POST)
    public Response addAnswer(@RequestBody AnswerTemplate answerTemp) {
        String u = getAuthenticatedUsername();
        if (u == null) new Response(Response.Status.ERROR, NOT_ENOUGH_RIGHTS);

        User user = userDAO.get(u);
        if (user == null) new Response(Response.Status.ERROR, NOT_ENOUGH_RIGHTS);

        Question quest = questionDAO.get(answerTemp.getQuestionId());
        Answer question = new Answer(answerTemp.getInformation(),
                new java.sql.Date(new java.util.Date().getTime()),
                quest,
                user);
        answerDAO.add(question);
        return new Response(Response.Status.OK, "Succeed");
    }

    @SuppressWarnings("unchecked")
    private String getAuthenticatedUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String token = null;
        if (auth instanceof OAuth2Authentication) {
            LinkedHashMap<String, String> details = (LinkedHashMap<String, String>) ((OAuth2Authentication) auth)
                    .getUserAuthentication().getDetails();
            token = details.get("sub");
        }

        if (token != null) {
            User user = credDAO.getUserByToken(token);
            return user != null ? user.getUsername() : null;
        } else {
            return auth.getName();
        }
    }
}
