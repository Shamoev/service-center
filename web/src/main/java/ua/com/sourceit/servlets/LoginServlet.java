package ua.com.sourceit.servlets;

import ua.com.sourceit.services.UserService;
import ua.com.sourceit.jdbc.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/JSP/login.jsp");
        dispatcher.forward(req, resp);
        //OutputStream stream = resp.getOutputStream();
        /*PrintWriter writer = resp.getWriter();

        String response = "<form action=\"/services/login\" method=\"post\">\n" +
            "          <table border=\"0\" align=\"right\">\n" +
            "            <tr>\n" +
            "               <td>Login:</td>\n" +
            "               <td> <input type=\"text\" name=\"login\"/></td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "               <td>Password:</td>\n" +
            "               <td> <input type=\"password\" name=\"password\"/></td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "            <tr>\n" +
            "                <td colspan=\"1\" align=\"center\">\n" +
            "                  <input type=\"submit\" value=\"login\" name=\"login\" />\n" +
            "                </td>\n" +
            "                <td colspan=\"1\" align=\"center\">\n" +
            "                  <input type=\"submit\" value=\"register\" name=\"register\" />\n" +
            "                </td>\n" +
            "            </tr>\n" +
            "          </table>\n" +
            "        </form>";
        writer.println(response);*/
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setContentType("text/html");
            Enumeration<String> parameterNames = req.getParameterNames();
            Map<String, String> params = new HashMap<>();
            while (parameterNames.hasMoreElements()) {
                    String current = parameterNames.nextElement();
                    params.put(current, req.getParameter(current));
            }
        /*try {
            System.out.println("be-be-be");
            Thread.sleep(1L); // !!!
        } catch (InterruptedException e) {}*/
        String login;
            if ((login = params.get("login")) != null) {
                System.out.println("be-be-be");
                User user = userService.getUserByLogin(login);
                if (user == null || !user.getPassword().equals(params.get("password"))) {
                    HttpSession session = req.getSession(true);
                    session.setAttribute("loginFailed", true);
                    Cookie cookie = new Cookie("cookie-example", "true");
                    resp.addCookie(cookie);
                    resp.sendRedirect("/services/login");
                } else {
                    HttpSession session = req.getSession(true);
                    session.setAttribute("user", user);
                    session.setAttribute("loginFailed", false);
                    //Cookie cookie = new Cookie("user_logon", "true");
                    //resp.addCookie(cookie);
                    resp.sendRedirect("/services/welcome");
                }
            }
    }
}
