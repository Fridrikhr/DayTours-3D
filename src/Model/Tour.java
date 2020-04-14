package Model;

public class Tour {
    private int id;
    private String name;
    private String category;
    private String description;
    private int duration;
    private String smallDescription;
    private int seats;
    private int seatsLeft;
    private String tourGuide;
    private String date;
    private String location;
    private int price;

    public Tour(int id, String name, String category, String description, int duration, String smallDescription, int seats, int seatsLeft, String tourGuide, String date, String location, int price)
    {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.duration = duration;
        this.smallDescription = smallDescription;
        this.seats = seats;
        this.seatsLeft = seatsLeft;
        this.tourGuide = tourGuide;
        this.date = date;
        this.location = location;
        this.price = price;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }

    public String getDescription() {
        return this.description;
    }


    public void reserve(int seats) {
        this.seatsLeft -= seats;
    }

    public int getDuration() {
        return this.duration;
    }

    public String getSmallDescription() {
        return this.smallDescription;
    }

    public int getSeats() {
        return this.seats;
    }

    public int getSeatsLeft() {
        return this.seatsLeft;
    }

    public String getTourGuide() {
        return this.tourGuide;
    }

    public String getDate() {
        return this.date;
    }

    public String getLocation() {
        return this.location;
    }

    public int getPrice() {
        return this.price;
    }
}
