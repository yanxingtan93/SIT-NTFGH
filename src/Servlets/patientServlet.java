package Servlets;

import DatabaseConnector.DBConn;
import com.google.gson.Gson;

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

@WebServlet(name = "patientServlet")
public class patientServlet extends HttpServlet {
    private Gson gson = new Gson();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post");
        String route = request.getRequestURL().toString().split("/patient/")[1];
        //Sort the request by url i.e. /patient/listMedication
        switch (route) {
            case "addToPillbox":
                addToPillbox(request);
                response.sendRedirect("http://localhost:8080/patient/pillboxOverview.jsp");
                break;
            case "editPillbox":
                editPillbox(request);
                response.sendRedirect("http://localhost:8080/patient/pillboxOverview.jsp");
                break;
            case "deleteFromPillbox":
                deleteFromPillbox(request.getParameter("id"));
                response.sendRedirect("http://localhost:8080/patient/pillboxOverview.jsp");
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get");
        String route = request.getRequestURL().toString().split("/patient/")[1];

        //Sort the request by url i.e. /patient/listMedication
        switch (route) {
            case "getMedicationNames":
                response.getWriter().write(getMedicationNames());
                break;

            case "listPillbox":
                response.getWriter().write(listPillbox());
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
        String sql = "SELECT * FROM INVENTORY INNER JOIN DRUGS on INVENTORY.drug_ID = DRUGS.drug_ID INNER JOIN DRUGINTAKE on INVENTORY.drugintake_ID = DRUGINTAKE.drugintake_ID INNER JOIN DRUGPHASE on INVENTORY.drugphase_ID = DRUGPHASE.drugphase_ID";
        ArrayList<Map> list = new ArrayList<>();
        try {
            PreparedStatement ps = DBConn.getPreparedStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("inventory_ID", resultSet.getString("inventory_ID"));
                map.put("drug_brand", resultSet.getString("drug_brand"));
                map.put("drugintake_term", resultSet.getString("drugintake_term"));
                map.put("drugphase_term", resultSet.getString("drugphase_term"));
                map.put("drug_description", resultSet.getString("drug_description"));
                map.put("drug_side_effect", resultSet.getString("drug_side_effect"));


                map.put("drug_name", resultSet.getString("drug_name"));
                map.put("inventory_balance", resultSet.getString("inventory_balance"));
                map.put("dose", resultSet.getString("dose"));
                map.put("drugintake_ID", resultSet.getString("drugintake_ID"));
                map.put("frequency", resultSet.getString("frequency"));
                map.put("drugphase_ID", resultSet.getString("drugphase_ID"));
                map.put("instructions", resultSet.getString("instructions"));
                map.put("strictness", resultSet.getString("strictness"));
                map.put("inventory_startdate", resultSet.getString("inventory_startdate"));

                list.add(map);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return gson.toJson(list);
    }

    private void addToPillbox(HttpServletRequest request){

        System.out.println(request.getParameter("addDrugQuantity"));//
        System.out.println(request.getParameter("addDrugDose"));//
        System.out.println(request.getParameter("addDrugMeals"));
        System.out.println(request.getParameter("addDrugFrequency"));
        System.out.println(request.getParameter("addDrugInterval"));
        System.out.println(request.getParameter("addDrugInstructions"));//
        System.out.println(request.getParameter("addDrugStrictness"));
        System.out.println(request.getParameter("addDrugStartDate"));

        String sql = "INSERT INTO INVENTORY(drug_ID, drugintake_ID,drugphase_ID,inventory_balance,inventory_status,inventory_startdate,dose,instructions,frequency,strictness) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?);";

        try {
            PreparedStatement ps = DBConn.getPreparedStatement(sql);
            ps.setInt(1, Integer.valueOf(request.getParameter("addDrugName")));
            ps.setInt(2, Integer.valueOf(request.getParameter("addDrugMeals")));
            ps.setInt(3, Integer.valueOf(request.getParameter("addDrugInterval")));
            ps.setInt(4, Integer.valueOf(request.getParameter("addDrugQuantity")));
            ps.setBoolean(5, true);
            ps.setString(6, request.getParameter("addDrugStartDate"));
            ps.setInt(7, Integer.valueOf(request.getParameter("addDrugDose")));
            ps.setString(8, request.getParameter("addDrugInstructions"));
            ps.setInt(9, Integer.valueOf(request.getParameter("addDrugFrequency")));

            if (request.getParameter("addDrugStrictness").equalsIgnoreCase("true")){
                ps.setBoolean(10, true);
            }
            else{
                ps.setBoolean(10, false);
            }

            System.out.println(ps.toString());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void editPillbox(HttpServletRequest request){


        System.out.println(request.getParameter("editDrugQuantity"));//
        System.out.println(request.getParameter("editDrugDose"));//
        System.out.println(request.getParameter("editDrugMeals"));
        System.out.println(request.getParameter("editDrugFrequency"));
        System.out.println(request.getParameter("editDrugInterval"));
        System.out.println(request.getParameter("editDrugName"));

        String sql = "UPDATE INVENTORY SET inventory_balance=?,dose=?,drugintake_ID=?,frequency=?,drugphase_ID=? "+
                "WHERE inventory_ID = ?;";
        //String sql = "UPDATE INVENTORY SET inventory_balance=?,dose=?,drugintake_ID=?,frequency=?,drugphase_ID=? "+
          //      "WHERE inventory_ID = ?;";


        try {
            PreparedStatement ps = DBConn.getPreparedStatement(sql);

            ps.setInt(1, Integer.valueOf(request.getParameter("editDrugQuantity")));
            ps.setInt(2, Integer.valueOf(request.getParameter("editDrugDose")));
            ps.setInt(3, Integer.valueOf(request.getParameter("editDrugMeals")));
            ps.setInt(4, Integer.valueOf(request.getParameter("editDrugFrequency")));
            ps.setInt(5, Integer.valueOf(request.getParameter("editDrugInterval")));
            ps.setInt(6, Integer.valueOf(request.getParameter("editDrugName")));
            System.out.println(ps.toString());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteFromPillbox(String id){
        String sql = "DELETE FROM INVENTORY WHERE inventory_ID="+id;
        try {
            PreparedStatement ps = DBConn.getPreparedStatement(sql);
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