<!DOCTYPE html>
<html>
<head>
    <title>Reset Password</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="navbar">Reset Password</div>

<div class="container">
    <div class="card" style="max-width:400px;">
        <h3>Set New Password</h3>

        <form method="post"
              action="${pageContext.request.contextPath}/users/reset-password">

            <input type="hidden" name="email" value="${email}" />

            <label>OTP</label><br>
            <input type="text" name="otp" required /><br><br>

            <label>New Password</label><br>
            <input type="password" name="password" required /><br><br>

            <label>Confirm Password</label><br>
            <input type="password" name="confirmPassword" required /><br><br>

            <button type="submit">Reset Password</button>
        </form>

        <p style="color:red">${error}</p>
        <p style="color:green">${success}</p>
        <p style="color:green;">
            ${info}
        </p>
    </div>
</div>

</body>
</html>
