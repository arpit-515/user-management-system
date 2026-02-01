<!DOCTYPE html>
<html>
<head>
    <title>Forgot Password</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="navbar">Forgot Password</div>

<div class="container">
    <div class="card" style="max-width:400px;">
        <h3>Reset Password</h3>

        <form method="post"
              action="${pageContext.request.contextPath}/users/forgot-password">
            <label>Email</label><br>
            <input type="email" name="email" required /><br><br>
            <button type="submit">Send OTP</button>
        </form>

        <p style="color:red">${error}</p>
    </div>
</div>

</body>
</html>
