<%@ page import="Dataaccesslayer.models.Location" %>
<%@ page import="Businesslogiclayer.services.LocationService" %>
<%@ page import="Businesslogiclayer.services.PackageService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<%
    if (session.getAttribute("Id") == null) {
        response.sendRedirect("http://localhost:8080/TransportManagementSystem_war_exploded/login.jsp");
    } else {
%>

<%
    String id = request.getParameter("id");
    Integer locationId = Integer.parseInt(id);


    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    LocationService locationService = LocationService.getInstance();

    Location location = locationService.getLocationById(locationId);
%>

<div id="emptyAlert" class="alert alert-warning alert-dismissible fade show" role="alert" style="display: none;">
    <strong>Warning!</strong> You have to enter in all inputs
</div>

<div class="row">
    <h1 class="h2 mb-3"><strong>Edit Location</strong></h1>
    <div class="col-12 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <form action="edit-location-servlet" method="post">
                    <input type="hidden" class="form-control" name="id" value="<%= location.getId() %>">
                    <div class="mb-3">
                        <label for="packageID" class="form-label">Package ID</label>
                        <select class="form-control" id="packageID" name="packageID">
                            <%
                                List<Integer> packageIds = PackageService.getInstance().getPackageIds();

                                if (!packageIds.isEmpty()) {
                                    for (Integer packageId : packageIds) {
                            %>
                            <option value="<%= packageId %>"><%= packageId %>
                            </option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="timestamp" class="form-label">Timestamp</label>
                        <input type="date" class="form-control" id="timestamp" name="timestamp"
                               value="<%= location.getTimestamp() %>">
                    </div>
                    <div class="mb-3">
                        <label for="latitude" class="form-label">Latitude</label>
                        <input type="number" class="form-control" id="latitude" name="latitude"
                               value="<%= location.getLatitude() %>">
                    </div>
                    <div class="mb-3">
                        <label for="longitude" class="form-label">Longitude</label>
                        <input type="number" class="form-control" id="longitude" name="longitude"
                               value="<%= location.getLongitude() %>">
                    </div>

                    <a href="http://localhost:8080/TransportManagementSystem_war_exploded/locations.jsp"
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