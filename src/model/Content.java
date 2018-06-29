package model;

import DatabaseConnector.DBConn;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Content {
    private int id;
    private String contentTitle;
    private String contentPath;

    //Default Constructor
    public Content(){

    }
    //Constructor with ID
    public Content(int id, String contentTitle,String contentPath){
        this.id =id;
        this.contentTitle=contentTitle;
        this.contentPath=contentPath;
    }
    //Constructor without ID
    public Content(String contentTitle,String contentPath){
        this.contentTitle = contentTitle;
        this.contentPath=contentPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContentPath() {
        return contentPath;
    }

    public void setContentPath(String contentPath) {
        this.contentPath = contentPath;
    }
    public void addContent(){
        DBConn db = new DBConn();
        String sql = "INSERT INTO CONTENT(content_title,content_document)"+
                "VALUES('"+contentTitle+"','"+contentPath+"')";
        System.out.println(contentTitle);
        try {
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
