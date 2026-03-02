package com.hotel.reservation.domain.model;

public class Guest {

    private int guestId;
    private String name;
    private String address;
    private String email;
    private String phone;

    public Guest(int guestId, String name, String address, String email, String phone) {
        this.guestId = guestId;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public Guest(String name, String address, String email, String phone) {
        this(0, name, address, email, phone);
    }

    public int getGuestId() { return guestId; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
}
