<!DOCTYPE html>
<html>
<head>
    <title>Change Admin Password</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="navbar">
    Admin : Change Password
</div>

<div class="container">
    <a class="back-link"
       href="${pageContext.request.contextPath}/admin/dashboard">
        - Back to Dashboard
    </a>

    <div class="card" style="max-width: 500px;">
        <h3>Change Admin Password</h3>
        <p>Please enter your current password to continue.</p>

        <form method="post"
              action="${pageContext.request.contextPath}/admin/change-password"
              onsubmit="return confirm('Are you sure you want to change the admin password?');">

            <label>Current Password</label><br>
            <input type="password" name="currentPassword" required /><br><br>

            <label>New Password</label><br>
            <input type="password" name="newPassword" required /><br><br>

            <label>Confirm New Password</label><br>
            <input type="password" name="confirmPassword" required /><br><br>

            <button type="submit">Change Password</button>
        </form>

        <p style="color:red">${error}</p>
        <p style="color:green">${success}</p>
    </div>

</div>

</body>
</html>
