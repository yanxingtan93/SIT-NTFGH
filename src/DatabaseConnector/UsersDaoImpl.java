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
    public ArrayList<User> getAllPatients(String caregiverNRIC) {

        String SQL = "SELECT pc.patient_NRIC,pc.caregiver_NRIC,u.user_name,u.user_NRIC FROM USERS u,PATIENTCAREGIVER pc " +
                "WHERE pc.caregiver_NRIC = ? AND pc.patient_NRIC = u.user_NRIC";

        ArrayList<User> users = new ArrayList<>();

        try {
            Connection connection = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, caregiverNRIC);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                User user = new User();
                user.setNRIC(rs.getString("patient_NRIC"));
                user.setName(rs.getString("user_name"));
                users.add(user);
            }

            return users;

        }
        catch(SQLException e){
            System.err.println("Error in getting caregiver's patients "+e);
        }


        return null;
    }

    @Override
    public boolean addNewUser(User user) {
        System.out.println("ADJW");
        boolean existUsers = false;
        boolean existRoles = false;

        String myRole = user.getRole().toLowerCase().trim();


        String searchUser = "SELECT user_NRIC from USERS WHERE user_NRIC = ?";
        try {
            Connection connection = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(searchUser);
            ps.setString(1, user.getNRIC());

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                existUsers=true;
            }

            String searchRole = "";
            PreparedStatement psRoleCheck = null;

            if(myRole.equals("patient")){
                searchRole = "SELECT * FROM PATIENTS WHERE user_NRIC = ?";
                psRoleCheck = connection.prepareStatement(searchRole);
                psRoleCheck.setString(1,user.getNRIC());

            }
            else if(myRole.equals("caregiver")){
                searchRole = "SELECT * FROM CAREGIVERS WHERE user_NRIC = ?";
                psRoleCheck = connection.prepareStatement(searchRole);
                psRoleCheck.setString(1,user.getNRIC());
            }
            else if(myRole.equals("admin")){
                searchRole = "SELECT * FROM ADMINS WHERE user_NRIC = ?";
                psRoleCheck = connection.prepareStatement(searchRole);
                psRoleCheck.setString(1,user.getNRIC());
            }
            else if(myRole.equals("pharmacist")){
                searchRole = "SELECT * FROM PHARMACISTS WHERE user_NRIC = ?";
                psRoleCheck = connection.prepareStatement(searchRole);
                psRoleCheck.setString(1,user.getNRIC());
            }

            ResultSet rsRole = psRoleCheck.executeQuery();
            while (rsRole.next()){
                existRoles=true;
            }

        }
        catch (SQLException e){
            System.err.println("Error getting name of user in UserDaoImpl " + e);
        }




        String sql = "INSERT INTO USERS(user_NRIC,user_name,user_password,user_email,user_contact,user_address,user_dob) VALUES(?,?,?,?,?,?,?)";

        String roleSQL = "";
        user.setPassword(User.hashPassword(user.getPassword()));

        try{
            Connection connection = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,user.getNRIC());
            ps.setString(2,user.getName());
            ps.setString(3,user.getPassword());
            ps.setString(4,user.getEmail());
            ps.setInt(5,user.getContact());
            ps.setString(6,user.getAddress());
            ps.setString(7,user.getDob());
            if(existUsers!=true) {
                ps.executeUpdate();
            }
            System.out.println("ADJW3");


            PreparedStatement ps1 = null;
            if(myRole.equals("patient")){
                roleSQL = "INSERT INTO PATIENTS(user_NRIC,allergies_patient) VALUES(?,?)";
                 ps1 = connection.prepareStatement(roleSQL);
                ps1.setString(1,user.getNRIC());
                ps1.setString(2,user.getSpecialCondition());
            }
            else if(myRole.equals("caregiver")){
                roleSQL = "INSERT INTO CAREGIVERS(user_NRIC) VALUES(?)";
                 ps1 = connection.prepareStatement(roleSQL);
                ps1.setString(1,user.getNRIC());
            }
            else if(myRole.equals("admin")){
                roleSQL = "INSERT INTO ADMINS(user_NRIC) VALUES(?)";
                 ps1 = connection.prepareStatement(roleSQL);
                ps1.setString(1,user.getNRIC());
            }
            else if(myRole.equals("pharmacist")){
                roleSQL = "INSERT INTO PHARMACISTS(user_NRIC) VALUES(?)";
                 ps1 = connection.prepareStatement(roleSQL);
                ps1.setString(1,user.getNRIC());
            }
            System.out.println("ADJW4");
            if(existRoles!=true) {
                ps1.executeUpdate();

            }
            System.out.println("ADJW5");
            connection.commit();
            connection.close();

        }catch (SQLException e) {
            e.printStackTrace();
        }


        if(existRoles==true){
            return false;
        }
        else
            return true;
    }


    @Override
    public void addNewPatientCaregiver(User user,String caregiverNRIC) {
        System.out.println("ADJW");
        String sql = "INSERT INTO USERS(user_NRIC,user_name,user_password,user_email,user_contact,user_address,user_dob) VALUES(?,?,?,?,?,?,?)";

        String myRole = user.getRole().toLowerCase().trim();

        String roleSQL = "";
        user.setPassword(User.hashPassword(user.getPassword()));

        try{
            Connection connection = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,user.getNRIC());
            ps.setString(2,user.getName());
            ps.setString(3,user.getPassword());
            ps.setString(4,user.getEmail());
            ps.setInt(5,user.getContact());
            ps.setString(6,user.getAddress());
            ps.setString(7,user.getDob());
            ps.executeUpdate();

            System.out.println("ADJW pc3");


            PreparedStatement ps1 = null;

                roleSQL = "INSERT INTO PATIENTS(user_NRIC,allergies_patient) VALUES(?,?)";
                ps1 = connection.prepareStatement(roleSQL);
                ps1.setString(1,user.getNRIC());
                ps1.setString(2,user.getSpecialCondition());


            ps1.executeUpdate();

            System.out.println("ADJW pc4");
            PreparedStatement ps2 = null;

            roleSQL = "INSERT INTO PATIENTCAREGIVER(patient_NRIC,caregiver_NRIC) VALUES(?,?)";
            ps2 = connection.prepareStatement(roleSQL);
            ps2.setString(1,user.getNRIC());
            ps2.setString(2,caregiverNRIC);


            ps2.executeUpdate();


            System.out.println("ADJW pc5");
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
    public String getName(String NRIC) {

        String SQL = "SELECT user_name from USERS WHERE user_NRIC = ?";
        String name = "";
        try {
            Connection connection = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, NRIC);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                name = rs.getString("user_name");
            }

        }
        catch (SQLException e){
            System.err.println("Error getting name of user in UserDaoImpl " + e);
        }
        return name;
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE USERS SET user_NRIC = ?,user_name = ?,user_contact = ?,user_address = ?," +
                "user_dob = ?,user_email = ? WHERE user_NRIC = ?";


        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,user.getNRIC());
            ps.setString(2,user.getName());
            ps.setInt(3,user.getContact());
            ps.setString(4,user.getAddress());
            ps.setString(5,user.getDob());
            ps.setString(6,user.getEmail());
            ps.setString(7,user.getNRIC());
            ps.executeUpdate();
            conn.commit();
            conn.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getMyRole(String NRIC) {



        return "";
    }

    @Override
    public void deleteUser(String NRIC) {

        String removeSQL = "DELETE FROM USERS WHERE user_NRIC = ?";

        System.out.println("UserDaoImpl: Error in removing user1 "+NRIC);

        try {
            Connection connection = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(removeSQL);
            ps.setString(1,NRIC);
            ps.executeUpdate();
            connection.commit();
            connection.close();
        }
        catch (SQLException e){
            System.out.println("UserDaoImpl: Error in removing user \n"+e);
        }
    }

    @Override
    public void removeCaregiver(String patientNRIC, String caregiverNRIC) {
        String removeSQL = "DELETE FROM PATIENTCAREGIVER WHERE patient_NRIC = ? and caregiver_NRIC = ?";

        try {
            Connection connection = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(removeSQL);
            ps.setString(1,patientNRIC);
            ps.setString(2,caregiverNRIC);
            ps.executeUpdate();
            connection.commit();
            connection.close();
        }
        catch (SQLException e){
            System.out.println("UserDaoImpl: Error in removing caregiver from patient \n"+e);
        }
    }

    @Override
    public boolean validateUser(String NRIC, String password,String role) {

        String SQLTable = "";
        String myRole = role.toLowerCase().trim();
        String saltedPassword = User.hashPassword(password);

System.out.println(saltedPassword + " PASSWorD:");
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
                "AND u.user_password = '"+saltedPassword+"' and p.user_NRIC = u.user_NRIC";
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


    @Override
    public boolean validateRole(String NRIC,String role) {

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
                "AND  p.user_NRIC = u.user_NRIC";
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
