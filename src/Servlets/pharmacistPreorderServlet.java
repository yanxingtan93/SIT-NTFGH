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

        String mode = request.getParameter("mode");

        switch (mode) {
            case "save":
                String status = request.getParameter("preorderStatus");
                String pid = request.getParameter("preorderID");

                System.out.println("\npreorder status:" + status);
                System.out.println("preorder id:" + pid);


                String sql = "UPDATE PREORDER SET status = '"+status+"' WHERE preorder_ID ='"+pid+"'";

                PreparedStatement ps = null;
                try {
                    ps = DBConn.getPreparedStatement(sql);
                    ResultSet resultSet = ps.executeQuery();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                response.sendRedirect("/pharmacist/preorder.jsp");
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");

        switch (mode) {
            case "get":
                System.out.println("in get");

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


            case "Edit":
                String id = request.getParameter("id");
                System.out.println("\nID is:" + id);

                System.out.println("in edit");

                String sql2 = "SELECT preorder_ID, user_NRIC, preorder_mode, collection_date, status FROM PREORDER WHERE preorder_ID='"+id+"'";
                try {
                    PreparedStatement ps = DBConn.getPreparedStatement(sql2);
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

                    String json2 = new Gson().toJson(list);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json2);

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                break;

            case "viewByID":
                ArrayList<Map> list = new ArrayList<>();

                String pid = request.getParameter("id");
                System.out.println("View by ID - ID=" + pid);
                String sql3 = "SELECT d.drug_name, pd.quantity FROM PREORDERDRUGS pd, DRUGS d" +
                        " WHERE pd.preorder_ID = '"+pid+"' and pd.drug_ID = d.drug_ID";

                PreparedStatement ps = null;
                try {
                    ps = DBConn.getPreparedStatement(sql3);
                    ResultSet resultSet= ps.executeQuery();
                    while (resultSet.next()){
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("drug_name", resultSet.getString(1));
                        map.put("quantity", resultSet.getString(2));

                        System.out.println(resultSet.getString(1));
                        System.out.println(resultSet.getString(2));
                        System.out.println("\n");
                        list.add(map);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                String json3 = new Gson().toJson(list);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json3);

                break;

            case "Save":

                break;
        }


    }
}
