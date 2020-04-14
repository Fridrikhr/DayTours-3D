package Controller;

import Model.DayTourSearch;
import Model.Tour;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchToursController {
    @FXML
    private DatePicker startDateChoice;
    @FXML
    private DatePicker endDateChoice;
    @FXML
    private ChoiceBox<?> locationChoice;
    @FXML
    private ChoiceBox<?> interestChoice;
    @FXML
    private TextField maxPriceInput;
    @FXML
    private TextField minPriceInput;
    @FXML
    private TextField seatsInput;
    @FXML
    private TextField tourNameInput;

    @FXML
    private Button searchButton;
    @FXML
    private Button myBookingsButton;


    @FXML
    public TableView<ObservableList<String>> resultTable = new TableView<>();

    public TableColumn<ObservableList<String>, String> column;

    private DayTourSearch dayTourSearch;

    private ArrayList<Tour> filteredTours;

    private Tour testTour = new Tour(1, "Nafn á tour", "nature", "description", 6, "small description", 10, 8, "Bjöggi", "12/4/2020", "Reykjavík", 10000);


    @FXML
    public void initialize() {
        dayTourSearch = new DayTourSearch();

        // Setur efstu röðina í töfluna
        List<String> columnNames = Arrays.asList("Name","Available Seats","Duration","Date","Price", "id");
        for(int i = 0; i < columnNames.size(); i++) {
            final int finalIdx = i;
            column = new TableColumn<>(columnNames.get(i));
            column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(finalIdx)));
            if (i == 0) {
                // Name
                column.setMinWidth(245);
            } else if(i == 5){
                // id
                column.setVisible(false);
            } else {
                column.setMinWidth(100);
            }
            resultTable.getColumns().add(column);
        }
        getTrips();

    }

    private void getTrips() {
        // sækjum tours sem á að birta í töflu
        filteredTours = dayTourSearch.getAllTours();

        for(Tour tour : filteredTours) {
            ObservableList<String> row = FXCollections.observableArrayList();

            row.add(tour.getName());
            row.add(tour.getSeatsLeft() + "/" + tour.getSeats());
            row.add(tour.getDuration() + " hours");
            row.add(tour.getDate());
            row.add(Integer.toString(tour.getPrice()));
            row.add(tour.getDate());
            row.add(Integer.toString(tour.getId()));

            resultTable.getItems().add(row);
        }
    }

    @FXML
    public void onClickTable(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getClickCount() == 2 && resultTable.getSelectionModel().getSelectedItem() != null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("./View/tour.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

            // finnur id á tour sem klikkað er á
            String id = resultTable.getSelectionModel().getSelectedItem().get(6);
            System.out.println("id á tour: " + id);
            Tour selected = dayTourSearch.getTourById(id);
            TourController tourController = loader.getController();
            tourController.initData(selected);
        }
    }

    @FXML
    void myBookingsButtonHandler(ActionEvent event) throws IOException {
        System.out.println("my bookings");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("./View/booking.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    void searchButtonHandler(ActionEvent event) {
        dayTourSearch.resetFilter();

        if(!tourNameInput.getText().equals("")) {
            dayTourSearch.searchName(tourNameInput.getText());
        }
        if(!seatsInput.getText().equals("")) {
            dayTourSearch.searchSeats(Integer.valueOf(seatsInput.getText()));
        }
        if(!minPriceInput.getText().equals("") && maxPriceInput.getText().equals("")) {
            dayTourSearch.searchMinPrice(Integer.valueOf(minPriceInput.getText()));
        }
        if(!maxPriceInput.getText().equals("") && minPriceInput.getText().equals("")) {
            dayTourSearch.searchMaxPrice(Integer.valueOf(maxPriceInput.getText()));
        }
        if(!minPriceInput.getText().equals("") && !maxPriceInput.getText().equals("")) {
            dayTourSearch.searchPriceSpace(Integer.valueOf(minPriceInput.getText()), Integer.valueOf(maxPriceInput.getText()));
        }
            //kannski setja filteredTrips = dayTourSearch.getTrips()
        displayTrips(dayTourSearch.getTrips());
    }
    public void resetTable() {
        resultTable.getItems().clear();
    }

    public void displayTrips(ArrayList<Tour> filteredTours) {
        resetTable();
        for(Tour tour : filteredTours) {
            ObservableList<String> row = FXCollections.observableArrayList();

            row.add(tour.getName());
            row.add(tour.getSeatsLeft() + "/" + tour.getSeats());
            row.add(tour.getDuration() + " hours");
            row.add(tour.getDate());
            row.add(Integer.toString(tour.getPrice()));
            row.add(tour.getDate());
            row.add(Integer.toString(tour.getId()));

            resultTable.getItems().add(row);
        }
    }
}
