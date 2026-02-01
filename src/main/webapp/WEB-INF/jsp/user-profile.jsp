<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div class="navbar">
    User Profile
</div>

<div class="container center">

    <div style="width: 100%; max-width: 600px;">

        <a class="back-link"
           href="${pageContext.request.contextPath}/users/dashboard"
           style="display: inline-block; margin-bottom: 10px; font-weight: bold;">
            &larr; Back to Dashboard
        </a>

        <div class="card" style="width: 100%;">

            <h3 style="text-align: center;">My Profile</h3>

            <form method="post"
                  action="${pageContext.request.contextPath}/users/profile">

                <label>Full Name</label>
                <input type="text"
                       name="fullName"
                       value="${user.fullName}"
                       required>

                <label>Email (immutable)</label>
                <input type="email"
                       value="${user.email}"
                       disabled
                       style="background-color: #e9ecef; cursor: not-allowed;">

                <label>Mobile (immutable)</label>
                <input type="text"
                       value="${user.mobile}"
                       disabled
                       style="background-color: #e9ecef; cursor: not-allowed;">

                <button type="submit">Update Profile</button>
            </form>

            <p class="success">${success}</p>
            <p class="error">${error}</p>

        </div>
    </div>

</div>

</body>
</html>