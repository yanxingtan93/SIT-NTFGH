package Servlets;

import DBUtils.DBConn;
import model.Medicine;
import model.User;

import javax.servlet.RequestDispatcher;
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

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //USERS (user_NRIC varchar(20) NOT NULL PRIMARY KEY, user_DOB varchar(20), user_password varchar(100), user_contact int(10),
           //     user_name varchar(100), user_email varchar(100), user_address varchar(200), user_special_condition varchar(500));
        String sql = "SELECT * from USERS ";
        //User.addFakeUsers();

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
                //user.setSpecialCondition(resultSet.getString("user_special_condition"));


                list.add(user);
            }


            String nextJSP = "/pharmacist/patientOverview.jsp";
            request.setAttribute("list", list);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request,response);

        } catch (SQLException ex) {
            ex.printStackTrace();

        }



    }
}
