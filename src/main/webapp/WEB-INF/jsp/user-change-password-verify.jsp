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

    <div style="width: 100%; max-width: 600px;">

        <a class="back-link"
           href="${pageContext.request.contextPath}/users/dashboard"
           style="display: inline-block; margin-bottom: 10px; font-weight: bold;">
            &larr; Back to Dashboard
        </a>

        <div class="card" style="width: 100%;">
            <h3 style="text-align:center;">Verify & Change Password</h3>

            <% if(request.getAttribute("info") != null) { %>
            <p class="success">${info}</p>
            <% } %>

            <form method="post"
                  action="${pageContext.request.contextPath}/users/change-password/verify">

                <label>OTP</label>
                <input type="text" name="otp" required>

                <label>New Password</label>
                <input type="password" name="newPassword" required>

                <label>Confirm Password</label>
                <input type="password" name="confirmPassword" required>

                <button type="submit">Change Password</button>
            </form>

            <p class="error">${error}</p>
        </div>

    </div> </div>

</body>
</html>