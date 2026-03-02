<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Guest | Hotel Reservation</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-100 font-sans">

<div class="flex h-screen">

    <jsp:include page="includes/sidebar.jsp" />



        <!-- ===== PAGE CONTENT ===== -->
        <main class="flex-1 overflow-y-auto p-10">

            <div class="max-w-3xl mx-auto bg-white shadow-xl rounded-3xl p-10">

                <h1 class="text-2xl font-bold text-gray-800 mb-8">
                    Update Guest Information
                </h1>

                <form method="post" action="guest-edit" class="space-y-6">

                    <input type="hidden" name="guestId" value="${guest.guestId}" />

                    <div>
                        <label class="block text-sm font-medium text-gray-600 mb-2">
                            Name
                        </label>
                        <input type="text"
                               name="name"
                               value="${guest.name}"
                               required
                               class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500 focus:outline-none transition">
                    </div>

                    <div>
                        <label class="block text-sm font-medium text-gray-600 mb-2">
                            Phone
                        </label>
                        <input type="text"
                               name="phone"
                               value="${guest.phone}"
                               required
                               class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500 focus:outline-none transition">
                    </div>

                    <div>
                        <label class="block text-sm font-medium text-gray-600 mb-2">
                            Email
                        </label>
                        <input type="email"
                               name="email"
                               value="${guest.email}"
                               class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500 focus:outline-none transition">
                    </div>

                    <div>
                        <label class="block text-sm font-medium text-gray-600 mb-2">
                            Address
                        </label>
                        <input type="text"
                               name="address"
                               value="${guest.address}"
                               class="w-full px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500 focus:outline-none transition">
                    </div>

                    <div class="pt-4 flex gap-4">

                        <button type="submit"
                                class="bg-indigo-600 text-white px-6 py-3 rounded-xl hover:bg-indigo-700 transition shadow-lg">
                            Update Guest
                        </button>

                        <a href="guests"
                           class="bg-gray-200 text-gray-700 px-6 py-3 rounded-xl hover:bg-gray-300 transition">
                            Cancel
                        </a>

                    </div>

                </form>

            </div>

        </main>

    </div>

</div>

</body>
</html>