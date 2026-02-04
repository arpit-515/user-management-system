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

<div class="container center">

    <div class="card" style="max-width: 400px; width: 100%;">

        <div class="section-title">Create Password</div>

        <form method="post"
              action="${pageContext.request.contextPath}/users/set-password">

            <input type="hidden" name="email" value="${email}" />

            <label for="password">New Password</label>
            <input type="password" id="password"
                   name="password" required>

            <label for="confirmPassword">Confirm Password</label>
            <input type="password" id="confirmPassword"
                   name="confirmPassword" required>

            <button type="submit">Save Password</button>
        </form>

        <p class="error">${error}</p>

    </div>

</div>

</body>
</html>
