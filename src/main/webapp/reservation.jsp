<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Make Reservation | Hotel</title>

    <script src="https://cdn.tailwindcss.com"></script>

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

<body class="bg-gray-100 font-sans">

<div class="flex h-screen">

    <jsp:include page="includes/sidebar.jsp" />

        <!-- ===== PAGE CONTENT ===== -->
        <main class="flex-1 overflow-y-auto p-10">

            <div class="max-w-5xl mx-auto bg-white shadow-xl rounded-3xl p-10">

                <h2 class="text-2xl font-bold text-gray-800 mb-8">
                    Make Reservation & Billing
                </h2>

                <% if (request.getAttribute("error") != null) { %>
                <div class="bg-red-100 text-red-600 p-4 rounded-lg mb-6">
                    <%= request.getAttribute("error") %>
                </div>
                <% } %>

                <form method="post" action="reservation" class="space-y-10">

                    <!-- Guest Details -->
                    <div>
                        <h3 class="text-lg font-semibold text-indigo-600 mb-4">
                            Guest Details
                        </h3>

                        <div class="grid grid-cols-1 md:grid-cols-2 gap-6">

                            <input type="text" id="phone" name="phone"
                                   placeholder="Phone"
                                   onkeyup="checkPhone()" required
                                   class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500">


                            <input type="text" id="name" name="name"
                                   placeholder="Name" required
                                   class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500">

                            <input type="text" id="address" name="address"
                                   placeholder="Address" required
                                   class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500">

                            <input type="email" id="email" name="email"
                                   placeholder="Email" required
                                   pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"
                                   title="Please enter a valid email address"
                                   class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500">
                        </div>
                    </div>

                    <!-- Reservation -->
                    <div>
                        <h3 class="text-lg font-semibold text-indigo-600 mb-4">
                            Reservation Details
                        </h3>

                        <div class="grid grid-cols-1 md:grid-cols-3 gap-6">

                            <input type="number" name="roomId"
                                   placeholder="Room ID" required
                                   class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500">

                            <input type="date" name="checkIn" required
                                   class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500">

                            <input type="date" name="checkOut" required
                                   class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500">
                        </div>
                    </div>

                    <!-- Billing -->
                    <div>
                        <h3 class="text-lg font-semibold text-indigo-600 mb-4">
                            Billing Details
                        </h3>

                        <select name="paymentMethod" required
                                class="w-full max-w-sm px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500">
                            <option value="">Select Payment</option>
                            <option value="CASH">Cash</option>
                            <option value="CARD">Card</option>
                        </select>
                    </div>

                    <button type="submit"
                            class="bg-indigo-600 text-white px-8 py-3 rounded-xl font-semibold hover:bg-indigo-700 transition shadow-lg">
                        Confirm Reservation & Generate Bill
                    </button>

                </form>

            </div>

        </main>

    </div>

</div>

</body>
</html>