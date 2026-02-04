<!DOCTYPE html>
<html>
<head>
    <title>Admin Login</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div class="navbar">
    Administrator Login
</div>

<div class="container center">

    <div class="card" style="max-width: 600px; width: 100%;">

        <div class="section-title" style="text-align:center;">
            Administrator Login
        </div>

        <form method="post"
              action="${pageContext.request.contextPath}/admin/login">

            <label for="username">Username</label>
            <input type="text" id="username"
                   name="username" required>

            <label for="password">Password</label>
            <input type="password" id="password"
                   name="password" required>

            <button type="submit">Login</button>
        </form>

        <p class="error">${error}</p>
        <p class="success">${success}</p>

        <%
            if ("true".equals(request.getParameter("passwordChanged"))) {
        %>
        <p class="success">
            Password changed successfully. Please login again.
        </p>
        <%
            }
        %>

        <%
            if ("true".equals(request.getParameter("deactivated"))) {
        %>
        <p class="error">
            Admin account has been deactivated.
        </p>
        <%
            }
        %>

    </div>

</div>

</body>
</html>
