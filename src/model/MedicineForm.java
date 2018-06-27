package model;

import DBUtils.DBConn;

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


    public static void addForm(String name){

        DBConn db = new DBConn();
        String sql = "INSERT INTO MEDICINEFORM(medicineform_name) VALUES ('"+name+"')";

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
