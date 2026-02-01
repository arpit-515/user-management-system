<!DOCTYPE html>
<html>
<head>
    <title>Verification Error</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div class="navbar">
    Account Verification
</div>

<div class="container">

    <div class="card" style="max-width: 500px;">
        <h3>Account Not Verified</h3>
        <p>Your account could not be verified or the link has expired.</p>

        <a href="${pageContext.request.contextPath}/users/verify/email?email=${email}">
            Try Verification Again
        </a>
    </div>

</div>

</body>
</html>
