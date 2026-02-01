package com.hotel.reservation.domain.model;

public class User {

    private final int userId;
    private final String username;
    private final String password;
    private final String fullName;
    private final String contactNo;
    private final String address;
    private final String role;
    private final String status;
    private final boolean isActive;

    // Constructor is PRIVATE (important for Builder)
    private User(UserBuilder builder) {
        this.userId = builder.userId;
        this.username = builder.username;
        this.password = builder.password;
        this.fullName = builder.fullName;
        this.contactNo = builder.contactNo;
        this.address = builder.address;
        this.role = builder.role;
        this.status = builder.status;
        this.isActive = builder.isActive;
    }

    // Getters only (immutable object)
    public int getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFullName() { return fullName; }
    public String getContactNo() { return contactNo; }
    public String getAddress() { return address; }
    public String getRole() { return role; }
    public String getStatus() { return status; }
    public boolean isActive() { return isActive; }

    // Builder access
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    // ================= BUILDER =================
    public static class UserBuilder {

        private int userId;
        private String username;
        private String password;
        private String fullName;
        private String contactNo;
        private String address;
        private String role;
        private String status = "ACTIVE";   // default
        private boolean isActive = true;     // default

        public UserBuilder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public UserBuilder contactNo(String contactNo) {
            this.contactNo = contactNo;
            return this;
        }

        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }

        public UserBuilder role(String role) {
            this.role = role;
            return this;
        }

        public UserBuilder status(String status) {
            this.status = status;
            return this;
        }

        public UserBuilder isActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public User build() {
            // Basic validation (business rule)
            if (username == null || password == null || role == null) {
                throw new IllegalStateException("Username, password, and role are required");
            }
            return new User(this);
        }
    }
}
