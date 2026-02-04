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

<div class="container center">

    <div class="card" style="max-width: 500px; width: 100%; text-align: center;">

        <div class="section-title">
            Account Not Verified
        </div>

        <p>
            Your account could not be verified or the verification link has expired.
        </p>

        <a class="back-link"
           href="${pageContext.request.contextPath}/users/login">
            &larr; Go to Login Page
        </a>

    </div>

</div>

</body>
</html>
