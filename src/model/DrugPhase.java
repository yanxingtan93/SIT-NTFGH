package model;

import DatabaseConnector.DBConn;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DrugPhase {

    private int id;
    private String phaseTerm;


    public DrugPhase(){

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getPhaseTerm() {
        return phaseTerm;
    }

    public void setPhaseTerm(String phaseTerm) {
        this.phaseTerm = phaseTerm;
    }

    public static void addPhase(String name){

        DBConn db = new DBConn();
        String sql = "INSERT INTO DRUGPHASE(drugphase_term) VALUES ('"+name+"')";

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
