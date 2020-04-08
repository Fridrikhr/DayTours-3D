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

    @FXML
    public void initialize() {
        // Setur efstu röðina í töfluna
        List<String> columnNames = Arrays.asList("Name","Available Seats","Duration","Date","Price", "id");
        for(int i = 0; i < columnNames.size(); i++) {
            final int finalIdx = i;
            column = new TableColumn<>(columnNames.get(i));
            column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(finalIdx)));
            if (i == 0) {
                // Name
                column.setMinWidth(220);
            } else if(i == 6){
                // id
                column.setMaxWidth(0);
            } else {
                column.setMinWidth(150);
            }
            resultTable.getColumns().add(column);
        }

        getTrips();

    }

    private void getTrips() {
        Tour testTour = new Tour(1, "test name", "nature", "description", 6, "small description", 10, 10, "Bjöggi", "12/4/2020", "Reykjavík", 10000);

        ObservableList<String> row = FXCollections.observableArrayList();

        row.add(testTour.getName());
        row.add(Integer.toString(testTour.getSeatsLeft()));
        row.add(Integer.toString(testTour.getDuration()));
        row.add(testTour.getDate());
        row.add(Integer.toString(testTour.getPrice()));
        row.add(testTour.getDate());
        row.add(Integer.toString(testTour.getId()));

        resultTable.getItems().add(row);
    }

    @FXML
    public void onClickTable(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getClickCount() == 2){
            // finnur id á tour sem klikkað er á
            String id = resultTable.getSelectionModel().getSelectedItem().get(6);
            System.out.println("id á tour: " + id);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("./View/tour.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
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
        System.out.println("Ýtt á takka, Kiddi,Frikki,almar were here");

    }

}
