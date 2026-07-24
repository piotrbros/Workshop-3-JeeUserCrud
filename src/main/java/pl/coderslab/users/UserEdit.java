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
        getServletContext().getRequestDispatcher("/users/add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (userName == null || userName.isBlank()
                || email == null || email.isBlank()
                || password == null || password.isBlank()) {

            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Wszystkie pola są wymagane");
            return;
        }

        User newUser = new User(email, userName, password);

        try {
            userDao.create(newUser);
            response.sendRedirect("/user/list");
        } catch (SQLException e) {
            response.getWriter().append("ERROR CREATING NEW USER");
        }
    }
}