import Businesslogiclayer.services.TransportationVehicleService;
import Dataaccesslayer.models.Package;
import Dataaccesslayer.models.TransportationVehicle;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "editTransportationVehicleServlet", value = "/edit-transportation-vehicle-servlet")
public class EditTransportationVehicleServlet extends HttpServlet {
    // Process POST requests
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set the content type of the response
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Get the 'id' parameter from the request
        String idParameter = req.getParameter("id");
        String vehicleType;
        String currentLocation;
        int capacity;

        // Check if 'id' parameter is provided and not empty
        if (idParameter != null && !idParameter.isEmpty()) {
            try {
                // Parse the 'id' parameter to get the transportation vehicle id
                int id = Integer.parseInt(idParameter);
                try {
                    // Parse other parameters required for transportation vehicle update
                    vehicleType = req.getParameter("vehicleType");
                    currentLocation = req.getParameter("currentLocation");
                    capacity = Integer.parseInt(req.getParameter("capacity"));

                    // Check if capacity is a non-negative value
                    if (!isCapacityValid(capacity)) {
                        out.println("Error: Capacity must be a non-negative value.");
                        return;
                    }

                    try {
                        // Load the SQL Server JDBC driver
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                        // Create an instance of TransportationVehicleService
                        TransportationVehicleService vehicleService = TransportationVehicleService.getInstance();

                        // Update the transportation vehicle using the TransportationVehicleService
                        vehicleService.updateTransportationVehicle(new TransportationVehicle(id, vehicleType, currentLocation, capacity));

                        // Redirect to the transportation vehicles page
                        resp.sendRedirect("http://localhost:8080/TransportManagementSystem_war_exploded/transportation-vehicles.jsp");
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

    // Check if the capacity is a non-negative value
    private boolean isCapacityValid(int capacity) {
        return capacity >= 0;
    }
}
