<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Audit Logs</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div class="navbar">
    Admin : Audit Logs
</div>

<div class="container">

    <a class="back-link"
       href="${pageContext.request.contextPath}/admin/dashboard">
        &larr; Back to Dashboard
    </a>

    <div class="card">
        <div class="section-title">System Logs</div>

        <div class="table-wrapper">
            <table class="table">
                <thead>
                <tr>
                    <th>Admin ID</th>
                    <th>Action</th>
                    <th>Target User</th>
                    <th>Timestamp</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List logs = (List) request.getAttribute("logs");

                    if (logs != null && !logs.isEmpty()) {
                        for (Object obj : logs) {
                            com.company.usercreation.model.AuditLog log =
                                    (com.company.usercreation.model.AuditLog) obj;
                %>
                <tr>
                    <td><%= log.getAdminId() %></td>
                    <td><%= log.getAction() %></td>
                    <td>
                        <%= log.getTargetUserId() != null
                                ? log.getTargetUserId()
                                : "-" %>
                    </td>
                    <td><%= log.getCreatedAt() %></td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="4" style="text-align:center;">
                        No logs found.
                    </td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>

</div>

</body>
</html>
