<%@ page import="Dataaccesslayer.models.Location" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Businesslogiclayer.services.LocationService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>

<%
    if (session.getAttribute("Id") == null) {
        response.sendRedirect("http://localhost:8080/TransportManagementSystem_war_exploded/login.jsp");
    } else {
%>

<div class="page-header">
    <h3 class="page-title">Locations</h3>
</div>

<div class="card">
    <div class="card_body">
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>PACKAGE ID</th>
                    <th>TIMESTAMP</th>
                    <th>LATITUDE</th>
                    <th>LONGITUDE</th>
                    <th><a href="http://localhost:8080/TransportManagementSystem_war_exploded/add-location.jsp" class="btn btn-success btn-sm" type="button">Add Location</a></th>
                </tr>
                </thead>
                <tbody>
                <%
                    try {
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                        LocationService locationService = LocationService.getInstance();
                        List<Location> locationList = locationService.getLocations();

                        for (Location location : locationList) {
                %>
                <tr>
                    <td><%= location.getId() %></td>
                    <td><%= location.getPackageID() %></td>
                    <td><%= location.getTimestamp() %></td>
                    <td><%= location.getLatitude() %></td>
                    <td><%= location.getLongitude() %></td>
                    <td>
                        <form action="delete-location-servlet" method="POST">
                            <a href="http://localhost:8080/TransportManagementSystem_war_exploded/edit-location.jsp?id=<%= location.getId() %>"
                               class="btn btn-primary btn-sm">Edit</a>
                            <button type="submit" name="delete" class="btn btn-danger btn-sm" value="<%= location.getId() %>">Delete</button>
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