<!DOCTYPE html>
<html>
<head>
    <title>Create User</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="navbar">
    Admin : Create User
</div>

<div class="container center">

    <div class="card" style="max-width: 500px; width: 100%;">

        <a class="back-link"
           href="${pageContext.request.contextPath}/admin/dashboard">
            &larr; Back to Dashboard
        </a>

        <div class="section-title">Create New User</div>
        <p>The user will need to verify their account before login.</p>

        <form method="post"
              action="${pageContext.request.contextPath}/admin/users/create"
              onsubmit="return confirm('Are you sure? The user must verify before login.')">

            <label for="fullName">Full Name</label>
            <input type="text" id="fullName"
                   name="fullName" required />

            <label for="email">Email</label>
            <input type="email" id="email"
                   name="email" required />

            <label for="mobile">Mobile</label>
            <input type="text" id="mobile"
                   name="mobile" required />

            <button type="submit">Create User</button>
        </form>

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>

        <c:if test="${not empty success}">
            <div class="success">${success}</div>
        </c:if>

    </div>

</div>

</body>
</html>
