package model;

import DatabaseConnector.DBConn;

import java.sql.*;

public class Preorder {
    private int preorderID;
    private String nric;
    private String mode;
    private int preorderDrugID;
    private int drugID;

    public Preorder(int preorderID, String nric, String mode, int preorderDrugID, int drugID) {
        this.preorderID = preorderID;
        this.nric = nric;
        this.mode = mode;
        this.preorderDrugID = preorderDrugID;
        this.drugID = drugID;
    }

    public Preorder(String nric, String mode, int drugID) {
        this.nric = nric;
        this.mode = mode;
        this.drugID = drugID;
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

    public int getDrugID() {
        return drugID;
    }

    public void setDrugID(int drugID) {
        this.drugID = drugID;
    }

    public void addPreorder(){
        String sql = "INSERT INTO PREORDER (user_NRIC,preorder_mode)" +
                "VALUES ('"+nric+"','"+mode+"');";

        try {
            DBConn db = new DBConn();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPreorderDrugs(int preorderID) {

        String sql = "INSERT INTO PREORDERDRUGS (preorder_ID,drug_ID)" +
                "VALUES ('"+preorderID+"','"+drugID+"');";

        try {
            DBConn db = new DBConn();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
