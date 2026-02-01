<!DOCTYPE html>
<html>
<head>
    <title>User Login</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div class="navbar">
    User Login
</div>

<div class="container">

    <div class="card" style="max-width: 400px;">
        <h3>User Login</h3>

        <form method="post"
              action="${pageContext.request.contextPath}/users/login">

            <label>Email</label><br>
            <input type="email" name="email" required /><br><br>

            <label>Password</label><br>
            <input type="password" name="password" required /><br><br>

            <label>Captcha - ${captchaQuestion}</label>
            <input type="text" name="captcha" required>

            <button type="submit">Login</button>
        </form>

        <p style="color:red">${error}</p>
        <p style="color:red">${success}</p>
        <%
            if ("true".equals(request.getParameter("passwordChanged"))) {
        %>
        <p style="color:green;">
            Password changed successfully. Please login again.
        </p>
        <%
            }
        %>
        <%
            if ("true".equals(request.getParameter("passwordSet"))) {
        %>
        <p class="success">
            Password set successfully. Please login.
        </p>
        <%
            }
        %>
        <%
            if ("true".equals(request.getParameter("verified"))) {
        %>
        <p class="success">
            Verification completed. Please login.
        </p>
        <%
            }
        %>

    </div>

</div>
<a href="${pageContext.request.contextPath}/users/forgot-password">
    Forgot Password?
</a>
</body>
</html>
