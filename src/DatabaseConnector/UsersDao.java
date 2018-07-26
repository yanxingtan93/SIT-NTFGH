package DatabaseConnector;

import model.User;

import java.util.ArrayList;

public interface UsersDao {
    public ArrayList<User> getAllUsers();
    public ArrayList<User> getAllPatients(String caregiverNRIC);
    public boolean addNewUser(User user);
    public void addNewPatientCaregiver(User user,String caregiverNRIC);
    public User getUser(String NRIC);
    public String getName(String NRIC);
    public void updateUser(User user);
    public String getMyRole(String NRIC);
    public void deleteUser(String NRIC);
    public void removeCaregiver(String patientNRIC,String caregiverNRIC);
    public boolean validateUser(String NRIC,String password,String role);
    public boolean validateRole(String NRIC,String role);


}
