package pl.coderslab.users;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user/list")
public class UserList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        User[] allUsers;
        try {
            allUsers = userDao.findAll();
        } catch (SQLException e) {
            response.getWriter().append("SQL ERROR WHEN GETTING ALL USERS FROM THE DB.");
            return;
        }
        request.setAttribute("allUsers", allUsers);

        getServletContext().getRequestDispatcher("/users/list.jsp").forward(request, response);
    }
}