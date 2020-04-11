package Model;

public class Booking {
    private int tourId;
    private String date;
    private String firstName;
    private String lastName;
    private String phone;
    private int bookingId;
    private int seats;
    private String email;


    public Booking(int bookingId, int tourId, String date, String firstName, String lastName, String phone, int seats, String email) {
        this.tourId = tourId;
        this.date = date;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.bookingId = bookingId;
        this.seats = seats;
        this.email = email;
    }

    public int getTourId() {
        return this.tourId;
    }

    public String getDate() {
        return this.date;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPhone() {
        return this.phone;
    }

    public int getBookingId() {
        return this.bookingId;
    }

    public int getSeats() {
        return this.seats;
    }

    public String getEmail() {
        return this.email;
    }
}
