package pl.coderslab.users;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class UserLogin extends HttpServlet {
    UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("loggedUser") != null) {
            response.sendRedirect("/user/list");
            return;
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = request.getParameter("email");
        String userPassword = request.getParameter("password");

        if (userPassword == null || userPassword.isEmpty()
                || userEmail == null || userEmail.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Wszystkie pola są wymagane");
            return;
        }

        User currentUser;
        try {
            currentUser =  userDao.read(userEmail);
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "SQL error");
            return;
        }

        if (currentUser == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Złe dane logowania.");
            return;
        }

        if (BCrypt.checkpw(userPassword, currentUser.getPassword())) {
            currentUser.setPassword(null);
            HttpSession session = request.getSession();
            session.setAttribute("loggedUser", currentUser);
            response.sendRedirect("/user/list");
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Złe dane logowania.");
            return;
        }
    }
}