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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class BookThisTourController {

    @FXML
    private Text date;
    @FXML
    private Text avalibleSeats;
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
    @FXML
    private Label phoneError;
    @FXML
    private Label firstNameError;
    @FXML
    private Label emailError;
    @FXML
    private Label lastNameError;
    @FXML
    private Label numberOfSeatsError;

    private int numberOfSeats = 0;
    private int totalPrice;
    private int oneSeatPrice;
    private Tour currentTour;
    private DayTourSearch dayTourSearch;

    public void initData(Tour tour) {
        dayTourSearch = new DayTourSearch();
        currentTour = tour; // svo klasinn viti hvaða tour sé verið að bóka
        oneSeatPrice = tour.getPrice();
        totalPrice = oneSeatPrice * numberOfSeats;
        name.setText(tour.getName());
        avalibleSeats.setText(String.valueOf(tour.getSeatsLeft()));
        price.setText(String.valueOf(totalPrice) + " ISK");
        length.setText(tour.getDuration() + " hours");
        date.setText(tour.getDate());
        setErrorVisable(false);
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
    void bookButtonHandler(ActionEvent event) throws IOException {
        setErrorVisable(false);
        if(!validate()) {
            return;
        }
        System.out.println("Creating a new booking...");

        int bookingNr = dayTourSearch.getValidBookingNumber();

        Booking booking = new Booking(bookingNr, currentTour.getId(), currentTour.getDate(), firstName.getText(), lastName.getText(), phone.getText(), numberOfSeats, email.getText());
        if(dayTourSearch.createNewBooking(booking)) {
            System.out.println("Booking created successfully. Booking number: " + bookingNr);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("./View/thankYou.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

            // finnur id á tour sem klikkað er á
            ThankYouController thankYouController = loader.getController();

            Tour tour = dayTourSearch.getTourById(String.valueOf(booking.getTourId()));

            thankYouController.initData(booking, tour);
        } else {
            System.out.println("Error creating booking");
        }

        // TODO: if successful: birta einhvað 'thank you' view með booking númeri

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
                price.setText(String.valueOf(totalPrice) + " ISK");
            } catch (NumberFormatException e) {
                //numberOfSeats = 0;
                seats.setText(String.valueOf(numberOfSeats));
                seats.positionCaret(100);
            }
        }
    }

    private void setErrorVisable(boolean bool) {
        firstNameError.setVisible(bool);
        lastNameError.setVisible(bool);
        emailError.setVisible(bool);
        phoneError.setVisible(bool);
        numberOfSeatsError.setVisible(bool);
    }

    private boolean validate() {
        boolean successful = true;
        if(firstName.getText().isEmpty()) {
            firstNameError.setVisible(true);
            successful = false;
        }
        if(lastName.getText().isEmpty()) {
            lastNameError.setVisible(true);
            successful = false;
        }
        if(!phone.getText().matches("^[0-9]+$")) {
            phoneError.setVisible(true);
            successful = false;
        }
        if(!email.getText().matches(".+@.+")) {
            emailError.setVisible(true);
            successful = false;
        }
        if(numberOfSeats > currentTour.getSeatsLeft() || numberOfSeats <= 0) {
            numberOfSeatsError.setVisible(true);
            successful = false;
        }
        return successful;
    }



}
