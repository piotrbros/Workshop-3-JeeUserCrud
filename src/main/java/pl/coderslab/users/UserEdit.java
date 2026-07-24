package pl.coderslab.users;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user/edit")
public class UserEdit extends HttpServlet {

    private final UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User currentUser;
        try {
            currentUser =  userDao.read(id);
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "SQL error");
            return;
        }
        request.setAttribute("currentUser", currentUser);
        getServletContext().getRequestDispatcher("/users/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String stringId = request.getParameter("id");

        if (userName == null || userName.isBlank()
                || email == null || email.isBlank()
                || password == null || password.isBlank()
                || stringId == null || stringId.isBlank()) {

            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Wszystkie pola są wymagane");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(stringId);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Error when parsing id");
            return;
        }
        User currentUser = new User(id, email, userName, password);

        try {
            userDao.update(currentUser);
            response.sendRedirect("/user/list");
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Error when doing SQL update for current user.");
        }
    }
}