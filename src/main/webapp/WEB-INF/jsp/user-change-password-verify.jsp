<!DOCTYPE html>
<html>
<head>
    <title>Verify Password Change</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div class="navbar">
    Verify Password Change
</div>

<div class="container center">

    <div class="card" style="max-width: 600px; width: 100%;">

        <a class="back-link"
           href="${pageContext.request.contextPath}/users/dashboard">
            &larr; Back to Dashboard
        </a>

        <div class="section-title" style="text-align:center;">
            Verify &amp; Change Password
        </div>

        <% if (request.getAttribute("info") != null) { %>
        <p class="success">${info}</p>
        <% } %>

        <form method="post"
              action="${pageContext.request.contextPath}/users/change-password/verify">

            <label for="otp">OTP</label>
            <input type="text" id="otp"
                   name="otp" required>

            <label for="newPassword">New Password</label>
            <input type="password" id="newPassword"
                   name="newPassword" required>

            <label for="confirmPassword">Confirm Password</label>
            <input type="password" id="confirmPassword"
                   name="confirmPassword" required>

            <button type="submit">Change Password</button>
        </form>

        <p class="error">${error}</p>

    </div>

</div>

</body>
</html>
