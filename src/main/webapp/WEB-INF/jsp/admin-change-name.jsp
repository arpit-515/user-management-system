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

<div class="container">
    <a class="back-link"
       href="${pageContext.request.contextPath}/admin/dashboard">
        - Back to Dashboard
    </a>

    <div class="card" style="max-width: 500px;">
        <h3>Change Admin Name</h3>

        <form method="post"
              action="${pageContext.request.contextPath}/admin/change-name"
              onsubmit="return confirm('Are you sure you want to change the admin name?');">

            <label>New Admin Name</label><br>
            <input type="text" name="newName" required /><br><br>

            <button type="submit">Update Name</button>
        </form>

        <p style="color:red">${error}</p>
        <p style="color:green">${success}</p>
    </div>

</div>

</body>
</html>
