<!DOCTYPE html>
<html>
<head>
    <title>User Dashboard</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div class="navbar">
    Automated Harness Tester : User Dashboard
</div>

<div class="container">

    <div class="card">
        <h3>Welcome</h3>
        <p>You are successfully logged in. Use the options below to manage your account.</p>
    </div>

    <br/>

    <div class="section-title">Account Settings</div>

    <div class="grid">

        <div class="card">
            <a href="${pageContext.request.contextPath}/users/change-password">
                Change Password
            </a>
            <p>Update your account password securely.</p>
        </div>

        <div class="card">
            <a href="${pageContext.request.contextPath}/users/profile">
                View / Update Profile
            </a>
            <p>View and update your personal information.</p>
        </div>

        <div class="card">
            <a class="button-danger"
               href="${pageContext.request.contextPath}/users/logout">
                Logout
            </a>
            <p>End your current session.</p>
        </div>

    </div>

</div>

</body>
</html>
