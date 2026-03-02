package com.hotel.reservation.help;

public class StaffHelpStrategy implements HelpStrategy {

    @Override
    public String getHelpContent() {

        return

                "<div class='space-y-6'>" +
                // Login
                "<div class='bg-gray-50 rounded-2xl shadow-sm'>" +
                        "<button class='accordion-header w-full text-left px-6 py-4 font-semibold text-indigo-600 flex justify-between items-center'>" +
                        "<i class='fa-solid fa-lock text-indigo-500 mr-3'></i>" +
                        "Login & Security <span>+</span>" +
                        "</button>" +
                        "<div class='accordion-body hidden px-6 pb-6 text-gray-600'>" +
                        "<ul class='list-disc ml-6 space-y-2'>" +
                        "<li>Login using your staff username and password</li>" +
                        "<li>Never share your credentials</li>" +
                        "<li>Always logout after finishing work</li>" +
                        "</ul>" +
                        "</div>" +
                        "</div>" +

                        // Reservations
                        "<div class='bg-gray-50 rounded-2xl shadow-sm'>" +
                        "<button class='accordion-header w-full text-left px-6 py-4 font-semibold text-indigo-600 flex justify-between items-center'>" +
                        "<i class='fa-solid fa-calendar-days text-indigo-500 mr-3'></i>" +
                        "Reservations <span>+</span>" +
                        "</button>" +
                        "<div class='accordion-body hidden px-6 pb-6 text-gray-600'>" +
                        "<ul class='list-disc ml-6 space-y-2'>" +
                        "<li>Create new reservations for guests</li>" +
                        "<li>Enter guest details accurately</li>" +
                        "<li>Check room availability before confirmation</li>" +
                        "<li>Cancel reservations if required</li>" +
                        "</ul>" +
                        "</div>" +
                        "</div>" +

                        // Billing
                        "<div class='bg-gray-50 rounded-2xl shadow-sm'>" +
                        "<button class='accordion-header w-full text-left px-6 py-4 font-semibold text-indigo-600 flex justify-between items-center'>" +
                        "<i class='fa-solid fa-credit-card text-indigo-500 mr-3'></i>" +
                        "Billing <span>+</span>" +
                        "</button>" +
                        "<div class='accordion-body hidden px-6 pb-6 text-gray-600'>" +
                        "<ul class='list-disc ml-6 space-y-2'>" +
                        "<li>Generate bill after reservation confirmation</li>" +
                        "<li>Billing is calculated by number of nights</li>" +
                        "<li>Accept cash or card payments</li>" +
                        "<li>Print bill for the guest</li>" +
                        "</ul>" +
                        "</div>" +
                        "</div>" +


                        "</div>";
    }

}