package Servlets;

import DBUtils.DBConn;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(name = "medicineFormServlet")
public class medicineFormServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> options = new LinkedHashMap<>();
        String sql = "SELECT * FROM MEDICINEFORM";

        try {
            PreparedStatement ps = DBConn.getPreparedStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                options.put(resultSet.getString(1),resultSet.getString(2));


            }


            String json = new Gson().toJson(options);
            System.out.print(json);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        }catch(SQLException ex){
            ex.printStackTrace();

        }




    }
}
