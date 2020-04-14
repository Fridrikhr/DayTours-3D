package Model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;


public class DayTourSearch {
    public static ArrayList<Tour> allTours = new ArrayList<Tour>();
    public static ArrayList<Booking> allBookings = new ArrayList<Booking>();

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
        try {
            int n = Integer.parseInt(number);
            for (Booking booking : allBookings) {
                if(booking.getBookingId() == n) return booking;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // If not found
    }

    public int getValidBookingNumber() {
        int rnd = (int) (Math.random()*9000) + 1000;
        for(Booking booking : allBookings) {
            if(rnd == booking.getBookingId()) {
                // try again
                return getValidBookingNumber();
            }
        }
        return rnd;
    }

    public boolean createNewBooking(Booking booking) {
        // insert into bookings.json
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("src/Storage/bookings.json"));
            JSONArray jsonArray = (JSONArray) obj;
            JSONObject newJsonObject = new JSONObject();

            newJsonObject.put("bookingId", booking.getBookingId());
            newJsonObject.put("date", booking.getDate());
            newJsonObject.put("firstName", booking.getFirstName());
            newJsonObject.put("lastName", booking.getLastName());
            newJsonObject.put("phone", booking.getPhone());
            newJsonObject.put("tourId", booking.getTourId());
            newJsonObject.put("seats", booking.getSeats());
            newJsonObject.put("email", booking.getEmail());

            jsonArray.add(newJsonObject);

            FileWriter file = new FileWriter("src/Storage/bookings.json");

            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();

        } catch (Exception e) {
            System.out.println("Failed.");
            return false;
        }

        // insert into allBookings variable
        allBookings.add(booking);
        // return true if successful, else false
        return true;
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

    public ArrayList<Tour> getTrips(){
        return myFilter;
    }

    public void resetFilter() {
        myFilter = allTours;
    }
}
