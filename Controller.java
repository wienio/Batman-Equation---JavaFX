package sample;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class Controller {
    private double MIN=-8;
    private double MAX=8;

    @FXML
    private Canvas canvas;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private TextField textField;

    @FXML
    private Label resultLabel;

    private DrawerTask task;

    @FXML
    private void handleRunStart() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,gc.getCanvas().getWidth(),gc.getCanvas().getHeight());
        double N;
        if (textField.getText().length() > 0) {
            try {
                N = Double.parseDouble(textField.getText());
                if (N > 0) {
                    task = new DrawerTask(gc, N);
                    progressBar.progressProperty().bind(task.progressProperty());
                    task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                        @Override
                        public void handle(WorkerStateEvent event) {
                            resultLabel.setText(Double.toString((Double) task.getValue()));
                        }
                    });
                    new Thread(task).start();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Za mała wartość!");
                    alert.setHeaderText("Proszę podać właściwą wartość!");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Podano nieprwidłową wartość!");
                alert.setHeaderText("Proszę podać właściwą wartość!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void handleSTOP() {
        if (task!= null) {
            task.cancel();
        }
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Label getResultLabel() {
        return resultLabel;
    }
}