<!DOCTYPE html>
<html>
<head>
    <title>Change Admin Name</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="navbar">
    Admin : Change Name
</div>

<div class="container center">

    <div class="card" style="max-width: 500px; width: 100%;">

        <a class="back-link"
           href="${pageContext.request.contextPath}/admin/dashboard">
            &larr; Back to Dashboard
        </a>

        <div class="section-title">Change Admin Name</div>

        <form method="post"
              action="${pageContext.request.contextPath}/admin/change-name"
              onsubmit="return confirm('Are you sure you want to change the admin name?');">

            <label for="newName">New Admin Name</label>
            <input type="text" id="newName" name="newName" required />

            <button type="submit">Update Name</button>
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
