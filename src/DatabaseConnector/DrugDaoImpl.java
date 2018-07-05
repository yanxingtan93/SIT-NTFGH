package DatabaseConnector;

import model.Medicine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    }

    @Override
    public Medicine getDrug(int id) {
        return null;
    }

    @Override
    public void updateDrug(Medicine med) {

    }

    @Override
    public void deleteDrug(int id) {

    }

    @Override
    public void deleteAllMedicine() {

    }
}
