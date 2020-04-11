package Controller;

import Model.Booking;
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
    private Text name1;
    @FXML
    private TextField bookingNr;

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
/*
    @FXML
    void searchbookingnrButtonHandler(ActionEvent event) {
        Pattern p = Pattern.compile("[a-z]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(bookingNr.getText());
        if(!m.find()){
            Booking book = Tour.getBookingById(Integer.parseInt(bookingNr.getText()));
            System.out.println(book == null);
            if(book == null){
                bookingNr.clear();
                bookingNr.setPromptText("No booking exists with that booking number");
            } else {
                trip = dayTours.getTripById(Integer.toString(book.getID()));
                locationBooked.setText(trip.getLocation());
                tripBooked.setText(trip.getName());
                seatsBooked.setText(Integer.toString(book.getSeats()));
                dateBooked.setText(book.getDate());
                buyerBooked.setText(book.getFullName());
                totalPrice.setText(trip.getPrice() * book.getSeats() + "kr.");
                aboutButton.setDisable(false);

            }
        } else if (bookingNr.getText().length() != 0) {
            bookingNr.clear();
            bookingNr.setPromptText("Booking number can not be empty");
        }


    }
*/
}
