import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "logoutServlet", value = "/logout-servlet")
public class LogoutServlet extends HttpServlet {
    // Handling POST requests
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set the content type of the response
        resp.setContentType("text/html");
        // Get the PrintWriter object to write HTML response
        PrintWriter out = resp.getWriter();

        // Get the session associated with the request (if it exists)
        HttpSession session = req.getSession(false);

        // If a session exists, invalidate it
        if (session != null) {
            session.invalidate();
        }

        // Redirect to the login page
        resp.sendRedirect("http://localhost:8080/TransportManagementSystem_war_exploded/login.jsp");
    }
}
