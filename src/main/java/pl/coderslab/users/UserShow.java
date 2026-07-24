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

@WebServlet("/user/show")
public class UserShow extends HttpServlet {
    private final UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Error when parsing id");
            return;
        }
        User currentUser;
        try {
            currentUser = userDao.read(id);
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "SQL error");
            return;
        }

        if (currentUser == null) {
            response.sendError(404, "User not found");
            return;
        }
        request.setAttribute("currentUser", currentUser);

        getServletContext().getRequestDispatcher("/users/show.jsp").forward(request, response);
    }
}