<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<%
    if (session.getAttribute("Id") == null) {
        response.sendRedirect("http://localhost:8080/TransportManagementSystem_war_exploded/login.jsp");
    } else {
%>

<div class="row">
    <h1 class="h2 mb-3"><strong>Add Transportation Vehicle</strong></h1>
    <div class="col-12 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <form action="add-transportation-vehicle-servlet" method="post">

                    <div class="mb-3">
                        <label for="vehicleType" class="form-label">Vehicle Type</label>
                        <select class="form-control" id="vehicleType" name="vehicleType">
                            <option value="Car">Car</option>
                            <option value="Truck">Truck</option>
                            <option value="Bike">Bike</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="currentLocation" class="form-label">Current Location</label>
                        <select class="form-control" id="currentLocation" name="currentLocation">
                            <option value="123 Main Street">123 Main Street</option>
                            <option value="456 Elm Avenue">456 Elm Avenue</option>
                            <option value="789 Oak Lane">789 Oak Lane</option>
                            <option value="101 Pine Boulevard">101 Pine Boulevard</option>
                            <option value="202 Cedar Road">202 Cedar Road</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="capacity" class="form-label">Capacity</label>
                        <input type="number" class="form-control" id="capacity" name="capacity">
                    </div>

                    <a href="http://localhost:8080/TransportManagementSystem_war_exploded/transportation-vehicles.jsp" class="btn btn-light">Cancel</a>
                    <button type="submit" class="btn btn-primary">Add</button>
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