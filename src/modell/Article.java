package modell;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Article {
    private String articleName;
    private int articleID;
    private int orderID;
    private String articleBuyDate;

    private static List<Article> articleList = new ArrayList<>();

    public Article(String articleName, int articleID, int orderID, String articleBuyDate) {
        this.articleName = articleName;
        this.articleID = articleID;
        this.orderID = orderID;
        this.articleBuyDate = articleBuyDate;
        articleList.add(this);
    }
    public String getArticleName() {
        return articleName;
    }

    public int getArticleID() {
        return articleID;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getArticleBuyDate() {
        return articleBuyDate;
    }

    public static List<Article> getArticleList() {
        return articleList;
    }
}
