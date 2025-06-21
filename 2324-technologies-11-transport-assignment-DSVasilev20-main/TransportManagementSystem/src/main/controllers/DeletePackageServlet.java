import Businesslogiclayer.services.LocationService;
import Businesslogiclayer.services.PackageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "deletePackageServlet", value = "/delete-package-servlet")
public class DeletePackageServlet extends HttpServlet {
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
                // Parse the 'delete' parameter to get the package id
                int id = Integer.parseInt(idParameter);

                try {
                    // Load the SQL Server JDBC driver
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                    // Create an instance of PackageService
                    PackageService packageService = PackageService.getInstance();

                    // Delete the package using the PackageService
                    packageService.deletePackage(id);

                    // Set a success message in the session
                    HttpSession session = req.getSession();
                    session.setAttribute("success", "You successfully deleted the package");

                    // Redirect to the packages page
                    resp.sendRedirect("http://localhost:8080/TransportManagementSystem_war_exploded/packages.jsp");

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
