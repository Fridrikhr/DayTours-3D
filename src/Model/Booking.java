package Model;

public class Booking {
    private int tourId;
    private String date;
    private String firstName;
    private String lastName;
    private String phonenumber;
    private int id;
    private int seats;
    private String email;


    public Booking(int tourId, String date, String firstName, String lastName, String phonenumber, int id, int seats, String email) {
        this.tourId = tourId;
        this.date = date;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phonenumber = phonenumber;
        this.id = id;
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

    public String getPhonenumber() {
        return this.phonenumber;
    }

    public int getId() {
        return this.id;
    }

    public int getSeats() {
        return this.seats;
    }

    public String getEmail() {
        return this.email;
    }
}
