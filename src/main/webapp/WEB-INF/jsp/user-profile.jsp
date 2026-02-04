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

    <div class="card" style="max-width: 600px; width: 100%;">

        <a class="back-link"
           href="${pageContext.request.contextPath}/users/dashboard">
            &larr; Back to Dashboard
        </a>

        <div class="section-title" style="text-align: center;">
            My Profile
        </div>

        <form method="post"
              action="${pageContext.request.contextPath}/users/profile">

            <label for="fullName">Full Name</label>
            <input type="text" id="fullName"
                   name="fullName"
                   value="${user.fullName}"
                   required>

            <label>Email (immutable)</label>
            <input type="email"
                   value="${user.email}"
                   disabled>

            <label>Mobile (immutable)</label>
            <input type="text"
                   value="${user.mobile}"
                   disabled>

            <button type="submit">Update Profile</button>
        </form>

        <p class="success">${success}</p>
        <p class="error">${error}</p>

    </div>

</div>

</body>
</html>
