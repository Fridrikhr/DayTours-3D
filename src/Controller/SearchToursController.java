package Controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.Arrays;
import java.util.List;

public class SearchToursController {
    @FXML
    public TableView<ObservableList<String>> resultTable = new TableView<>();

    @FXML
    private Button searchButton;

    public TableColumn<ObservableList<String>, String> column;

    @FXML
    public void initialize() {
        // Setur efstu dálkana í töfluna
        List<String> columnNames = Arrays.asList("Name","Available Seats","Duration","Date","Price");
        for(int i = 0; i < columnNames.size(); i++) {
            final int finalIdx = i;
            column = new TableColumn<>(columnNames.get(i));
            column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(finalIdx)));
            if (i == 0) {
                // Name
                column.setMinWidth(220);
            } else {
                column.setMinWidth(150);
            }
            resultTable.getColumns().add(column);
        }

        // TODO: sækja trips í JSON og setja í töfluna
    }

    @FXML
    void searchButtonHandler(ActionEvent event) {
        System.out.println("Ýtt á takka");
    }

}
