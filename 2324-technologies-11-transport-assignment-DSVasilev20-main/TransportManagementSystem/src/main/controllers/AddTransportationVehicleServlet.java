import Businesslogiclayer.services.TransportationVehicleService;
import Dataaccesslayer.models.TransportationVehicle;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "addTransportationVehicleServlet", value = "/add-transportation-vehicle-servlet")
public class AddTransportationVehicleServlet extends HttpServlet {
    // Process POST requests
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set the content type of the response
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Variables to store input parameters
        String vehicleType;
        String currentLocation;
        int capacity;

        // Parse parameters from the request
        try {
            vehicleType = req.getParameter("vehicleType");
            currentLocation = req.getParameter("currentLocation");
            capacity = Integer.parseInt(req.getParameter("capacity"));

            // Check if capacity is non-negative
            if (!isCapacityValid(capacity)) {
                out.println("Error: Capacity must be a non-negative value.");
                return;
            }

            try {
                // Load the SQL Server JDBC driver
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                // Create an instance of TransportationVehicleService
                TransportationVehicleService vehicleService = TransportationVehicleService.getInstance();

                // Add a new transportation vehicle using the TransportationVehicleService
                vehicleService.addTransportationVehicle(new TransportationVehicle(0, vehicleType, currentLocation, capacity));

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
    }

    // Check if the capacity is non-negative
    private boolean isCapacityValid(int capacity) {
        return capacity >= 0;
    }
}
