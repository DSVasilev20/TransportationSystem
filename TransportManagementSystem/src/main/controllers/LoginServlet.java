import Businesslogiclayer.services.EmployeeService;
import Businesslogiclayer.services.UserService;
import Dataaccesslayer.models.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {
    // Handling POST requests
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set the content type of the response
        resp.setContentType("text/html");
        // Get the PrintWriter object to write HTML response
        PrintWriter out = resp.getWriter();

        // Variables to store user input
        String email;
        String password;

        try {
            // Get user input from request parameters
            email = req.getParameter("email");
            password = req.getParameter("password");

            try {
                // Load SQL Server JDBC driver
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                // Get an instance of the EmployeeService
                EmployeeService userService = EmployeeService.getInstance();

                // Retrieve Employee object based on the provided email
                Employee employee = userService.getEmployeeByEmail(email);

                // Get the session associated with the request
                HttpSession session = req.getSession();

                // Check if the provided password matches the stored password
                if (employee.getPassword().equals(password)) {
                    // If passwords match, set user attributes in the session
                    session.setAttribute("Id", employee.getId());
                    session.setAttribute("FirstName", employee.getFirstName());
                    session.setAttribute("LastName", employee.getLastName());
                    session.setAttribute("Email", employee.getEmail());
                    session.setAttribute("Password", employee.getPassword());

                    // Redirect to the home page
                    resp.sendRedirect("http://localhost:8080/TransportManagementSystem_war_exploded/index.jsp");
                } else {
                    // If passwords don't match, set an attribute indicating wrong password
                    session.setAttribute("wrongpassword", "Your password is incorrect");
                    // Redirect to the login page
                    resp.sendRedirect("http://localhost:8080/TransportManagementSystem_war_exploded/login.jsp");
                }
            } catch (ClassNotFoundException e) {
                out.println(e.getMessage());
                throw new RuntimeException(e);
            }

        } catch (NumberFormatException e) {
            out.println("Error: Invalid input");
            return;
        }
    }
}