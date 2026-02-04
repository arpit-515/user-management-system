<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div class="navbar">
    Automated Harness Tester : Admin Dashboard
</div>

<div class="container">

    <div class="card">
        <div class="section-title">Welcome, Administrator</div>
        <p>
            You are successfully logged in.
            Use the options below to manage users and system settings.
        </p>
    </div>

    <div class="section-title">Administration</div>

    <div class="grid">

        <div class="card">
            <a href="${pageContext.request.contextPath}/admin/users/create">
                Create User
            </a>
            <p>Create a new system user.</p>
        </div>

        <div class="card">
            <a href="${pageContext.request.contextPath}/admin/users">
                View Users
            </a>
            <p>Activate, deactivate or delete users.</p>
        </div>

        <div class="card">
            <a href="${pageContext.request.contextPath}/admin/audit-logs">
                Audit Logs
            </a>
            <p>View all administrative actions.</p>
        </div>

    </div>

    <div class="section-title">Account Settings</div>

    <div class="grid">

        <div class="card">
            <a href="${pageContext.request.contextPath}/admin/change-name">
                Change Admin Name
            </a>
            <p>Update administrator display name.</p>
        </div>

        <div class="card">
            <a href="${pageContext.request.contextPath}/admin/change-password">
                Change Password
            </a>
            <p>Update administrator password.</p>
        </div>

        <div class="card">
            <a href="${pageContext.request.contextPath}/admin/status">
                Admin Status
            </a>
            <p>Activate or deactivate admin account.</p>
        </div>

    </div>

    <div class="footer">
        <a class="button-danger"
           href="${pageContext.request.contextPath}/admin/logout">
            Logout
        </a>
    </div>

</div>

</body>
</html>
