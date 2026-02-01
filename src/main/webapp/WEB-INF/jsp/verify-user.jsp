<!DOCTYPE html>
<html>
<head>
    <title>Verify Account</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div class="navbar">
    Account Verification
</div>

<div class="container">

    <div class="card" style="max-width: 500px;">
        <h3>Verify Your Account</h3>
        <p>Please enter the OTP and set a secure password.</p>

        <form method="post"
              action="${pageContext.request.contextPath}/users/verify">

            <input type="hidden" name="email" value="${email}" />

            <label>OTP</label><br>
            <input type="text" name="otp" required /><br><br>

            <label>New Password</label><br>
            <input type="password" name="password" required /><br><br>

            <label>Confirm Password</label><br>
            <input type="password" name="confirmPassword" required /><br><br>

            <button type="submit">Verify & Set Password</button>
        </form>

        <p style="color:red">${error}</p>
        <p style="color:green">${success}</p>
    </div>

</div>

</body>
</html>
