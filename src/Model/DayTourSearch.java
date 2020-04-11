package Model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;


public class DayTourSearch {
    public ArrayList<Tour> allTours = new ArrayList<Tour>();

    private ArrayList<Tour> myFilter = allTours;

    public DayTourSearch() {

        JSONParser parser = new JSONParser();
        try {
            Object tours = parser.parse(new FileReader("src/Storage/tours.json"));
            JSONArray toursArray = (JSONArray) tours;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Tour> getAllTours() {
        allTours.remove(1);
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
}
