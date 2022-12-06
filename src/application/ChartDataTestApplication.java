package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ChartDataTestApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ChartDataTestApplication.class.getResource("/view/ChartDataTest-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setResizable(true);
        primaryStage.setTitle("Chart Data Test");
        primaryStage.getIcons().add(new Image(ChartDataTestApplication.class.getResourceAsStream("/resources/img/LFJD-Analytics-window-icon.png")));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
