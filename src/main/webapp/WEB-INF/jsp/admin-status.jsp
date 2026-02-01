<!DOCTYPE html>
<html>
<head>
    <title>Admin Status</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div class="navbar">
    Admin : Account Status
</div>

<div class="container">
    <a class="back-link"
       href="${pageContext.request.contextPath}/admin/dashboard">
        ← Back to Dashboard
    </a>

    <div class="card" style="max-width: 500px;">
        <h3>Admin Account Status</h3>

        <p>
            Current Status:
            <strong>
                <%
                    Boolean active = (Boolean) request.getAttribute("active");
                    if (active != null && active) {
                        out.print("ACTIVE");
                    } else {
                        out.print("INACTIVE");
                    }
                %>
            </strong>
        </p>

        <form method="post"
              action="${pageContext.request.contextPath}/admin/status"
              onsubmit="return confirm('Are you sure you want to change admin status?');">
            <button type="submit">
                <%
                    if (active != null && active) {
                        out.print("Deactivate Admin");
                    } else {
                        out.print("Activate Admin");
                    }
                %>
            </button>
        </form>
    </div>

</div>

</body>
</html>
