package DatabaseConnector;

import model.Content;
import model.Medicine;
import model.MedicineForm;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicineFormDaoImpl implements MedicineFormDao {
    private DBConn db;

    public MedicineFormDaoImpl(){
        db = new DBConn();
    }

    @Override
    public List<MedicineForm> getAllMedicineForm() {
        List medicineFormList = new ArrayList<MedicineForm>();
        String sql = "SELECT * FROM MEDICINEFORM";
        try {
            Connection con = db.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                MedicineForm medForm = new MedicineForm();
                medForm.setFormName(rs.getString(2));
                medicineFormList.add(medForm);

            }
            con.commit();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicineFormList;

    }

    @Override
    public void addNewMedicineForm(MedicineForm medicineForm) {
        String sql = "INSERT INTO MEDICINEFORM(medicineform_name) VALUES(?)";
        try{
            Connection connection = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,medicineForm.getFormName());
            ps.executeUpdate();
            connection.commit();
            connection.close();

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public MedicineForm getMedicineForm(int id) {
        MedicineForm theForm = new MedicineForm();
        String sql = "SELECT * FROM MEDICINEFORM WHERE ID = ?";
        try{
            Connection connection=db.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                theForm.setFormName(rs.getString(2));
            }


        }catch(SQLException e){
            e.printStackTrace();
        }
        return theForm;
    }

    @Override
    public void updateMedicineForm(MedicineForm medicineForm, int type) {
String sql = "";

        if(type==1){
             sql = "UPDATE MEDICINEFORM SET medicineform_name = ? WHERE medicineform_ID=?";
        }
        else if(type==2){
             sql = "UPDATE DRUGPHASE SET drugphase_term = ? WHERE drugphase_ID=?";
        }
        else if(type==3){
             sql = "UPDATE DRUGINTAKE SET drugintake_term = ? WHERE drugintake_ID=?";
        }





        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, medicineForm.getFormName());
            ps.setInt(2,medicineForm.getId());

            ps.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteForm(int id,int type) {
        String sql = "";

        if(type==1) {
            sql = "DELETE FROM MEDICINEFORM WHERE medicineform_ID = ?";
        }
        else if(type==2) {
            sql = "DELETE FROM DRUGPHASE WHERE drugphase_ID = ?";
        }
        else if(type==3) {
            sql = "DELETE FROM DRUGINTAKE WHERE drugintake_ID = ?";
        }
        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteAllForm() {
        String sql = "truncate table MEDICINEFORM";
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

    @Override
    public String getMedicineFormName(int id) {

        String sql = "SELECT * FROM MEDICINEFORM WHERE medicineform_ID=?";
        String name = "";

        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
             name =  rs.getString("medicineform_name");

            }
            con.commit();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return name;
    }


    @Override
    public String getMedicinePhaseName(int id) {

        String sql = "SELECT * FROM DRUGPHASE WHERE drugphase_ID=?";
        String name = "";

        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                name =  rs.getString("drugphase_term");

            }
            con.commit();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return name;
    }


    @Override
    public String getMedicineIntakeName(int id) {

        String sql = "SELECT * FROM DRUGINTAKE WHERE drugintake_ID=?";
        String name = "";

        try {
        Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
        while (rs.next()) {
        name =  rs.getString("drugintake_term");

        }
        con.commit();
        con.close();

        } catch (SQLException e) {
        e.printStackTrace();
        }

                return name;
            }
        }
////class test{
////    public static void main(String[] args) {
////        MedicineFormDao dao = new MedicineFormDaoImpl();
////        MedicineForm newForm = new MedicineForm();
////        newForm.setFormName("Test");
////        dao.addNewMedicineForm(newForm);
////    }
//
//
//}
