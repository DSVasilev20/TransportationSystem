import Businesslogiclayer.services.PackageService;
import Dataaccesslayer.models.Package;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "editPackageServlet", value = "/edit-package-servlet")
public class EditPackageServlet extends HttpServlet {
    // Process POST requests
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set the content type of the response
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // Get the 'id' parameter from the request
        String idParameter = req.getParameter("id");
        int senderUserID;
        int receiveUserID;
        String description;
        String status;
        String currentLocation;
        String deliveryDate;

        // Check if 'id' parameter is provided and not empty
        if (idParameter != null && !idParameter.isEmpty()) {
            try {
                // Parse the 'id' parameter to get the package id
                int id = Integer.parseInt(idParameter);
                try {
                    // Parse other parameters required for package update
                    senderUserID = Integer.parseInt(req.getParameter("senderUserID"));
                    receiveUserID = Integer.parseInt(req.getParameter("receiveUserID"));
                    description = req.getParameter("description");
                    status = req.getParameter("status");
                    currentLocation = req.getParameter("currentLocation");
                    deliveryDate = req.getParameter("deliveryDate");

                    // Check if current location contains valid English characters
                    if (!isValidEnglishName(currentLocation)) {
                        out.println("Error: Invalid characters in Current Location");
                        return;
                    }

                    // Check if any required fields are empty
                    if (areFieldsEmpty(senderUserID, receiveUserID, description, status, currentLocation, deliveryDate)) {
                        out.println("Error: All fields must be filled.");
                        return;
                    }

                    try {
                        // Load the SQL Server JDBC driver
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                        // Create an instance of PackageService
                        PackageService packageService = PackageService.getInstance();

                        // Update the package using the PackageService
                        packageService.updatePackage(new Package(id, senderUserID, receiveUserID, description, status, currentLocation, deliveryDate));

                        // Redirect to the packages page
                        resp.sendRedirect("http://localhost:8080/TransportManagementSystem_war_exploded/packages.jsp");
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

    // Check if the name contains valid English characters
    private boolean isValidEnglishName(String name) {
        String regex = "^[a-zA-Z]+$";
        return name.matches(regex);
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
                return true;
            }
        }
        return false;
    }
}