<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html>
<head>
    <title>Help Guide | Hotel Reservation</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-100 font-sans">

<div class="flex h-screen">

    <jsp:include page="includes/sidebar.jsp" />

        <!-- ===== PAGE CONTENT ===== -->
        <main class="flex-1 overflow-y-auto p-10">

            <div class="max-w-4xl mx-auto bg-white shadow-xl rounded-3xl p-10">

                <div class="mb-8">
                    <h1 class="text-3xl font-bold text-gray-800">
                        Help & User Guide
                    </h1>
                    <p class="text-gray-500 mt-3">
                        This guide will help you understand how to use the Hotel Reservation System efficiently.
                    </p>
                </div>

                <!-- Dynamic Help Content -->
                <div class="space-y-6 text-gray-700 leading-relaxed">
                    <%= request.getAttribute("helpContent") %>
                </div>

                <!-- Footer -->
                <div class="mt-12 border-t pt-6 text-sm text-gray-400">
                    Need more help? Please contact the system administrator.
                </div>

            </div>

        </main>

    </div>

</div>

<!-- ===== ACCORDION SCRIPT ===== -->
<script>
    const headers = document.querySelectorAll(".accordion-header");

    headers.forEach(header => {
        header.addEventListener("click", () => {

            const body = header.nextElementSibling;
            const icon = header.querySelector("span");

            body.classList.toggle("hidden");

            icon.textContent = body.classList.contains("hidden") ? "+" : "−";
        });
    });
</script>

</body>
</html>