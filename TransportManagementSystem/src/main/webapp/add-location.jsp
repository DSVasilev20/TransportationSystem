<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<%@ page import="Businesslogiclayer.services.PackageService" %>
<%@ page import="Dataaccesslayer.models.Package" %>
<%@ page import="Dataaccesslayer.repositories.PackageRepository" %>
<%@ page import="java.util.List" %>

<%
    if (session.getAttribute("Id") == null) {
        response.sendRedirect("http://localhost:8080/TransportManagementSystem_war_exploded/login.jsp");
    } else {
%>

<div class="row">
    <h1 class="h2 mb-3"><strong>Add Location</strong></h1>
    <div class="col-12 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <form action="add-location-servlet" method="post">

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
                        <label for="timestamp" class="form-label">timestamp</label>
                        <input type="date" class="form-control" id="timestamp" name="timestamp">
                    </div>
                    <div class="mb-3">
                        <label for="latitude" class="form-label">Latitude</label>
                        <input type="number" class="form-control" id="latitude" name="latitude" step="0.0000000000000001">
                    </div>
                    <div class="mb-3">
                        <label for="longitude" class="form-label">Longitude</label>
                        <input type="number" class="form-control" id="longitude" name="longitude" step="0.0000000000000001">
                    </div>

                    <a href="http://localhost:8080/TransportManagementSystem_war_exploded/locations.jsp"
                       class="btn btn-light">Cancel</a>
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