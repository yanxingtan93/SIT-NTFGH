package model;

import DBUtils.DBConn;
import sun.security.pkcs11.Secmod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Medicine {
    private int id;
    private String medicineName;
    private String brand;
    private float price;
    private int medicineFormId;
    private String description;
    private String sideEffect;
    private String medicineForm;

    //Default Constructor
    public Medicine(){

    }
    //Constructor with ID
    public Medicine(int id, String medicineName, String brand, float price, int medicineFormId, String description, String sideEffect) {
        this.id = id;
        this.medicineName = medicineName;
        this.brand = brand;
        this.price = price;
        this.medicineFormId = medicineFormId;
        this.description = description;
        this.sideEffect = sideEffect;
    }
    //Constructor without ID
    public Medicine(String medicineName, String brand, float price, int medicineFormId, String description, String sideEffect) {
        this.medicineName = medicineName;
        this.brand = brand;
        this.price = price;
        this.medicineFormId = medicineFormId;
        this.description = description;
        this.sideEffect = sideEffect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getMedicineFormId() {
        return medicineFormId;
    }

    public void setMedicineFormId(int medicineFormId) {
        this.medicineFormId = medicineFormId;
    }

    public String getMedicineForm() {
        return medicineForm;
    }

    public void setMedicineForm(String medicineForm) {
        this.medicineForm = medicineForm;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSideEffect() {
        return sideEffect;
    }

    public void setSideEffect(String sideEffect) {
        this.sideEffect = sideEffect;
    }

    public void addNewMedicine(){
        DBConn db = new DBConn();
        String sql = "INSERT INTO DRUGS(drug_name,drug_brand,drug_price,medicineform_ID,drug_description,drug_side_effect) " +
                "VALUES('"+medicineName+"','"+brand+"',"+price+","+medicineFormId+",'"+description+"','"+sideEffect+"');";
        try {
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void updateMedicine(int id){
        DBConn db = new DBConn();

        String sql = "UPDATE DRUGS SET drug_name = '"+medicineName +"',drug_brand = '"+ brand+"',drug_price = " +price+ "," +
                "medicineform_ID = '"+ medicineFormId+"',drug_description = '"+ description+"',drug_side_effect = '"+sideEffect +"' " +
                "WHERE drug_ID = '"+id+"'";

        System.out.println("in med method "+price);
        try {
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            conn.commit();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void deleteMedicine(int id){
        DBConn db = new DBConn();


        String sql = "DELETE FROM DRUGS WHERE drug_ID = '"+id+"'";

        try {
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            conn.commit();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
