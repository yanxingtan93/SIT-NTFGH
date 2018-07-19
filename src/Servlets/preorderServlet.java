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

            ArrayList<Map> list = new ArrayList<>();

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

        HttpSession sessions = request.getSession();
        String NRIC = null;
        if(request.getParameter("action").equals("Form")) {
            if (request.getParameter("role").equals("Caregiver")) {
                NRIC = sessions.getAttribute("patientID").toString();
            }
            else if (request.getParameter("role").equals("Patient")) {
                NRIC = sessions.getAttribute("userID").toString();
            }


            /*String patientNRIC = sessions.getAttribute("patientID").toString();
            System.out.println("preorder NRIC of user "+patientNRIC);
            if(patientNRIC.equals("")){
                System.out.println("Empty "+patientNRIC);
            }
            else if(patientNRIC==null){
                System.out.println("Null "+patientNRIC);
            }
*/
            //String NRIC = sessions.getAttribute("userID").toString();
            String[] meds = request.getParameterValues("medicationPreorder");
            String quantity = request.getParameter("quantity");
            String mode = request.getParameter("method");
            String date = request.getParameter("date");
            String status = "Order Submitted";


            Preorder preorder = new Preorder();
            int amount = Integer.valueOf(quantity);
            if (date == null) {
                String na = "n.a.";
                preorder = new Preorder(NRIC, mode, amount, na, status);
                preorder.addPreorder();
            } else if (date != null) {
                preorder = new Preorder(NRIC, mode, amount, date, status);
                preorder.addPreorder();
            }

            for (int i = 0; i < meds.length; i++) {
                String medID = preorder.getID(meds[i]);
                int preorderid = preorder.getPreorderID();
                System.out.println(preorderid);
                preorder.addPreorderDrugs(preorderid, medID);

            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String mode = request.getParameter("mode");

        switch (mode) {
            case "getUser":
                HttpSession sessions = request.getSession();
                String myNRIC = sessions.getAttribute("userID").toString();

                String sql = "SELECT preorder_ID, preorder_mode, collection_date, status FROM PREORDER WHERE user_NRIC ='"+myNRIC+"'";
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

                        System.out.println("ID: " + resultSet.getString("preorder_ID") + " mode: " +
                                resultSet.getString("preorder_mode") + " date: " +
                                resultSet.getString("collection_date")+ " status: " +
                                resultSet.getString("status"));

                        list.add(map);
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

            case "getPatient":

                HttpSession session2 = request.getSession();
                String patNRIC = session2.getAttribute("patientID").toString();

                String sql2 = "SELECT preorder_ID, preorder_mode, collection_date, status FROM PREORDER WHERE user_NRIC ='"+patNRIC+"'";
                try {
                    PreparedStatement ps = DBConn.getPreparedStatement(sql2);
                    ResultSet resultSet = ps.executeQuery();

                    ArrayList<Map> list = new ArrayList<>();

                    while (resultSet.next()) {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("preorder_ID", resultSet.getString("preorder_ID"));
                        map.put("preorder_mode",resultSet.getString("preorder_mode"));
                        map.put("collection_date",resultSet.getString("collection_date"));
                        map.put("status",resultSet.getString("status"));

                        System.out.println("ID: " + resultSet.getString("preorder_ID") + " mode: " +
                                resultSet.getString("preorder_mode") + " date: " +
                                resultSet.getString("collection_date")+ " status: " +
                                resultSet.getString("status"));

                        list.add(map);
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
