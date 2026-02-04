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

<div class="container center">

    <div class="card" style="max-width: 500px; width: 100%;">

        <a class="back-link"
           href="${pageContext.request.contextPath}/admin/dashboard">
            &larr; Back to Dashboard
        </a>

        <div class="section-title">Change Admin Password</div>
        <p>Please enter your current password to continue.</p>

        <form method="post"
              action="${pageContext.request.contextPath}/admin/change-password"
              onsubmit="return confirm('Are you sure you want to change the admin password?');">

            <label for="currentPassword">Current Password</label>
            <input type="password" id="currentPassword"
                   name="currentPassword" required />

            <label for="newPassword">New Password</label>
            <input type="password" id="newPassword"
                   name="newPassword" required />

            <label for="confirmPassword">Confirm New Password</label>
            <input type="password" id="confirmPassword"
                   name="confirmPassword" required />

            <button type="submit">Change Password</button>
        </form>

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>

        <c:if test="${not empty success}">
            <div class="success">${success}</div>
        </c:if>

    </div>

</div>

</body>
</html>
