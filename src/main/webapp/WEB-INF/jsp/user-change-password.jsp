<!DOCTYPE html>
<html>
<head>
    <title>Change Password</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div class="navbar">
    Change Password
</div>

<div class="container center">

    <div class="card" style="max-width: 400px; width: 100%;">

        <a class="back-link"
           href="${pageContext.request.contextPath}/users/dashboard">
            &larr; Back to Dashboard
        </a>

        <div class="section-title">Verify Current Password</div>

        <form method="post"
              action="${pageContext.request.contextPath}/users/change-password">

            <label for="oldPassword">Current Password</label>
            <input type="password" id="oldPassword"
                   name="oldPassword" required>

            <button type="submit">Continue</button>
        </form>

        <p class="error">${error}</p>

    </div>

</div>

</body>
</html>
