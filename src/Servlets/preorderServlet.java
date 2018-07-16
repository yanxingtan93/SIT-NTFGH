package Servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

        if(request.getParameter("action").equals("View")) {
            String mode = null;
            String date = null;
            String status = null;
            String ID = request.getParameter("preorder_ID");
            System.out.println("Servlet ID is: " + ID);

            ArrayList<Map> list = new ArrayList<>();

            System.out.println("preorderView.jsp");

            String sql = "SELECT d.drug_name, pd.quantity FROM PREORDERDRUGS pd, DRUGS d" +
                    " WHERE pd.preorder_ID = '"+ID+"' and pd.drug_ID = d.drug_ID";

            PreparedStatement ps = null;
            try {
                ps = DBConn.getPreparedStatement(sql);
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

            String sql2 = "SELECT * FROM PREORDER" +
                    " WHERE preorder_ID = '"+ID+"'";

            try {
                ps = DBConn.getPreparedStatement(sql2);
                ResultSet resultSet= ps.executeQuery();
                while (resultSet.next()){
                    mode = resultSet.getString("preorder_mode");
                    date = resultSet.getString("collection_date");
                    status = resultSet.getString("status");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String nextJSP = "/patient/preorderView.jsp";
            request.setAttribute("list", list);
            request.setAttribute("id", ID);
            request.setAttribute("mode", mode);
            request.setAttribute("date", date);
            request.setAttribute("status", status);

            System.out.print("\n"+ "rows --> " + list);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(ID != null) {

            }
//            request.setAttribute("preorder", list);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("preorderViewServlet");
//                dispatcher = getServletContext().getRequestDispatcher(nextJSP);


        }
        //String page = request.getParameter("action");

        if(request.getParameter("action").equals("Form")) {
            HttpSession sessions = request.getSession();
            String NRIC = sessions.getAttribute("userID").toString();
            String[] meds = request.getParameterValues("medicationPreorder");
            String quantity = request.getParameter("quantity");
            String mode = request.getParameter("method");
            String date = request.getParameter("date");
            String status = "Order Submitted";

            Preorder preorder = new Preorder();
            int amount = Integer.valueOf(quantity);
            if (date == null) {
                preorder = new Preorder(NRIC, mode, amount, status);
                preorder.addDeliveryPreorder();
            } else if (date != null) {
                preorder = new Preorder(NRIC, mode, amount, date, status);
                preorder.addCollectionPreorder();
            }

            for (int i = 0; i < meds.length; i++) {
                String medID = meds[i];
                //System.out.println("\n meds in list: " + medID);
//                String sql = "SELECT drug_ID FROM DRUGS " +
//                        "WHERE drug_name ='" + medName + "'";
//                PreparedStatement ps = null;
//                String medID = null;
//                try {
//                    ps = DBConn.getPreparedStatement(sql);
//                    ResultSet resultSet = ps.executeQuery();
//                    while (resultSet.next()) {
//                        medID = resultSet.getString(1);
//                        System.out.print("Result Set: " + resultSet.getString(1));
//                        System.out.println("Med ID:" + medID);
//                    }
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
                int preorderid = preorder.getPreorderID();
                System.out.println(preorderid);
                preorder.addPreorderDrugs(preorderid, medID);

            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String mode = request.getParameter("mode");

        switch (mode) {
            case "get":
                HttpSession sessions = request.getSession();
                String myNRIC = sessions.getAttribute("userID").toString();
                String NRIC = myNRIC;
//                String sql = "SELECT p.preorder_mode, d.drug_name, pd.quantity FROM PREORDER p, PREORDERDRUGS pd, DRUGS d " +
//                        "WHERE p.user_NRIC = '"+NRIC+"' AND p.preorder_ID = pd.preorder_ID AND pd.drug_ID = d.drug_id ";

//                String sql = "SELECT p.preorder_ID, p.preorder_mode, p.collection_date, p.status, d.drug_name, pd.quantity FROM PREORDER p, PREORDERDRUGS pd, DRUGS d WHERE p.user_NRIC ='"+NRIC+"' and pd.preorder_ID = p.preorder_ID and pd.drug_ID = d.drug_ID" ;

                String sql = "SELECT p.preorder_ID, p.preorder_mode, p.collection_date, p.status FROM PREORDER p WHERE p.user_NRIC ='"+NRIC+"' " ;
                try {
                    PreparedStatement ps = DBConn.getPreparedStatement(sql);
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
