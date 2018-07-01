package Servlets;

import DatabaseConnector.DBConn;
import com.google.gson.Gson;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String route = request.getParameter("route");

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
                System.out.println("My Role: "+role+" of NRIC: "+NRIC+" name-> "+name);
               // User.addNewUser(NRIC,name,Integer.parseInt(contact),email,address,dob,password,role);
                response.sendRedirect("/admin/patientOverview.jsp");
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

                //HARDCODED
                String userNRIC = "S1234567A";

                String patientSQL = "SELECT pc.patient_NRIC,pc.caregiver_NRIC,u.user_NRIC,u.user_name,u.user_contact,u.user_email,u.user_address " +
                        "FROM PATIENTCAREGIVER pc,USERS u WHERE pc.caregiver_NRIC = u.user_NRIC AND pc.patient_NRIC = '"+userNRIC+"'";
// AND pc.patient_NRIC = '"+userNRIC+"'
                ArrayList<User> caregiverList = new ArrayList<User>();

                try {
                    PreparedStatement ps = DBConn.getPreparedStatement(patientSQL);
                    ResultSet resultSet = ps.executeQuery();

                    while (resultSet.next()) {

                        User user = new User();
                        user.setName(resultSet.getString("user_NRIC"));
                        user.setName(resultSet.getString("user_name"));
                        user.setContact(resultSet.getInt("user_contact"));
                        user.setEmail(resultSet.getString("user_email"));
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


        }
    }
}
