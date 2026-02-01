<!DOCTYPE html>
<html>
<head>
    <title>Set Password</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="navbar">
    Set Account Password
</div>

<div class="container">
    <div class="card" style="max-width:400px;">
        <h3>Create Password</h3>

        <form method="post"
              action="${pageContext.request.contextPath}/users/set-password">

            <input type="hidden" name="email" value="${email}"/>

            <label>New Password</label>
            <input type="password" name="password" required>

            <label>Confirm Password</label>
            <input type="password" name="confirmPassword" required>

            <button type="submit">Save Password</button>
        </form>

        <p class="error">${error}</p>
    </div>
</div>

</body>
</html>
