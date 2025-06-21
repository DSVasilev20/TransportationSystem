import Businesslogiclayer.services.LocationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "deleteLocationServlet", value = "/delete-location-servlet")
public class DeleteLocationServlet extends HttpServlet {
    // Process POST requests
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set the content type of the response
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Get the 'delete' parameter from the request
        String idParameter = req.getParameter("delete");

        // Check if 'delete' parameter is provided and not empty
        if (idParameter != null && !idParameter.isEmpty()) {
            try {
                // Parse the 'delete' parameter to get the location id
                int id = Integer.parseInt(idParameter);

                try {
                    // Load the SQL Server JDBC driver
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                    // Create an instance of LocationService
                    LocationService locationService = LocationService.getInstance();

                    // Delete the location using the LocationService
                    locationService.deleteLocation(id);

                    // Set a success message in the session
                    HttpSession session = req.getSession();
                    session.setAttribute("success", "You successfully deleted the location");

                    // Redirect to the locations page
                    resp.sendRedirect("http://localhost:8080/TransportManagementSystem_war_exploded/locations.jsp");

                } catch (ClassNotFoundException e) {
                    out.println(e.getMessage());
                    throw new RuntimeException(e);
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
}
