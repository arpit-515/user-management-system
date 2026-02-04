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

<div class="container center">

    <div class="card" style="max-width: 400px; width: 100%;">

        <div class="section-title" style="text-align:center;">
            User Login
        </div>

        <form method="post"
              action="${pageContext.request.contextPath}/users/login">

            <label for="email">Email</label>
            <input type="email" id="email"
                   name="email" required />

            <label for="password">Password</label>
            <input type="password" id="password"
                   name="password" required />

            <label for="captcha">
                Captcha – ${captchaQuestion}
            </label>
            <input type="text" id="captcha"
                   name="captcha" required />

            <button type="submit">Login</button>
        </form>

        <p class="error">${error}</p>
        <p class="error">${success}</p>

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

        <a class="back-link"
           href="${pageContext.request.contextPath}/users/forgot-password">
            Forgot Password?
        </a>

    </div>

</div>

</body>
</html>
