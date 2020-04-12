package Controller;

import Model.Booking;
import Model.DayTourSearch;
import Model.Tour;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.IOException;

public class BookingController {

    @FXML
    private Button searchButton;
    @FXML
    private Text name11;
    @FXML
    private Button backButton;
    @FXML
    private Text name;
    @FXML
    private Text seatsBooked;
    @FXML
    private Text namePerson;
    @FXML
    private Text date;
    @FXML
    private Text price;
    @FXML
    private TextField bookingNr;
    private DayTourSearch dayTourSearch;

    @FXML
    public void initialize() {
        dayTourSearch = new DayTourSearch();
    }

    @FXML
    void backButtonHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("./View/searchTours.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    void searchbookingnrButtonHandler(ActionEvent event) {
        Booking booking = dayTourSearch.searchBookingNumber(bookingNr.getText());
        if(booking == null) {
            bookingNr.setText("");
            bookingNr.setPromptText("Booking number not found");
            name.setText("");
            seatsBooked.setText("");
            namePerson.setText("");
            date.setText("");
            price.setText("");
        } else {
            // ná í upplýsingar um tourinn
            String tourId = String.valueOf(booking.getTourId());
            Tour tour = dayTourSearch.getTourById(tourId);

            name.setText(tour.getName());
            seatsBooked.setText(String.valueOf(booking.getSeats()));
            namePerson.setText(booking.getFullName());
            date.setText(tour.getDate());
            price.setText(tour.getPrice()*booking.getSeats() + "isk.");
        }
    }

}
