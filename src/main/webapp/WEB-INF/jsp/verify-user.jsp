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

<div class="container center">

    <div class="card" style="max-width: 500px; width: 100%;">

        <div class="section-title">
            Verify Your Account
        </div>

        <p>
            Please enter the OTP and set a secure password.
        </p>

        <form method="post"
              action="${pageContext.request.contextPath}/users/verify">

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

            <button type="submit">Verify &amp; Set Password</button>
        </form>

        <p class="error">${error}</p>
        <p class="success">${success}</p>

    </div>

</div>

</body>
</html>
