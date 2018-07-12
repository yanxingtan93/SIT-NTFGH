package model;

import DatabaseConnector.DBConn;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Content {
    private int id;
    private String contentTitle;
    private String contentPath;
    private String contentCategory;

    //Default Constructor
    public Content(){

    }
    //Constructor with ID
    public Content(int id, String contentTitle,String contentPath,String contentCategory){
        this.id =id;
        this.contentTitle=contentTitle;
        this.contentPath=contentPath;
        this.contentCategory = contentCategory;
    }
    //Constructor without ID
    public Content(String contentTitle,String contentPath, String contentCategory){
        this.contentTitle = contentTitle;
        this.contentPath=contentPath;
        this.contentCategory = contentCategory;
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
    public String getContentCategory(){
        return contentCategory;
    }
    public void setContentCategory(String contentCategory){
        this.contentCategory = contentCategory;
    }

}
