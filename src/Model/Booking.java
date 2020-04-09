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
}
