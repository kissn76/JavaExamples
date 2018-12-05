package threads.countdownTimer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CountdownTimer extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Countdown Timer");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainpane.fxml"));
        Parent root = loader.load();
        // MainViewController controller = loader.getController();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        // primaryStage.setOnHidden(e -> {
        // controller.shutdown();
        // Platform.exit();
        // });
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
