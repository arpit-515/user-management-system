<!DOCTYPE html>
<html>
<head>
    <title>Create User</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="navbar">
    Admin – Create User
</div>

<div class="container">
    <a class="back-link"
       href="${pageContext.request.contextPath}/admin/dashboard">
        - Back to Dashboard
    </a>

    <div class="card" style="max-width: 500px;">
        <h3>Create New User</h3>
        <p>The user will need to verify their account before login.</p>

        <form method="post"
              action="${pageContext.request.contextPath}/admin/users/create"
              onsubmit="return confirm('Are you sure? The user must verify before login.')">

            <label>Full Name</label><br>
            <input type="text" name="fullName" required /><br><br>

            <label>Email</label><br>
            <input type="email" name="email" required /><br><br>

            <label>Mobile</label><br>
            <input type="text" name="mobile" required /><br><br>

            <button type="submit">Create User</button>
        </form>

        <p style="color:red;">${error}</p>
        <p style="color:green;">${success}</p>
    </div>

</div>

</body>
</html>
