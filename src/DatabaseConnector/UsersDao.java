package DatabaseConnector;

import model.User;

import java.util.ArrayList;

public interface UsersDao {
    public ArrayList<User> getAllUsers();
    public void addNewUser(User user);
    public User getUser(String NRIC);
    public void updateUser(User user);
    public void deleteUser(String NRIC);
    public boolean validateUser(String NRIC,String password,String role);

}
