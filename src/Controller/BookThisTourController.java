package Controller;

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
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class BookThisTourController {

    @FXML
    private Text date;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phone;
    @FXML
    private Button bookButton;
    @FXML
    private Text price;
    @FXML
    private Button backButton;
    @FXML
    private Text name;
    @FXML
    private Text length;
    @FXML
    private TextField seats;
    @FXML
    private TextField email;

    private int numberOfSeats = 0;
    private int totalPrice;
    private int oneSeatPrice;

    public void initData(Tour tour) {
        oneSeatPrice = tour.getPrice();
        totalPrice = oneSeatPrice * numberOfSeats;
        name.setText(tour.getName());
        price.setText(String.valueOf(totalPrice));
        length.setText(tour.getDuration() + " hours");
        date.setText(tour.getDate());
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
    void bookButtonHandler(ActionEvent event) {
        System.out.println("New booking");
        // skrifa í bookings.json og birta einhvað 'thank you' view með booking númeri
    }

    @FXML
    void onChangeSeats(KeyEvent event) {
        String input = seats.getText();
        if(input.length() == 0) {
            price.setText("0");
            numberOfSeats = 0;
        } else {
            try {
                numberOfSeats = Integer.parseInt(input);
                totalPrice = numberOfSeats * oneSeatPrice;
                price.setText(String.valueOf(totalPrice));
            } catch (NumberFormatException e) {
                //numberOfSeats = 0;
                seats.setText(String.valueOf(numberOfSeats));
                seats.positionCaret(100);
            }
        }
    }





}
