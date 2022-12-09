package controller;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import logic.DBConnection;
import modell.Article;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChartDataTestController {
    @FXML
    protected LineChart lcDataChart;
    @FXML
    protected NumberAxis xAxis;
    @FXML
    protected NumberAxis yAxis;

    List<XYChart.Series> seriesList = new ArrayList<>();

    String[] articles = new String[]{
            "Underpants",
            "Socks",
            "Shoes",
            "T-Shirt",
            "Rain Jacket",
            "Jeans",
            "Sweatpants",
            "Winter Jacket",
            "Pullover",
            "Gloves",
            "Winter hat",
            "Jeans Shorts",
            "Swim Trunks"
    };

    @FXML
    protected void initialize() {
        DBConnection con = new DBConnection("jdbc:mysql://localhost:3306/lfjd-analytics", "root", "");
        con.connect();
        con.getAllIOrderedtems();
        con.close();
        transformDataSingle();
        fillDataToChart();
    }

    private void transformData() {
        List<Article> articleList = Article.getArticleList();

        Article article = null;
        String articleName = "";
        for (int i = 0; i < articles.length; i++) {
            int[] monthArr = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            for (int j = 0; j < articleList.size(); j++) {
                article = articleList.get(j);
                if (article.getArticleID() == i + 1) {
                    LocalDate date = LocalDate.parse(article.getArticleBuyDate());
                    int month = date.getMonthValue();
                    monthArr[month - 1] += 1;
                }
            }

            XYChart.Series serie = new XYChart.Series();
            for (int k = 0; k < monthArr.length; k++) {
                serie.getData().add(new XYChart.Data(k + 1, monthArr[k]));
            }
            serie.setName(articles[i]);
            seriesList.add(serie);
        }
    }

    private void transformDataSingle() {
        List<Article> articleList = Article.getArticleList();
        Article article = null;
        int[] monthArr = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int j = 0; j < articleList.size(); j++) {
            article = articleList.get(j);
            if (article.getArticleID() == 6) {
                LocalDate date = LocalDate.parse(article.getArticleBuyDate());
                int month = date.getMonthValue();
                monthArr[month - 1] += 1;
            }
        }

        XYChart.Series serie = new XYChart.Series();

        for (int i = 0; i < monthArr.length; i++) {
            serie.getData().add(new XYChart.Data(i + 1, monthArr[i]));
        }

        serie.setName(article.getArticleName());
        seriesList.add(serie);

    }

    private void fillDataToChart() {

        for (XYChart.Series ser : seriesList) {
            lcDataChart.getData().add(ser);
        }
    }
}
