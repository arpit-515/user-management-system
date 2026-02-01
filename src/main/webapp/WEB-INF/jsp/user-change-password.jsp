<!DOCTYPE html>
<html>
<head>
    <title>Change Password</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div class="navbar">
    Change Password
</div>

<div class="container">
    <a class="back-link"
       href="${pageContext.request.contextPath}/users/dashboard">
        - Back to Dashboard
    </a>

    <div class="card" style="max-width: 400px;">
        <h3>Verify Current Password</h3>

        <form method="post"
              action="${pageContext.request.contextPath}/users/change-password">

            <label>Current Password</label>
            <input type="password" name="oldPassword" required>

            <button type="submit">Continue</button>
        </form>

        <p class="error">${error}</p>
    </div>

</div>

</body>
</html>
