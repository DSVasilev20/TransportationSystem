<%@ page import="Dataaccesslayer.models.TransportationVehicle" %>
<%@ page import="Businesslogiclayer.services.TransportationVehicleService" %>
<%@ page import="Dataaccesslayer.repositories.TransportationVehicleRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<%
    if (session.getAttribute("Id") == null) {
        response.sendRedirect("http://localhost:8080/TransportManagementSystem_war_exploded/login.jsp");
    } else {
%>

<%
    String id = request.getParameter("id");
    Integer vehicleId = Integer.parseInt(id);

    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    TransportationVehicleService vehicleService = TransportationVehicleService.getInstance();

    TransportationVehicle vehicle = vehicleService.getTransportationVehicleById(vehicleId);
%>

<div id="emptyAlert" class="alert alert-warning alert-dismissible fade show" role="alert" style="display: none;">
    <strong>Warning!</strong> You have to enter in all inputs
</div>

<div class="row">
    <h1 class="h2 mb-3"><strong>Edit Transportation Vehicle</strong></h1>
    <div class="col-12 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <form action="edit-transportation-vehicle-servlet" method="post">
                    <input type="hidden" class="form-control" name="id" value="<%= vehicle.getVehicleID() %>">
                    <div class="mb-3">
                        <label for="vehicleType" class="form-label">Vehicle Type</label>
                        <select class="form-control" id="vehicleType" name="vehicleType">
                            <option value="Car" <%= vehicle.getVehicleType().equals("Car") ? "selected" : "" %>>Car</option>
                            <option value="Truck" <%= vehicle.getVehicleType().equals("Truck") ? "selected" : "" %>>Truck</option>
                            <option value="Bike" <%= vehicle.getVehicleType().equals("Bike") ? "selected" : "" %>>Bike</option>
                               value="<%= vehicle.getVehicleType() %>">
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="currentLocation" class="form-label">Current Location</label>
                        <select class="form-control" id="currentLocation" name="currentLocation">
                            <option value="123 Main Street" <%= vehicle.getCurrentLocation().equals("123 Main Street") ? "selected" : "" %>>123 Main Street</option>
                            <option value="456 Elm Avenue" <%= vehicle.getCurrentLocation().equals("456 Elm Avenue") ? "selected" : "" %>>456 Elm Avenue</option>
                            <option value="789 Oak Lane" <%= vehicle.getCurrentLocation().equals("789 Oak Lane") ? "selected" : "" %>>789 Oak Lane</option>
                            <option value="101 Pine Boulevard" <%= vehicle.getCurrentLocation().equals("101 Pine Boulevard") ? "selected" : "" %>>101 Pine Boulevard</option>
                            <option value="202 Cedar Road" <%= vehicle.getCurrentLocation().equals("202 Cedar Road") ? "selected" : "" %>>202 Cedar Road</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="capacity" class="form-label">Capacity</label>
                        <input type="number" class="form-control" id="capacity" name="capacity"
                               value="<%= vehicle.getCapacity() %>">
                    </div>
                    <a href="http://localhost:8080/TransportManagementSystem_war_exploded/transportation-vehicles"
                       class="btn btn-light">Cancel</a>
                    <button type="submit" class="btn btn-primary">Save</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    updateDatetime(document.getElementById('currentDatetime'));
</script>

<%
    }
%>

<%@ include file="footer.jsp" %>