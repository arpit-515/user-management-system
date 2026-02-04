<!DOCTYPE html>
<html>
<head>
    <title>Reset Password</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="navbar">
    Reset Password
</div>

<div class="container center">

    <div class="card" style="max-width: 400px; width: 100%;">

        <div class="section-title">Set New Password</div>

        <form method="post"
              action="${pageContext.request.contextPath}/users/reset-password">

            <input type="hidden" name="email" value="${email}" />

            <label for="otp">OTP</label>
            <input type="text" id="otp"
                   name="otp" required />

            <label for="password">New Password</label>
            <input type="password" id="password"
                   name="password" required />

            <label for="confirmPassword">Confirm Password</label>
            <input type="password" id="confirmPassword"
                   name="confirmPassword" required />

            <button type="submit">Reset Password</button>
        </form>

        <p class="error">${error}</p>
        <p class="success">${success}</p>
        <p class="success">${info}</p>

    </div>

</div>

</body>
</html>
