package Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class  Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        // Byrjum alltaf á að opna View/searchTours.fxml sem er aðal útlitið

        Parent root = FXMLLoader.load(getClass().getResource("../View/searchTours.fxml"));
        primaryStage.setTitle("Day tours");
        primaryStage.setScene(new Scene(root, 1068, 511));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
