package Model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;


public class DayTourSearch {
    public ArrayList<Tour> tour = new ArrayList<Tour>();
    private ArrayList<Tour> filtered = tour;

    public DayTourSearch() {

        JSONParser parser = new JSONParser();
        try {
            Object tours = parser.parse(new FileReader("src/Storage/tours.json"));
            JSONArray toursArray = (JSONArray) tours;

            for (int i = 0; i < toursArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) toursArray.get(i);

                int id = (int)((long)jsonObject.get("id"));
                String name = (String) jsonObject.get("name");
                String category = (String) jsonObject.get("category");
                String description = (String) jsonObject.get("description");
                int duration = (int)((long)jsonObject.get("duration"));
                String smallDescription = (String) jsonObject.get("smallDescription");
                int seats = (int)((long)jsonObject.get("seats"));
                int seatsLeft = (int)((long)jsonObject.get("seatsLeft"));
                String tourGuide = (String) jsonObject.get("tourGuide");
                String date = (String) jsonObject.get("date");
                String location = (String) jsonObject.get("location");
                int price = (int)((long)jsonObject.get("price"));

                tour.add(new Tour(id, name, category, description, duration, smallDescription, seats, seatsLeft, tourGuide, date, location, price));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
