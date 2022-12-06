package logic;


import modell.Article;

import java.sql.*;
import java.time.LocalDate;

public class DBConnection {

    private String url;
    private String user;
    private String pass;
    private Connection con;

    public DBConnection(String url, String user, String pass) {
        this.url = url;
        this.user = user;
        this.pass = pass;
    }

    public void connect() {
        try {
            con = DriverManager.getConnection(url, user, pass);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllIOrderedtems() {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select position.positionID, `order`.orderid, article.articlename, article.articleID, `order`.orderdatetime\n" +
                                                "from position\n" +
                                                "Inner Join article on position.fk_articleID = article.articleID\n" +
                                                "Inner Join `order` on position.fk_orderID = `order`.orderID\n" +
                                                "order by position.positionID");
            while (rs.next()) {
                new Article(rs.getString("articlename"), rs.getInt("articleid"), rs.getInt("orderID"),rs.getString("orderdatetime") );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
