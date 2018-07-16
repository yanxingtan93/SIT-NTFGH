package Servlets;

import DatabaseConnector.DBConn;
import DatabaseConnector.UsersDao;
import DatabaseConnector.UsersDaoImpl;
import com.google.gson.Gson;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String ENC_ALGORITHM = "PBEWithMD5AndDES";

        String route = request.getParameter("route");
        UsersDao usersDao = new UsersDaoImpl();

        System.out.println("route in UserServlet = "+route);

        switch(route){
            case "adminAdd":
                String name = request.getParameter("user_name");
                String NRIC = request.getParameter("user_NRIC");
                String email = request.getParameter("user_email");
                String contact  = request.getParameter("user_contact");
                String dob = request.getParameter("user_dob");
                String address = request.getParameter("user_address");
                String password = request.getParameter("user_password");
                String role = request.getParameter("role");

                User user =  new User(NRIC,name,password,dob,Integer.parseInt(contact),email,address,role);
                usersDao.addNewUser(user);

                break;

            case "login":
                    String userNRIC = request.getParameter("userName");
                    String userPass = request.getParameter("userPassword");
                    String userRole = request.getParameter("optradio");

                    UsersDao userDao = new UsersDaoImpl();
                    boolean valid = userDao.validateUser(userNRIC,userPass,userRole);
                    if(valid){
                        String myRole = userRole.toLowerCase().trim();
                        System.out.println(myRole+" Accessing");
                        HttpSession session=request.getSession();
                        session.setAttribute("userID",userNRIC);
                        session.setAttribute("role",userRole);

                        if(myRole.equals("patient")){
                            response.sendRedirect("http://localhost:8080/patient/pillboxOverview.jsp");
                        }
                        else if(myRole.equals("caregiver")){
                            response.sendRedirect("http://localhost:8080/caregiver/patientOverview.jsp");
                        }
                        else if(myRole.equals("admin")){
                            response.sendRedirect("http://localhost:8080/admin/patientOverview.jsp");
                        }
                        else if(myRole.equals("pharmacist")){
                            response.sendRedirect("http://localhost:8080/pharmacist/patientOverview.jsp");
                        }

                    }
                    else {
                        response.sendRedirect("http://localhost:8080/registration.jsp");
                    }
                    break;


            case "userAdd":
                String name1 = request.getParameter("user_name");
                String NRIC1 = request.getParameter("user_NRIC");
                String email1 = request.getParameter("user_email");
                String contact1  = request.getParameter("user_contact");
                String dob1 = request.getParameter("user_dob");
                String address1 = request.getParameter("user_address");
                String password1 = request.getParameter("user_password");
                String condition = "";
                String role1 = "";
                 condition = request.getParameter("user_condition");
                 role1 = request.getParameter("roleA");

                System.out.println(role1+" -- "+role1);
                System.out.println(condition+" -- "+condition);

                User user1 =  new User(NRIC1,name1,password1,dob1,Integer.parseInt(contact1),email1,address1,role1,condition);
                user1.setSpecialCondition(condition);
                usersDao.addNewUser(user1);
                response.sendRedirect("http://localhost:8080/index.jsp");
                break;

            case "View":

                break;

            // FOR ADMINS REMOVING USERS
            case "Delete":
                String delNRIC = request.getParameter("user_NRIC");
                User removeUser = new User();
                usersDao.deleteUser(delNRIC);
                response.sendRedirect("http://localhost:8080/admin/patientOverview.jsp");

                break;

            case "logout":
                HttpSession session=request.getSession();
                session.removeAttribute("userID");
                response.sendRedirect("http://localhost:8080/index.jsp");
                break;
            case "edit":

                String name2 = request.getParameter("user_name");
                String NRIC2 = request.getParameter("user_NRIC");
                String email2 = request.getParameter("user_email");
                String contact2  = request.getParameter("user_contact");
                String dob2 = request.getParameter("user_dob");
                String address2 = request.getParameter("user_address");
                String role2 = request.getParameter("roleA");

               User editUser = new User(NRIC2,name2,dob2,Integer.parseInt(contact2),email2,address2,role2,"");
               usersDao.updateUser(editUser);


                break;
                default:
                    System.out.println("Error in adding (Via Admin Add Account)");
                    break;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");


        switch (mode) {

            case "pharmacistAllPatients":
            String sql = "SELECT u.user_NRIC,u.user_name,u.user_DOB,u.user_contact,u.user_email,u.user_address" +
                    ",p.allergies_patient" +
                    " from USERS u, PATIENTS p WHERE u.user_NRIC = p.user_NRIC";


            try {


                PreparedStatement ps = DBConn.getPreparedStatement(sql);
                ResultSet resultSet = ps.executeQuery();

                ArrayList<User> list = new ArrayList<User>();

                while (resultSet.next()) {
                    User user = new User();
                    user.setNRIC(resultSet.getString("user_NRIC"));
                    user.setName(resultSet.getString("user_name"));
                    user.setDob(resultSet.getString("user_DOB"));
                    user.setContact(resultSet.getInt("user_contact"));
                    user.setEmail(resultSet.getString("user_email"));
                    user.setAddress(resultSet.getString("user_address"));
                    user.setSpecialCondition(resultSet.getString("allergies_patient"));


                    list.add(user);
                }

                String json = new Gson().toJson(list);
                System.out.print(" rows --> " + json);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);


            } catch (SQLException ex) {
                ex.printStackTrace();

            }
            break;


            case "pharmacistAllPharmacists":
                String sqlPH = "SELECT u.user_NRIC,u.user_name,u.user_DOB,u.user_contact,u.user_email,u.user_address" +
                        " from USERS u, PHARMACISTS p WHERE u.user_NRIC = p.user_NRIC";


                try {


                    PreparedStatement ps1 = DBConn.getPreparedStatement(sqlPH);
                    ResultSet resultSet = ps1.executeQuery();

                    ArrayList<User> list = new ArrayList<User>();

                    while (resultSet.next()) {
                        User user = new User();
                        user.setNRIC(resultSet.getString("user_NRIC"));
                        user.setName(resultSet.getString("user_name"));
                        user.setContact(resultSet.getInt("user_contact"));
                        user.setEmail(resultSet.getString("user_email"));
                        user.setAddress(resultSet.getString("user_address"));


                        list.add(user);
                    }

                    String json = new Gson().toJson(list);
                    System.out.print(" rows --> " + json);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);


                } catch (SQLException ex) {
                    ex.printStackTrace();

                }
                break;


            case "admin":
                String sql1 = "SELECT u.user_NRIC,u.user_name,u.user_DOB,u.user_contact,u.user_email,u.user_address" +
                        " from USERS u, PATIENTS p WHERE u.user_NRIC = p.user_NRIC";

                String sql2 = "SELECT u.user_NRIC,u.user_name,u.user_DOB,u.user_contact,u.user_email,u.user_address" +
                        " from USERS u, CAREGIVERS p WHERE u.user_NRIC = p.user_NRIC";

                String sql3 = "SELECT u.user_NRIC,u.user_name,u.user_DOB,u.user_contact,u.user_email,u.user_address" +
                        " from USERS u, PHARMACISTS p WHERE u.user_NRIC = p.user_NRIC";

                String sql4 = "SELECT u.user_NRIC,u.user_name,u.user_DOB,u.user_contact,u.user_email,u.user_address" +
                        " from USERS u, ADMINS p WHERE u.user_NRIC = p.user_NRIC";

                ArrayList<User> list = new ArrayList<User>();

                try {
                    PreparedStatement ps = DBConn.getPreparedStatement(sql1);
                    ResultSet resultSet = ps.executeQuery();

                    while (resultSet.next()) {

                        User user = new User();
                        user.setNRIC(resultSet.getString("user_NRIC"));
                        user.setName(resultSet.getString("user_name"));
                        user.setDob(resultSet.getString("user_DOB"));
                        user.setContact(resultSet.getInt("user_contact"));
                        user.setEmail(resultSet.getString("user_email"));
                        user.setAddress(resultSet.getString("user_address"));
                        user.setRole("Patient");


                        list.add(user);
                    }

                    resultSet.close();

                    PreparedStatement ps2 = DBConn.getPreparedStatement(sql2);
                    resultSet = ps2.executeQuery();

                    while (resultSet.next()) {

                        User user = new User();
                        user.setNRIC(resultSet.getString("user_NRIC"));
                        user.setName(resultSet.getString("user_name"));
                        user.setDob(resultSet.getString("user_DOB"));
                        user.setContact(resultSet.getInt("user_contact"));
                        user.setEmail(resultSet.getString("user_email"));
                        user.setAddress(resultSet.getString("user_address"));
                        user.setRole("Caregiver");


                        list.add(user);
                    }
                    resultSet.close();
                    PreparedStatement ps3 = DBConn.getPreparedStatement(sql3);
                    resultSet = ps3.executeQuery();

                    while (resultSet.next()) {

                        User user = new User();
                        user.setNRIC(resultSet.getString("user_NRIC"));
                        user.setName(resultSet.getString("user_name"));
                        user.setDob(resultSet.getString("user_DOB"));
                        user.setContact(resultSet.getInt("user_contact"));
                        user.setEmail(resultSet.getString("user_email"));
                        user.setAddress(resultSet.getString("user_address"));
                        user.setRole("Pharmacist");


                        list.add(user);
                    }
                    resultSet.close();
                    PreparedStatement ps4 = DBConn.getPreparedStatement(sql4);
                    resultSet = ps4.executeQuery();

                    while (resultSet.next()) {

                        User user = new User();
                        user.setNRIC(resultSet.getString("user_NRIC"));
                        user.setName(resultSet.getString("user_name"));
                        user.setDob(resultSet.getString("user_DOB"));
                        user.setContact(resultSet.getInt("user_contact"));
                        user.setEmail(resultSet.getString("user_email"));
                        user.setAddress(resultSet.getString("user_address"));
                        user.setRole("Admin");


                        list.add(user);
                    }





                    String json = new Gson().toJson(list);
                    System.out.print(" rows --> " + json);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);


                } catch (SQLException ex) {
                    ex.printStackTrace();

                }
                break;


            case "patient":

                String userNRIC = request.getParameter("id");

                String myProfileSQL = "SELECT * from USERS WHERE user_NRIC = '"+userNRIC+"'";


                String patientSQL = "SELECT pc.patient_NRIC,pc.caregiver_NRIC,u.user_NRIC,u.user_name,u.user_contact,u.user_email,u.user_address,u.user_DOB " +
                        "FROM PATIENTCAREGIVER pc,USERS u WHERE pc.caregiver_NRIC = u.user_NRIC AND pc.patient_NRIC = '"+userNRIC+"'";




                ArrayList<User> caregiverList = new ArrayList<User>();

                try {
                    PreparedStatement ps0 = DBConn.getPreparedStatement(myProfileSQL);
                    ResultSet resultSet = ps0.executeQuery();
                    while (resultSet.next()) {

                        User user = new User();
                        user.setNRIC(resultSet.getString("user_NRIC"));
                        user.setName(resultSet.getString("user_name"));
                        user.setContact(resultSet.getInt("user_contact"));
                        user.setEmail(resultSet.getString("user_email"));
                        user.setDob(resultSet.getString("user_DOB"));
                        user.setAddress(resultSet.getString("user_address"));

                        caregiverList.add(user);
                    }


                    PreparedStatement ps = DBConn.getPreparedStatement(patientSQL);
                    resultSet = ps.executeQuery();

                    while (resultSet.next()) {

                        User user = new User();
                        user.setNRIC(resultSet.getString("user_NRIC"));
                        user.setName(resultSet.getString("user_name"));
                        user.setContact(resultSet.getInt("user_contact"));
                        user.setEmail(resultSet.getString("user_email"));
                        user.setDob(resultSet.getString("user_DOB"));
                        user.setAddress(resultSet.getString("user_address"));

                        caregiverList.add(user);
                    }

                    String json = new Gson().toJson(caregiverList);
                   // System.out.print(" rows --> " + json);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                } catch (SQLException ex) {
                    ex.printStackTrace();

                }
                break;

            case "caregiver":
                String careGiverNRIC = "S1234567C";
                String sqlCare = "SELECT pc.patient_NRIC,pc.caregiver_NRIC,u.user_NRIC,u.user_name,u.user_email,u.user_contact,u.user_address" +
                        ",u.user_dob FROM USERS u, PATIENTCAREGIVER pc WHERE pc.caregiver_NRIC = '"+careGiverNRIC+"' " +
                        "AND pc.patient_NRIC = u.user_NRIC";

                ArrayList<User> caregiverPatientList = new ArrayList<User>();

                try {
                    PreparedStatement ps = DBConn.getPreparedStatement(sqlCare);
                    ResultSet resultSet = ps.executeQuery();

                    while (resultSet.next()) {

                        User user = new User();
                        user.setNRIC(resultSet.getString("user_NRIC"));
                        user.setName(resultSet.getString("user_name"));
                        user.setContact(resultSet.getInt("user_contact"));
                        user.setEmail(resultSet.getString("user_email"));
                        user.setAddress(resultSet.getString("user_address"));
                        user.setDob(resultSet.getString("user_dob"));

                        caregiverPatientList.add(user);
                    }

                    String json = new Gson().toJson(caregiverPatientList);
                    // System.out.print(" rows --> " + json);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                } catch (SQLException ex) {
                    ex.printStackTrace();

                }
                break;


            case "myprofile":

                String myNRIC = request.getParameter("id");

                String myProfileSQL1 = "SELECT * from USERS WHERE user_NRIC = '"+myNRIC+"'";

                ArrayList<User> myProfile = new ArrayList<User>();

                try {
                    PreparedStatement ps0 = DBConn.getPreparedStatement(myProfileSQL1);
                    ResultSet resultSet = ps0.executeQuery();
                    while (resultSet.next()) {

                        User user = new User();
                        user.setNRIC(resultSet.getString("user_NRIC"));
                        user.setName(resultSet.getString("user_name"));
                        user.setContact(resultSet.getInt("user_contact"));
                        user.setEmail(resultSet.getString("user_email"));
                        user.setDob(resultSet.getString("user_dob"));
                        user.setAddress(resultSet.getString("user_address"));

                        myProfile.add(user);
                    }


                    String json = new Gson().toJson(myProfile);

                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                } catch (SQLException ex) {
                    ex.printStackTrace();

                }
                break;

            case "validate":

                System.out.println("validating the role of User");
                String role = request.getParameter("role");
                String NRIC = request.getParameter("NRIC");
                UsersDao user = new UsersDaoImpl();
                boolean valid = user.validateRole(NRIC,role);
                System.out.println(valid);
                String json = new Gson().toJson(valid);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);

                break;


        }
    }
}
