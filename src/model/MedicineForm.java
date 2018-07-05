package model;

import DatabaseConnector.DBConn;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MedicineForm {

    private int id;
    private String formName;


    public MedicineForm(){

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }





}
