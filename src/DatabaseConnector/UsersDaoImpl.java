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
