package DatabaseConnector;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsersDaoImpl implements UsersDao {
    private DBConn db;

    public UsersDaoImpl(){
         db = new DBConn();
    }

    @Override
    public ArrayList<User> getAllUsers() {
        return null;
    }

    @Override
    public void addNewUser(User user) {
        System.out.println("ADJW");
        String sql = "INSERT INTO USERS(user_NRIC,user_name,user_password,user_email,user_contact,user_address,user_dob) VALUES(?,?,?,?,?,?,?)";
        System.out.println("A"+" ad "+user.getSpecialCondition()+" ad "+user.getName()+" ad "+user.getNRIC()+" ad "+user.getDob()+" ad "
                +user.getAddress()+" ad "+user.getEmail()+" ad "+user.getName()+" ad "+user.getPassword()+" ad "+user.getRole());
        String myRole = user.getRole().toLowerCase().trim();

        String roleSQL = "";
        System.out.println("ADJW2");

        try{
            Connection connection = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            System.out.println("ADJW23");
            ps.setString(1,user.getNRIC());
            ps.setString(2,user.getName());
            ps.setString(3,user.getPassword());
            ps.setString(4,user.getEmail());
            ps.setInt(5,user.getContact());
            ps.setString(6,user.getAddress());
            ps.setString(7,user.getDob());
            ps.executeUpdate();
            System.out.println("ADJW3");

            PreparedStatement ps1 = connection.prepareStatement(roleSQL);

            if(myRole.equals("patient")){
                roleSQL = "INSERT INTO PATIENTS(allergies_patient,user_NRIC) VALUES(?,?)";
                ps1.setString(1,user.getNRIC());
                ps1.setString(2,user.getSpecialCondition());
            }
            else if(myRole.equals("caregiver")){
                roleSQL = "INSERT INTO CAREGIVERS(user_NRIC) VALUES(?)";
                ps1.setString(1,user.getNRIC());
            }
            else if(myRole.equals("admin")){
                roleSQL = "INSERT INTO ADMINS(user_NRIC) VALUES(?)";
                ps1.setString(1,user.getNRIC());
            }
            else if(myRole.equals("pharmacist")){
                roleSQL = "INSERT INTO PHARMACISTS(user_NRIC) VALUES(?)";
                ps1.setString(1,user.getNRIC());
            }
            System.out.println("ADJW4");
            ps1.executeUpdate();
            System.out.println("ADJW5");
            connection.commit();
            connection.close();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(String NRIC) {
        return null;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(String NRIC) {

    }

    @Override
    public boolean validateUser(String NRIC, String password,String role) {

        String SQLTable = "";
        String myRole = role.toLowerCase().trim();


        if(myRole.equals("patient")){
            SQLTable = "PATIENTS";
        }
        else if(myRole.equals("caregiver")){
            SQLTable = "CAREGIVERS";
        }
        else if(myRole.equals("admin")){
            SQLTable = "ADMINS";
        }
        else if(myRole.equals("pharmacist")){
            SQLTable = "PHARMACISTS";
        }

        boolean valid = false;

        String sql = "SELECT u.user_NRIC,u.user_password,p.user_NRIC FROM USERS u,"+SQLTable+" p WHERE p.user_NRIC = '"+ NRIC+"' " +
                "AND u.user_password = '"+password+"' and p.user_NRIC = u.user_NRIC";
        try{
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            valid = true;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }


        return valid;
    }
}
