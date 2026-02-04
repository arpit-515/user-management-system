<!DOCTYPE html>
<html>
<head>
    <title>Forgot Password</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="navbar">
    Forgot Password
</div>

<div class="container center">

    <div class="card" style="max-width: 400px; width: 100%;">

        <div class="section-title">Reset Password</div>

        <form method="post"
              action="${pageContext.request.contextPath}/users/forgot-password">

            <label for="email">Email</label>
            <input type="email" id="email"
                   name="email" required />

            <button type="submit">Send OTP</button>
        </form>

        <p class="error">${error}</p>

    </div>

</div>

</body>
</html>
