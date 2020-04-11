package Model;

import Controller.TourController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;


public class DayTourSearch {
    public ArrayList<Tour> allTours = new ArrayList<Tour>();
    public ArrayList<Booking> allBookings = new ArrayList<Booking>();

    private ArrayList<Tour> myFilter = allTours; // The tours we are going to display, matching the search

    public DayTourSearch() {

        JSONParser parser = new JSONParser();
        try {
            /*  Read and parse tours.json into allTours */
            Object toursJSON = parser.parse(new FileReader("src/Storage/tours.json"));
            JSONArray toursArray = (JSONArray) toursJSON;
            for (int i = 0; i < toursArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) toursArray.get(i);

                int id = (int) ((long) jsonObject.get("id"));
                String name = (String) jsonObject.get("Tour Name");
                String category = (String) jsonObject.get("Category");
                String description = (String) jsonObject.get("Description");
                int duration = (int) ((long) jsonObject.get("Duration"));
                String smallDescription = (String) jsonObject.get("Small Description");
                int seats = (int) ((long) jsonObject.get("Seats"));
                int seatsLeft = (int) ((long) jsonObject.get("Seats Left"));
                String tourGuide = (String) jsonObject.get("Tour Guide");
                String date = (String) jsonObject.get("Date");
                String location = (String) jsonObject.get("Location");
                int price = (int) ((long) jsonObject.get("Price"));
                allTours.add(new Tour(id, name, category, description, duration, smallDescription, seats, seatsLeft, tourGuide, date, location, price));
            }

            /*  Read and parse booking.json into allBookings */
            Object bookingJSON = parser.parse(new FileReader("src/Storage/Bookings.json"));
            JSONArray bookingArray = (JSONArray) bookingJSON;
            for (int i = 0; i < bookingArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) bookingArray.get(i);

                int bookingId = (int) ((long) jsonObject.get("bookingId"));
                int tourId = (int) ((long) jsonObject.get("tourId"));
                String date = (String) jsonObject.get("date");
                String firstName = (String) jsonObject.get("firstName");
                String lastName = (String) jsonObject.get("lastName");
                String phone = (String) jsonObject.get("phone");
                int seats = (int) ((long) jsonObject.get("seats"));
                String email = (String) jsonObject.get("email");
                allBookings.add(new Booking(bookingId, tourId, date, firstName, lastName, phone, seats, email));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Booking searchBookingNumber(String number) {
        int n = Integer.parseInt(number);
        for (Booking booking : allBookings) {
            if(booking.getBookingId() == n) return booking;
        }
        return null; // If not found
    }

    public ArrayList<Tour> getAllTours() {
        return allTours;
    }

    public Tour getTourById(String id) {
        for (Tour tour : allTours) {
            if (tour.getId() == Integer.parseInt(id)) {
                return tour;
            }
        }
        return null;
    }

    public void searchName(String s) {
        ArrayList<Tour> filtered  = new ArrayList<Tour>();

        for (int i = 0; i < myFilter.size(); i++) {
            String name = myFilter.get(i).getName();
            String description = myFilter.get(i).getDescription();
            if (name.toLowerCase().contains(s.toLowerCase()) || description.toLowerCase().contains(s.toLowerCase())) {
                filtered.add(myFilter.get(i));
            }
        }

        myFilter = filtered;
    }

    public void searchSeats(int x) {
        ArrayList<Tour> filtered  = new ArrayList<Tour>();

        for (int i = 0; i < myFilter.size(); i++) {
            int availableSeats = myFilter.get(i).getSeatsLeft();
            if (availableSeats >= x) filtered.add(myFilter.get(i));
        }
        myFilter = filtered;
    }

    public void searchMinPrice(int s){
        ArrayList<Tour> filtered  = new ArrayList<Tour>();

        for (int i = 0; i < myFilter.size(); i++) {
            int higher = myFilter.get(i).getPrice();
            if (s <= higher) filtered.add(myFilter.get(i));
        }

        myFilter = filtered;
    }

    public void searchMaxPrice(int s) {
        ArrayList<Tour> filtered  = new ArrayList<Tour>();

        for (int i = 0; i < myFilter.size(); i++) {
            int lower = myFilter.get(i).getPrice();
            if (s >= lower) filtered.add(myFilter.get(i));
        }

        myFilter = filtered;
    }

    public void searchPriceSpace (int min, int max) {
        ArrayList<Tour> filtered  = new ArrayList<Tour>();

        for (int i = 0; i < myFilter.size(); i++) {
            int x = myFilter.get(i).getPrice();
            if ((min <= x) && (x <= max)) filtered.add(myFilter.get(i));
        }

        myFilter = filtered;
    }

    public void searchDates(LocalDate start, LocalDate end) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Tour> filtered = new ArrayList<Tour>();
        Date inputStart = sdf.parse(start.toString());
        Date inputEnd = sdf.parse(end.toString());

        for (int i = 0; i < myFilter.size(); i++) {
            String date = myFilter.get(i).getDate();
            Date tripDate = sdf.parse(date);
            if(inputStart.before(tripDate) && inputEnd.after(tripDate) || inputStart.equals(tripDate) || inputEnd.equals(tripDate)){
                filtered.add(myFilter.get(i));
            }
        }
        myFilter = filtered;
    }

    public ArrayList<Tour> getTrips(){
        return myFilter;
    }

    public void resetFilter() {
        myFilter = allTours;
    }
}
