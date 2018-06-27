package model;

import DBUtils.DBConn;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DrugIntake {

    private int id;
    private String intakeTerm;


    public DrugIntake(){

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntakeTerm() {
        return intakeTerm;
    }

    public void setIntakeTerm(String intakeTerm) {
        this.intakeTerm = intakeTerm;
    }

    public static void addIntake(String name){

        DBConn db = new DBConn();
        String sql = "INSERT INTO DRUGINTAKE(drugintake_term) VALUES ('"+name+"')";

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
