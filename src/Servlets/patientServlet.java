package Servlets;

import DBUtils.DBConn;
import com.google.gson.Gson;
import model.Medicine;
import model.User;
import model.pillbox;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "patientServlet")
public class patientServlet extends HttpServlet {
    private Gson gson = new Gson();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String route = request.getRequestURL().toString().split("/patient/")[1];

        //Sort the request by url i.e. /patient/listMedication
        switch (route) {
            case "getMedicationNames":
                response.getWriter().write(getMedicationNames());
                break;

            case "addToPillbox":
                addToPillbox();
                response.sendRedirect("http://localhost:8080/patient/pillboxOverview.jsp");
                break;

            case "listPillbox":
                response.getWriter().write(listPillbox());
                break;

            case "editMedication":
                response.getWriter().write("edit");
                break;
            case "deleteMedication":
                response.getWriter().write("delete");
                break;
        }
    }

    private String getMedicationNames(){
        String sql = "SELECT * FROM DRUGS";
        ArrayList<Map> list = new ArrayList<>();
        try {
            PreparedStatement ps = DBConn.getPreparedStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("drug_ID", resultSet.getString("drug_ID"));
                map.put("drug_name", resultSet.getString("drug_name"));
                list.add(map);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return gson.toJson(list);
    }
    private String listPillbox(){
        String sql = "SELECT * FROM INVENTORY";
        ArrayList<Map> list = new ArrayList<>();
        try {
            PreparedStatement ps = DBConn.getPreparedStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("inventory_ID", resultSet.getString("inventory_ID"));
                map.put("drug_ID", resultSet.getString("drug_ID"));
                map.put("drugintake_ID", resultSet.getString("drugintake_ID"));
                map.put("drugphase_ID", resultSet.getString("drugphase_ID"));
                map.put("inventory_balance", resultSet.getString("inventory_balance"));
                map.put("inventory_status", resultSet.getString("inventory_status"));
                map.put("inventory_startdate", resultSet.getString("inventory_startdate"));
                list.add(map);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return gson.toJson(list);
    }

    private void addToPillbox(){
        String sql = "INSERT INTO INVENTORY(drug_ID, drugintake_ID,drugphase_ID,inventory_balance,inventory_status,inventory_startdate) " +
                "VALUES(?,?,?,?,?,?);";
        try {
            PreparedStatement ps = DBConn.getPreparedStatement(sql);
            ps.setInt(1, 1);
            ps.setInt(2, 1);
            ps.setInt(3, 1);
            ps.setInt(4, 1);
            ps.setBoolean(5, true);
            ps.setString(6, "01/06/2018");
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }




    private String consumeMedication(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("response", "success");
        return gson.toJson(map);
    }

}
/*
        List<pillbox> list = new ArrayList<pillbox>();

        try {
            Connection conn = DBConn.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM INVENTORY");
            String id = "1";
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                pillbox p = new pillbox();
                p.setInventory_id(rs.getString(1));
                p.setDrugintake_id(rs.getString(2));
                p.setDrugphase_id(rs.getString(3));
                p.setInventory_balance(rs.getString(4));
                p.setInventory_days(rs.getString(5));
                p.setInventory_startdate(rs.getString(6));
                p.setInventory_status(rs.getString(8));
                p.setDrug_ID(rs.getString(9));
                list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("test");
        System.out.println(gson.toJson(list));
        return gson.toJson(list);
* */