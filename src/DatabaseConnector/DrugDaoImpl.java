package DatabaseConnector;

import model.Medicine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DrugDaoImpl implements DrugsDao {
    private DBConn db;

    public DrugDaoImpl(){
         db = new DBConn();
    }

    @Override
    public ArrayList<Medicine> getAllDrugs() {
        ArrayList<Medicine> drugList = new ArrayList<>();
        String sql = "SELECT d.drug_id,d.drug_name,d.drug_brand," +
                "d.medicineform_ID,d.drug_description,d.drug_side_effect,m.medicineform_name FROM DRUGS d,MEDICINEFORM m " +
                "WHERE d.medicineform_ID = m.medicineform_ID";
        try{
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Medicine medicineCat = new Medicine();
                medicineCat.setId(rs.getInt("drug_ID"));
                medicineCat.setMedicineName(rs.getString("drug_name"));
                medicineCat.setBrand(rs.getString("drug_brand"));
                medicineCat.setMedicineFormId(rs.getInt("medicineform_ID"));
                medicineCat.setDescription(rs.getString("drug_description"));
                medicineCat.setSideEffect(rs.getString("drug_side_effect"));
                medicineCat.setMedicineForm(rs.getString("medicineform_name"));
                drugList.add(medicineCat);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return drugList;

    }

    public ArrayList<Medicine> getAllDrugEdit(String medicineID){
        ArrayList<Medicine>  drugList = new ArrayList<>();
        String sql = "SELECT d.drug_id,d.drug_name,d.drug_brand," +
                "d.medicineform_ID,d.drug_description,d.drug_side_effect,m.medicineform_name FROM DRUGS d,MEDICINEFORM m " +
                "WHERE d.drug_ID = " + medicineID.trim() + " AND d.medicineform_ID = m.medicineform_ID";
        try {
            Connection connection = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();


            while (resultSet.next()) {
                Medicine medicineCat = new Medicine();
                medicineCat.setId(resultSet.getInt("drug_ID"));
                medicineCat.setMedicineName(resultSet.getString("drug_name"));
                medicineCat.setBrand(resultSet.getString("drug_brand"));
                medicineCat.setMedicineFormId(resultSet.getInt("medicineform_ID"));
                medicineCat.setDescription(resultSet.getString("drug_description"));
                medicineCat.setSideEffect(resultSet.getString("drug_side_effect"));
                medicineCat.setMedicineForm(resultSet.getString("medicineform_name"));
                drugList.add(medicineCat);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return drugList;


    }

    @Override
    public void addNewDrug(Medicine med) {
        String SQL = "INSERT INTO DRUGS(drug_name,drug_brand,medicineform_ID,drug_description,drug_side_effect) VALUES(?,?,?,?,?);";
        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1,med.getMedicineName());
            ps.setString(2,med.getBrand());
            ps.setInt(3,med.getMedicineFormId());
            ps.setString(4,med.getDescription());
            ps.setString(5,med.getSideEffect());
            ps.executeUpdate();
            conn.commit();
            conn.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }

    }

    @Override
    public Medicine getDrug(int id) {
        Medicine drug = new Medicine();
        String SQL ="SELECT * FROM DRUGS WHERE drug_ID = ?";
        try{
            Connection connection = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                drug.setMedicineName(rs.getString("drug_name"));
                drug.setBrand(rs.getString("drug_brand"));
                drug.setMedicineFormId(rs.getInt("medicineform_ID"));
                drug.setDescription(rs.getString("drug_description"));
                drug.setSideEffect(rs.getString("drug_side_effect"));

            }
            connection.commit();
            connection.close();

        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return drug;
    }

    @Override
    public void updateDrug(Medicine med) {

        String sql = "UPDATE DRUGS SET drug_name = ?,drug_brand = ?,medicineform_ID = ?,drug_description = ?,drug_side_effect = ? WHERE drug_ID = ?";


        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,med.getMedicineName());
            ps.setString(2,med.getBrand());
            ps.setInt(3,med.getMedicineFormId());
            ps.setString(4,med.getDescription());
            ps.setString(5,med.getSideEffect());
            ps.setInt(6,med.getId());
            ps.executeUpdate();
            conn.commit();
            conn.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteDrug(int id) {
        String sql = "DELETE FROM DRUGS WHERE drug_ID = ?";
        try{
            Connection connection = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
            connection.commit();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void deleteAllMedicine() {
        String sql = "truncate table DRUGS";
        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            con.commit();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
