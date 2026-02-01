<!DOCTYPE html>
<html>
<head>
    <title>Verify OTP</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="navbar">
    Account Verification
</div>

<div class="container">
    <div class="card" style="max-width: 400px;">
        <h3>${title}</h3>

        <p>${info}</p>

        <form method="post"
              action="${pageContext.request.contextPath}${postUrl}">
            <input type="hidden" name="email" value="${email}" />

            <label>OTP</label>
            <input type="text" name="otp" required />

            <button type="submit">Verify</button>
        </form>

        <p class="error">${error}</p>
    </div>
</div>

</body>
</html>
