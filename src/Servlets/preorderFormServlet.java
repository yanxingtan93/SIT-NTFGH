package Servlets;

import DatabaseConnector.DBConn;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "preorderFormServlet")
public class preorderFormServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Map<String, String> map = new LinkedHashMap<>();
        List<String> drugList = new ArrayList<String>();
        String[] drugLists = new String[]{};
        String sql = "SELECT * FROM DRUGS";

        try {
            PreparedStatement ps = DBConn.getPreparedStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                drugList.add(rs.getString(2));
                //map.put(rs.getString(1), rs.getString(2));
            }

            //String json = new Gson().toJson(map);
            String json = new Gson().toJson(drugList);
            System.out.print("form:" + json);
//            PrintWriter writer = response.getWriter();
//            writer.write(json);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        }catch(SQLException ex){
            ex.printStackTrace();

        }
    }
}
