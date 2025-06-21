import Businesslogiclayer.services.LocationService;
import jakarta.servlet.ServletException;
import Dataaccesslayer.models.Location;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "editLocationServlet", value = "/edit-location-servlet")
public class EditLocationServlet extends HttpServlet {
    // Process POST requests
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set the content type of the response
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Get the 'id' parameter from the request
        String idParameter = req.getParameter("id");
        int packageID;
        String timestamp;
        float latitude;
        float longitude;

        // Check if 'id' parameter is provided and not empty
        if (idParameter != null && !idParameter.isEmpty()) {
            try {
                // Parse the 'id' parameter to get the location id
                int id = Integer.parseInt(idParameter);
                try {
                    // Parse other parameters required for location update
                    packageID = Integer.parseInt(req.getParameter("packageID"));
                    timestamp = req.getParameter("timestamp");
                    latitude = Float.parseFloat(req.getParameter("latitude"));
                    longitude = Float.parseFloat(req.getParameter("longitude"));

                    // Check if any required fields are empty
                    if (areFieldsEmpty(packageID, timestamp, latitude, longitude)) {
                        out.println("Error: All fields must be filled.");
                        return;
                    }

                    try {
                        // Load the SQL Server JDBC driver
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                        // Create an instance of LocationService
                        LocationService locationService = LocationService.getInstance();

                        // Update the location using the LocationService
                        locationService.updateLocation(new Location(id, packageID, timestamp, latitude, longitude));

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
            } catch (NumberFormatException e) {
                out.println("Error: Invalid input for id.");
                return;
            }
        } else {
            out.println("Error: Missing parameter 'id'.");
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
