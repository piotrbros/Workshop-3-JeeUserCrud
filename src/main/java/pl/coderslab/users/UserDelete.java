package pl.coderslab.users;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pl.coderslab.entity.UserDao;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user/delete")
public class UserDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            userDao.delete(id);
            response.sendRedirect("/user/list");
        } catch (NumberFormatException e) {
            response.getWriter().append("INVALID ID");
        } catch (SQLException e) {
            response.getWriter().append("SQL ERROR WHEN DELETING FROM DB");
        }
    }
}