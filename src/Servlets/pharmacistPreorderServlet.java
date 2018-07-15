package Servlets;

import DatabaseConnector.DBConn;
import com.google.gson.Gson;

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
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "pharmacistPreorderServlet")
public class pharmacistPreorderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");

        switch (mode) {
            case "get":
                System.out.println("in do get");

                String sql = "SELECT p.preorder_ID, p.user_NRIC, p.preorder_mode, p.collection_date, p.status FROM PREORDER p";
                try {
                    PreparedStatement ps = DBConn.getPreparedStatement(sql);
                    ResultSet resultSet = ps.executeQuery();

                    ArrayList<Map> list = new ArrayList<>();

                    while (resultSet.next()) {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("preorder_ID", resultSet.getString("preorder_ID"));
                        map.put("user_NRIC", resultSet.getString("user_NRIC"));
                        map.put("preorder_mode", resultSet.getString("preorder_mode"));
                        map.put("collection_date", resultSet.getString("collection_date"));
                        map.put("status", resultSet.getString("status"));
                        list.add(map);
                    }

                    String json = new Gson().toJson(list);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                break;
        }


    }
}
