import Businesslogiclayer.services.LocationService;
import Businesslogiclayer.services.PackageService;
import Dataaccesslayer.models.Location;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "addLocationServlet", value = "/add-location-servlet")
public class AddLocationServlet extends HttpServlet {
    // Process POST requests
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set the content type of the response
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Variables to store input parameters
        int packageID;
        String timestamp;
        int latitude;
        int longitude;

        // Parse parameters from the request
        try {
            packageID = Integer.parseInt(req.getParameter("packageID"));
            timestamp = req.getParameter("timestamp");
            latitude = Integer.parseInt(req.getParameter("latitude"));
            longitude = Integer.parseInt(req.getParameter("longitude"));

            // Check if any fields are empty
            if (areFieldsEmpty(packageID, timestamp, latitude, longitude)) {
                out.println("Error: All fields must be filled.");
                return;
            }

            try {
                // Load the SQL Server JDBC driver
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                // Create an instance of LocationService
                LocationService locationService = LocationService.getInstance();

                // Add a new location using the LocationService
                locationService.addLocation(new Location(0, packageID, timestamp, latitude, longitude));

                // Redirect to the locations page
                resp.sendRedirect("http://localhost:8080/TransportManagementSystem_war_exploded/locations.jsp");
            } catch (ClassNotFoundException e) {
                out.println(e.getMessage());
                throw new RuntimeException(e);
            }

        } catch (NumberFormatException e) {
            out.println("Error: Invalid input.");
            return;
        }
    }

    // Check if any fields are empty
    private boolean areFieldsEmpty(Object... fields) {
        for (Object field : fields) {
            if (field == null) {
                return true;
            }
            if (field instanceof String && ((String) field).trim().isEmpty()) {
                return true;
            }
            if (field instanceof Integer && ((Integer) field) == 0) {
                continue;
            }
            return false;
        }
        return true;
    }
}