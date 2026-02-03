<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Make Reservation</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">

    <script>
        function checkPhone() {
            const phone = document.getElementById("phone").value;
            if (phone.length < 9) return;

            fetch("reservation?phone=" + phone)
                .then(res => res.json())
                .then(data => {
                    if (data.name) {
                        document.getElementById("name").value = data.name;
                        document.getElementById("address").value = data.address;
                        document.getElementById("email").value = data.email;
                    }
                });
        }
    </script>
</head>

<body>

<!-- ===== NAVBAR ===== -->
<div class="navbar">
    <div class="logo">Hotel Reservation System</div>
    <div class="nav-right">
        <div class="username">
            <%= session.getAttribute("username") %>
        </div>
        <form action="logout" method="post">
            <button type="submit">Logout</button>
        </form>
    </div>
</div>

<!-- ===== MAIN CONTENT ===== -->
<div class="dashboard-container">
    <div class="dashboard-card">

        <h2>Make Reservation & Billing</h2>

        <!-- ERROR MESSAGE -->
        <% if (request.getAttribute("error") != null) { %>
        <div class="error">
            <%= request.getAttribute("error") %>
        </div>
        <% } %>

        <form method="post" action="reservation">

            <!-- ===== GUEST DETAILS ===== -->
            <h3>Guest Details</h3>

            <div class="input-group">
                <label>Phone (unique)</label>
                <input type="text" id="phone" name="phone"
                       placeholder="Enter phone number"
                       onkeyup="checkPhone()" required>
            </div>

            <div class="input-group">
                <label>Name</label>
                <input type="text" id="name" name="name" required>
            </div>

            <div class="input-group">
                <label>Address</label>
                <input type="text" id="address" name="address" required>
            </div>

            <div class="input-group">
                <label>Email</label>
                <input type="email" id="email" name="email" required>
            </div>

            <!-- ===== RESERVATION DETAILS ===== -->
            <h3>Reservation Details</h3>

            <div class="input-group">
                <label>Room ID</label>
                <input type="number" name="roomId" required>
            </div>

            <div class="input-group">
                <label>Check In</label>
                <input type="date" name="checkIn" required>
            </div>

            <div class="input-group">
                <label>Check Out</label>
                <input type="date" name="checkOut" required>
            </div>

            <!-- ===== BILLING DETAILS ===== -->
            <h3>Billing Details</h3>

            <div class="input-group">
                <label>Payment Method</label>
                <select name="paymentMethod" required>
                    <option value="">Select Payment</option>
                    <option value="CASH">Cash</option>
                    <option value="CARD">Card</option>
                </select>
            </div>

            <br>
            <button class="btn" type="submit">
                Confirm Reservation & Generate Bill
            </button>

        </form>

    </div>
</div>

</body>
</html>
