package Model;

import org.json.simple.parser.JSONParser;

import java.util.ArrayList;

public class DayTourSearch {
    public ArrayList<Tour> tour = new ArrayList<Tour>();

    public DayTourSearch() {
        JSONParser parser = new JSONParser();
    }
}
