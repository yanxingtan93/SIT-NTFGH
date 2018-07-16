package DatabaseConnector;

import model.User;

import java.util.ArrayList;

public interface UsersDao {
    public ArrayList<User> getAllUsers();
    public ArrayList<User> getAllPatients(String caregiverNRIC);
    public void addNewUser(User user);
    public User getUser(String NRIC);
    public String getName(String NRIC);
    public void updateUser(User user);
    public String getMyRole(String NRIC);
    public void deleteUser(String NRIC);
    public boolean validateUser(String NRIC,String password,String role);
    public boolean validateRole(String NRIC,String role);

}
