import Businesslogiclayer.services.UserService;
import Dataaccesslayer.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "editUserServlet", value = "/edit-user-servlet")
public class EditUserServlet extends HttpServlet {
    // Process POST requests
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set the content type of the response
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Get the 'id' parameter from the request
        String idParameter = req.getParameter("id");
        String firstName;
        String lastName;
        String email;
        String phoneNumber;

        // Check if 'id' parameter is provided and not empty
        if (idParameter != null && !idParameter.isEmpty()) {
            try {
                // Parse the 'id' parameter to get the user id
                int id = Integer.parseInt(idParameter);
                try {
                    // Get user details from the request parameters
                    firstName = req.getParameter("firstName");
                    lastName = req.getParameter("lastName");
                    email = req.getParameter("email");
                    phoneNumber = req.getParameter("phoneNumber");

                    // Check if all fields are filled
                    if (areFieldsEmpty(firstName, lastName, email, phoneNumber)) {
                        out.println("Error: All fields must be filled.");
                        return;
                    }

                    // Check if first name and last name contain valid English characters
                    if (!isValidEnglishName(firstName) || !isValidEnglishName(lastName)) {
                        out.println("Error: Invalid characters in first name or last name.");
                        return;
                    }

                    // Check if phone number has exactly 10 digits
                    if (!isValidPhoneNumberLength(phoneNumber)) {
                        out.println("Error: Phone number must have exactly 10 digits.");
                        return;
                    }

                    try {
                        // Load the SQL Server JDBC driver
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                        // Create an instance of UserService
                        UserService userService = UserService.getInstance();

                        // Update the user using the UserService
                        userService.updateUser(new User(id, firstName, lastName, email, phoneNumber));

                        // Redirect to the index page
                        resp.sendRedirect("http://localhost:8080/TransportManagementSystem_war_exploded/index.jsp");
                    } catch (ClassNotFoundException e) {
                        out.println(e.getMessage());
                        throw new RuntimeException(e);
                    }

                } catch (NumberFormatException e) {
                    out.println("Error: Invalid input.");
                    return;
                }
            } catch (NumberFormatException e) {
                out.println("Error: Invalid input for id.");
                return;
            }
        } else {
            out.println("Error: Missing parameter 'id'.");
            return;
        }
    }

    // Check if the input name is a valid English name
    private boolean isValidEnglishName(String name) {
        String regex = "^[a-zA-Z]+$";
        return name.matches(regex);
    }

    // Check if any of the provided fields are empty
    private boolean areFieldsEmpty(String... fields) {
        for (String field : fields) {
            if (field == null || field.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    // Check if the phone number has exactly 10 digits
    private boolean isValidPhoneNumberLength(String phoneNumber) {
        return phoneNumber != null && phoneNumber.length() == 10;
    }
}