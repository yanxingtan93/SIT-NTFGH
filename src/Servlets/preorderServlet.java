package Servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import DatabaseConnector.DBConn;
import com.google.gson.Gson;
import model.Medicine;
import model.Preorder;

import static DatabaseConnector.DBConn.getPreparedStatement;

@WebServlet(name = "preorderServlet")
public class preorderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        String page = request.getParameter("action");
        String ID = request.getParameter("preorder_ID");
        System.out.println("\n Servlet ID is: " + ID);

        ArrayList<Map> list = new ArrayList<>();
        if(page.equals("View") && ID != null) {
            System.out.println("preorderView.jsp");

            String sql = "SELECT d.drug_name, pd.quantity FROM PREORDERDRUGS pd, DRUGS d" +
                    " WHERE pd.preorder_ID = '"+ID+"' and pd.drug_ID = d.drug_ID";

            PreparedStatement ps = null;
            try {
                ps = getPreparedStatement(sql);
                ResultSet resultSet= ps.executeQuery();
                while (resultSet.next()){
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("drug_name", resultSet.getString("drug_name"));
                    map.put("quantity", resultSet.getString("quantity"));
                    map.put("preorder_ID", ID);

                    System.out.println(resultSet.getString("drug_name"));
                    System.out.println(resultSet.getString("quantity"));
                    System.out.println("\n");
                    list.add(map);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String nextJSP = "/patient/preorderView.jsp";
            request.setAttribute("list", list);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);

//            request.setAttribute("preorder", list);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("preorderViewServlet");
            //dispatcher = getServletContext().getRequestDispatcher(nextJSP);

            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        String NRIC = "S1234567A";
//        String[] meds = request.getParameterValues("medicationPreorder");
//        int quantity = Integer.valueOf(request.getParameter("quantity"));
//        String mode = request.getParameter("method");
//        String date = request.getParameter("date");
//
//        Preorder preorder = new Preorder();
//        if (date == null) {
//            preorder = new Preorder(NRIC, mode, quantity);
//            preorder.addDeliveryPreorder();
//        }
//        else if (date != null) {
//            preorder = new Preorder(NRIC, mode, quantity, date);
//            preorder.addCollectionPreorder();
//        }
//
//        for (int i = 0; i<meds.length; i++) {
//            String medName = meds[i];
//            String sql = "SELECT drug_ID FROM DRUGS " +
//                    "WHERE drug_name ='"+medName+"'";
//            PreparedStatement ps = null;
//            String medID = null;
//            try {
//                ps = getPreparedStatement(sql);
//                ResultSet resultSet= ps.executeQuery();
//                while (resultSet.next()) {
//                    medID = resultSet.getString(1);
//                    System.out.print("Result Set: "+ resultSet.getString(1));
//                    System.out.println("Med ID:" + medID);
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            int preorderid = preorder.getPreorderID();
//            System.out.println(preorderid);
//            preorder.addPreorderDrugs(preorderid, medID);
//
//        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String mode = request.getParameter("mode");

        switch (mode) {
            case "get":
                String NRIC = "S1234567A";
//                String sql = "SELECT p.preorder_mode, d.drug_name, pd.quantity FROM PREORDER p, PREORDERDRUGS pd, DRUGS d " +
//                        "WHERE p.user_NRIC = '"+NRIC+"' AND p.preorder_ID = pd.preorder_ID AND pd.drug_ID = d.drug_id ";

//                String sql = "SELECT p.preorder_ID, p.preorder_mode, p.collection_date, p.status, d.drug_name, pd.quantity FROM PREORDER p, PREORDERDRUGS pd, DRUGS d WHERE p.user_NRIC ='"+NRIC+"' and pd.preorder_ID = p.preorder_ID and pd.drug_ID = d.drug_ID" ;

                String sql = "SELECT p.preorder_ID, p.preorder_mode, p.collection_date, p.status FROM PREORDER p WHERE p.user_NRIC ='"+NRIC+"' " ;
                try {
                    PreparedStatement ps = getPreparedStatement(sql);
                    ResultSet resultSet = ps.executeQuery();

                    ArrayList<Map> list = new ArrayList<>();

                    while (resultSet.next()) {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("preorder_ID", resultSet.getString("preorder_ID"));
                        map.put("preorder_mode",resultSet.getString("preorder_mode"));
                        map.put("collection_date",resultSet.getString("collection_date"));
                        map.put("status",resultSet.getString("status"));

                        list.add(map);

                        System.out.println("ID: " + resultSet.getString("preorder_ID") + " mode: " +
                        resultSet.getString("preorder_mode") + " date: " +
                        resultSet.getString("collection_date")+ " status: " +
                        resultSet.getString("status")+ " drug_name: ");
                    }

                    String json = new Gson().toJson(list);
                    System.out.print("\n"+ "rows --> " + json);
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
