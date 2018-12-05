package threads.countdownTimer;

import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.util.Duration;

public class CountdownTimerController {

    private Integer timeSeconds;
    private Timeline timeline;

    @FXML
    private Label countdownLabel;

    @FXML
    private TextField countdownTime;

    @FXML
    private Button countdownStartBtn;

    @FXML
    private Button countdownPauseBtn;

    @FXML
    public void initialize() {
        countdownStartBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (timeline != null) {
                    timeline.stop();
                }

                timeSeconds = Integer.parseInt(countdownTime.getText());
                countdownLabel.setText(timeSeconds.toString()); // update timerLabel
                countdownPauseBtn.setText("Pause");

                timeline = new Timeline();
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

                    // KeyFrame event handler
                    public void handle(ActionEvent event) {
                        timeSeconds--;
                        countdownLabel.setText(timeSeconds.toString()); // update timerLabel
                        if (timeSeconds <= 0) {
                            timeline.stop();
                            endDialog();
                        }
                    }
                }));
                timeline.playFromStart();
            }
        });

        countdownPauseBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (timeline != null) {
                    if (timeline.getStatus().equals(Status.RUNNING)) {
                        timeline.pause();
                        countdownPauseBtn.setText("Play");
                    } else {
                        if (timeline.getStatus().equals(Status.PAUSED)) {
                            timeline.play();
                            countdownPauseBtn.setText("Pause");
                        }
                    }
                }

            }
        });
    }

    private void endDialog() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Timer action");
        alert.setHeaderText("End of timer!");
        String s = "End of timer!";
        alert.setContentText(s);
        alert.setResizable(true);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
    }
}
