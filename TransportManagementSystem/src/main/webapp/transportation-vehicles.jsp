<%@ page import="Dataaccesslayer.models.TransportationVehicle" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Businesslogiclayer.services.TransportationVehicleService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<%
    if (session.getAttribute("Id") == null) {
        response.sendRedirect("http://localhost:8080/TransportManagementSystem_war_exploded/login.jsp");
    } else {
%>




<div class="page-header">
    <h3 class="page-title">Transportation Vehicles</h3>
</div>

<div class="card">
    <div class="card_body">
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>VEHICLE TYPE</th>
                    <th>CURRENT LOCATION</th>
                    <th>CAPACITY</th>
                    <th><a href="http://localhost:8080/TransportManagementSystem_war_exploded/add-transportation-vehicle.jsp" class="btn btn-success btn-sm" type="button">Add Vehicle</a></th>
                </tr>
                </thead>
                <tbody>
                <%
                    try {
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                        TransportationVehicleService locationService = TransportationVehicleService.getInstance();
                        List<TransportationVehicle> vehicleList = locationService.getTransportationVehicles();

                        for (TransportationVehicle vehicle : vehicleList) {
                %>
                <tr>
                    <td><%= vehicle.getVehicleID() %></td>
                    <td><%= vehicle.getVehicleType() %></td>
                    <td><%= vehicle.getCurrentLocation() %></td>
                    <td><%= vehicle.getCapacity() %></td>
                    <td>
                        <form action="delete-transportation-vehicles-servlet" method="POST">
                            <a href="http://localhost:8080/TransportManagementSystem_war_exploded/edit-transportation-vehicle.jsp?id=<%= vehicle.getVehicleID() %>"
                               class="btn btn-primary btn-sm">Edit</a>
                            <button type="submit" name="delete" class="btn btn-danger btn-sm" value="<%= vehicle.getVehicleID() %>">Delete</button>
                        </form>
                    </td>
                </tr>

                <%
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error: " + e.getMessage());
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%
    }
%>

<%@ include file="footer.jsp" %>