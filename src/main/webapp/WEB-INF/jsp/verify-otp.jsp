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

<div class="container center">

    <div class="card" style="max-width: 400px; width: 100%;">

        <div class="section-title">
            ${title}
        </div>

        <p>${info}</p>

        <form method="post"
              action="${pageContext.request.contextPath}${postUrl}">

            <input type="hidden" name="email" value="${email}" />

            <label for="otp">OTP</label>
            <input type="text" id="otp"
                   name="otp" required />

            <button type="submit">Verify</button>
        </form>

        <p class="error">${error}</p>

    </div>

</div>

</body>
</html>
