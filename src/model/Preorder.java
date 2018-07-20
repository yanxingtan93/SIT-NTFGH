package model;

import DatabaseConnector.DBConn;

import java.sql.*;

public class Preorder {
    private int preorderID;
    private String nric;
    private String mode;
    private int preorderDrugID;
    private String drugID; //prev int
    private String drugname;
    private int quantity;
    private String collectiondate;
    private String status;

//    public Preorder(int preorderID, String nric, String mode, int preorderDrugID, String drugID, String drugname, int quantity, String collectiondate) {
//        this.preorderID = preorderID;
//        this.nric = nric;
//        this.mode = mode;
//        this.preorderDrugID = preorderDrugID;
//        this.drugID = drugID;
//        this.drugname = drugname;
//        this.quantity = quantity;
//        this.collectiondate = collectiondate;
//    }

    public Preorder(String nric, String mode, String collectiondate, String status) {
        this.nric = nric;
        this.mode = mode;
        //this.quantity = quantity;
        this.collectiondate = collectiondate;
        this.status = status;
    }

//    public Preorder(String nric, String mode, int quantity, String status) {
//        this.nric = nric;
//        this.mode = mode;
//        this.quantity = quantity;
//        this.status = status;
//    }

    public Preorder() {

    }

    public int getPreorderID() {
        String sql = "SELECT MAX(preorder_ID) FROM PREORDER";

        try {
            DBConn db = new DBConn();
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                this.preorderID = rs.getInt(1);
                //System.out.println(this.preorderID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preorderID;
    }

    public void setPreorderID(int preorderID) {
        this.preorderID = preorderID;
    }

    public String getNric() {
        return nric;
    }

    public void setNric(String nric) {
        this.nric = nric;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getPreorderDrugID() {
        return preorderDrugID;
    }

    public void setPreorderDrugID(int preorderDrugID) {
        this.preorderDrugID = preorderDrugID;
    }

    public String getDrugID() {
        return drugID;
    }

    public void setDrugID(String drugID) {
        this.drugID = drugID;
    }

    public String getID(String drugname){
        String sql = "SELECT drug_ID FROM drugs WHERE drug_name='"+drugname+"'";
        try {
            DBConn db = new DBConn();
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                this.drugID = rs.getString("drug_ID");
                System.out.println("\nIn preorder java, drug id:" + drugID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drugID;
    }

    public void addPreorder(){
        String sql = "INSERT INTO PREORDER (user_NRIC,preorder_mode, collection_date,status)" +
                "VALUES ('"+nric+"','"+mode+"', '"+collectiondate+"','"+status+"');";

        try {
            DBConn db = new DBConn();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void addDeliveryPreorder(){
//        String sql = "INSERT INTO PREORDER (user_NRIC,preorder_mode,status)" +
//                "VALUES ('"+nric+"','"+mode+"','"+status+"');";
//
//        try {
//            DBConn db = new DBConn();
//            Connection conn = db.getConnection();
//            Statement stmt = conn.createStatement();
//            stmt.executeUpdate(sql);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void addPreorderDrugs(int preorderID, String drugID, int quantity) {
        System.out.println("ID: " + preorderID);
        System.out.println("drug ID: " + drugID);
        System.out.println("Quantity: " + quantity);
        String sql = "INSERT INTO PREORDERDRUGS (preorder_ID,drug_ID, quantity)" +
                "VALUES ('"+preorderID+"','"+drugID+"', '"+quantity+"');";

        try {
            DBConn db = new DBConn();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getPreorderDrugs(int preorderID) {
        String sql = "SELECT d.drug_name, pd.quantity FROM PREORDERDRUGS pd, PREORDER p, DRUGS d" +
                " WHERE p.preorder_ID = '"+preorderID+"' and pd.drug_ID = d.drug_ID";

        try {
            DBConn db = new DBConn();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getDrugname() {
        return drugname;
    }

    public void setDrugname(String drugname) {
        this.drugname = drugname;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCollectiondate() {
        return collectiondate;
    }

    public void setCollectiondate(String collectiondate) {
        this.collectiondate = collectiondate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
