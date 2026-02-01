🧩 User Creation & Management System

A secure, role-based user management system built using Spring Boot, implementing admin-controlled user lifecycle, OTP-based verification, and audit logging.

This project demonstrates backend fundamentals required for enterprise Java applications, including authentication, authorization, data integrity, and security best practices.

🚀 Features
👤 Admin Module

Admin authentication (session-based)

Create users with email & mobile

Activate / deactivate users

Soft delete users

Update user details (email/mobile re-verification enforced)

View all users

View audit logs for all admin actions

🙋 User Module

User login with captcha

Dual OTP verification:

Email verification

Mobile verification

Secure password setup (bcrypt)

Password change with OTP

Profile view & update (immutable fields protected)
Session-based dashboard access

🔐 Security

Passwords hashed using BCrypt

OTP-based verification for sensitive actions

Captcha protection on login

Session validation on all protected routes

Soft deletes instead of destructive deletes

Audit logging for all admin actions

🛠️ Tech Stack

Backend: Java 17, Spring Boot

Developed as part of an internship-level backend system to demonstrate real-world authentication and user lifecycle management.
