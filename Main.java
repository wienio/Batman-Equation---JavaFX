package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MonteCarloOverview.fxml"));
        primaryStage.setTitle("Monte Carlo");
        primaryStage.getIcons().add(new Image("https://cdn2.iconfinder.com/data/icons/mathematics-geometry/154/math-function-mathematical-integral-128.png"));
        primaryStage.setScene(new Scene(root, 599, 593));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
