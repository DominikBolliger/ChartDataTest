package controller;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import logic.DBConnection;
import modell.Article;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChartDataTestController {
    @FXML
    protected LineChart lcDataChart;
    List<XYChart.Series> seriesList = new ArrayList<>();

    @FXML
    protected void initialize() {
        DBConnection con = new DBConnection("jdbc:mysql://localhost:3306/lfjd-analytics", "root", "");
        con.connect();
        con.getAllIOrderedtems();
        con.close();
        transformData();
        fillDataToChart();
    }

    private void transformData() {
        List<Article> articleList = Article.getArticleList();
        int[] monthArr = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Article article = null;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < articleList.size(); j++) {
                article = articleList.get(j);
                if (article.getArticleID() == i + 11) {
                    LocalDate date = LocalDate.parse(article.getArticleBuyDate());
                    int month = date.getMonthValue();
                    monthArr[month-1] += 1;
                }
            }

            XYChart.Series serie = new XYChart.Series();
            for (int k = 0; k < monthArr.length; k++) {
                serie.getData().add(new XYChart.Data(k + 1, monthArr[k]));
            }
            serie.setName(article.getArticleName());
            seriesList.add(serie);
        }
    }

    private void fillDataToChart() {
        NumberAxis xAxis = new NumberAxis(1, 12, 1);
        NumberAxis yAxis = new NumberAxis(0, 400, 50);

        xAxis.setLabel("Month");
        yAxis.setLabel("Article");

        for (XYChart.Series ser:seriesList) {
            lcDataChart.getData().add(ser);
        }
    }
}
