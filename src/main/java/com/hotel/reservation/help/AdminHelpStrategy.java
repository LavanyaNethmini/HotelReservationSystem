package com.hotel.reservation.help;

public class AdminHelpStrategy implements HelpStrategy {

    @Override
    public String getHelpContent() {
        return
                "<div class='accordion'>" +

                        "<div class='accordion-item'>" +
                        "<button class='accordion-header'>🔐 Login & Security</button>" +
                        "<div class='accordion-body'>" +
                        "<ul>" +
                        "<li>Login using your staff username and password</li>" +
                        "<li>Never share your credentials</li>" +
                        "<li>Always logout after finishing work</li>" +
                        "</ul>" +
                        "</div>" +
                        "</div>" +

                        "<div class='accordion-item'>" +
                        "<button class='accordion-header'>📅 Reservations</button>" +
                        "<div class='accordion-body'>" +
                        "<ul>" +
                        "<li>Create new reservations for guests</li>" +
                        "<li>Enter guest details accurately</li>" +
                        "<li>Check room availability before confirmation</li>" +
                        "<li>Cancel reservations if required</li>" +
                        "</ul>" +
                        "</div>" +
                        "</div>" +

                        "<div class='accordion-item'>" +
                        "<button class='accordion-header'>💳 Billing</button>" +
                        "<div class='accordion-body'>" +
                        "<ul>" +
                        "<li>Generate bill after reservation confirmation</li>" +
                        "<li>Billing is calculated by number of nights</li>" +
                        "<li>Accept cash or card payments</li>" +
                        "<li>Print bill for the guest</li>" +
                        "</ul>" +
                        "</div>" +
                        "</div>" +

                        "<div class='accordion-item'>" +
                        "<button class='accordion-header'>🔍 Search & Reports</button>" +
                        "<div class='accordion-body'>" +
                        "<ul>" +
                        "<li>Search reservations by guest name or phone</li>" +
                        "<li>Filter reservations by month</li>" +
                        "<li>View reservation history</li>" +
                        "</ul>" +
                        "</div>" +
                        "</div>" +

                        "</div>";
    }

}
