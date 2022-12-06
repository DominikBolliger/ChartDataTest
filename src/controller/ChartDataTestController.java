package controller;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import logic.DBConnection;

public class ChartDataTestController {
    @FXML
    protected LineChart lcDataChart;
    @FXML
    protected void initialize(){
        DBConnection con = new DBConnection("jdbc:mysql://localhost:3306/lfjd-analytics", "root", "");
        con.connect();
        con.getAllIOrderedtems();
        con.close();
    }
}
