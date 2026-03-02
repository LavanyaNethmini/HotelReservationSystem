<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <title>Reservation List | Hotel</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-100 font-sans">

<div class="flex h-screen">

  <jsp:include page="includes/sidebar.jsp" />
    <!-- ===== PAGE CONTENT ===== -->
    <main class="flex-1 overflow-y-auto p-10">

      <div class="bg-white rounded-3xl shadow-xl p-8">

        <!-- FILTER BAR -->
        <form method="get"
              action="${pageContext.request.contextPath}/reservation"
              class="flex flex-wrap gap-4 mb-8">

          <input type="text"
                 name="search"
                 placeholder="Search by guest name or phone"
                 class="px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500">

          <input type="month"
                 name="month"
                 class="px-4 py-3 border rounded-xl focus:ring-2 focus:ring-indigo-500">

          <button type="submit"
                  class="bg-indigo-600 text-white px-6 py-3 rounded-xl hover:bg-indigo-700 transition">
            Filter
          </button>
        </form>

        <!-- TABLE -->
        <div class="overflow-x-auto">
          <table class="min-w-full text-sm text-left border-collapse">

            <thead class="bg-gray-100 text-gray-600 uppercase text-xs">
            <tr>
              <th class="px-6 py-4">ID</th>
              <th class="px-6 py-4">Guest</th>
              <th class="px-6 py-4">Phone</th>
              <th class="px-6 py-4">Room</th>
              <th class="px-6 py-4">Check In</th>
              <th class="px-6 py-4">Check Out</th>
              <th class="px-6 py-4">Status</th>
              <th class="px-6 py-4">Action</th>
            </tr>
            </thead>

            <tbody class="divide-y">

            <c:forEach items="${reservations}" var="r">
              <tr class="hover:bg-gray-50 transition">

                <td class="px-6 py-4">${r.reservationId}</td>
                <td class="px-6 py-4 font-medium">${r.guestName}</td>
                <td class="px-6 py-4">${r.guestPhone}</td>
                <td class="px-6 py-4">${r.roomId}</td>
                <td class="px-6 py-4">${r.checkIn}</td>
                <td class="px-6 py-4">${r.checkOut}</td>

                <td class="px-6 py-4">
                                    <span class="
                                        px-3 py-1 rounded-full text-xs font-semibold
                                        ${r.status == 'CONFIRMED' ? 'bg-green-100 text-green-600' :
                                          r.status == 'CANCELLED' ? 'bg-red-100 text-red-600' :
                                          'bg-yellow-100 text-yellow-600'}">
                                        ${r.status}
                                    </span>
                </td>

                <td class="px-6 py-4">
                  <a class="bg-red-500 text-white px-4 py-2 rounded-lg hover:bg-red-600 transition"
                     href="cancel-reservation?id=${r.reservationId}"
                     onclick="return confirm('Cancel this reservation?');">
                    Cancel
                  </a>
                </td>

              </tr>
            </c:forEach>

            <c:if test="${empty reservations}">
              <tr>
                <td colspan="8"
                    class="text-center py-10 text-gray-400">
                  No reservations found
                </td>
              </tr>
            </c:if>

            </tbody>

          </table>
        </div>

      </div>

    </main>

  </div>

</div>

</body>
</html>