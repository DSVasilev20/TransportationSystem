import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    // Instance variable to store the message
    private String message;

    // Initialization method called once when the servlet is loaded
    public void init() {
        // Initialize the message
        message = "Hello World!";
    }

    // Handling GET requests
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Set the content type of the response
        response.setContentType("text/html");

        // Get the PrintWriter object to write HTML response
        PrintWriter out = response.getWriter();

        // HTML response with the message
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    // Destruction method called once when the servlet is unloaded
    public void destroy() {
        // Perform cleanup or resource release if needed
    }
}