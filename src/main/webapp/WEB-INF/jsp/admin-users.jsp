<!DOCTYPE html>
<html>
<head>
    <title>Users</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div class="navbar">
    Admin : User Management
</div>

<div class="container">

    <a class="back-link"
       href="${pageContext.request.contextPath}/admin/dashboard">
        &larr; Back to Dashboard
    </a>

    <div class="card">

        <div class="section-title">Users</div>
        <p>Manage system users below.</p>

        <div class="table-wrapper">
            <table class="table">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Mobile</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
                </thead>

                <tbody>
                <%
                    String editId = request.getParameter("edit");
                    java.util.List users = (java.util.List) request.getAttribute("users");

                    for (Object obj : users) {
                        com.company.usercreation.model.User u =
                                (com.company.usercreation.model.User) obj;

                        boolean isEdit =
                                editId != null && editId.equals(String.valueOf(u.getId()));
                %>

                <tr>
                    <%
                        if (isEdit) {
                    %>
                    <form method="post"
                          action="${pageContext.request.contextPath}/admin/users/<%= u.getId() %>/update">

                        <td>
                            <input type="text"
                                   name="fullName"
                                   value="<%= u.getFullName() %>"
                                   required>
                        </td>

                        <td>
                            <input type="email"
                                   name="email"
                                   value="<%= u.getEmail() %>"
                                   required>
                        </td>

                        <td>
                            <input type="text"
                                   name="mobile"
                                   value="<%= u.getMobile() %>"
                                   required>
                        </td>

                        <td><%= u.getStatus() %></td>

                        <td class="actions">
                            <button type="submit">Save</button>
                            <a href="${pageContext.request.contextPath}/admin/users">
                                Cancel
                            </a>
                        </td>
                    </form>
                    <%
                    } else {
                    %>

                    <td><%= u.getFullName() %></td>
                    <td><%= u.getEmail() %></td>
                    <td><%= u.getMobile() %></td>
                    <td><%= u.getStatus() %></td>

                    <td class="actions">

                        <a href="${pageContext.request.contextPath}/admin/users?edit=<%= u.getId() %>">
                            Edit
                        </a>

                        <%
                            if (u.getStatus() != com.company.usercreation.model.User.Status.ACTIVE
                                    && u.getPasswordHash() != null) {
                        %>
                        <form method="post"
                              action="${pageContext.request.contextPath}/admin/users/<%= u.getId() %>/activate"
                              onsubmit="return confirm('Activate this user?');">
                            <button type="submit">Activate</button>
                        </form>
                        <%
                            }
                        %>

                        <%
                            if (u.getStatus() == com.company.usercreation.model.User.Status.ACTIVE) {
                        %>
                        <form method="post"
                              action="${pageContext.request.contextPath}/admin/users/<%= u.getId() %>/deactivate"
                              onsubmit="return confirm('Deactivate this user?');">
                            <button type="submit">Deactivate</button>
                        </form>
                        <%
                            }
                        %>

                        <form method="post"
                              action="${pageContext.request.contextPath}/admin/users/<%= u.getId() %>/delete"
                              onsubmit="return confirm('This will permanently remove access for this user. Continue?');">
                            <button class="button-danger" type="submit">
                                Delete
                            </button>
                        </form>

                    </td>
                    <%
                        }
                    %>
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
