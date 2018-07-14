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
        String patientNRIC = request.getParameter("patientList");
        System.out.println("\n patient selected: " + patientNRIC);

        String sql = "SELECT p.preorder_ID, p.preorder_mode, p.collection_date, p.status FROM PREORDER p WHERE p.user_NRIC ='"+patientNRIC+"' " ;
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
            }
            String nextJSP = "/pharmacist/preorder.jsp";
            request.setAttribute("list", list);
            System.out.print("\n"+ "rows --> " + list);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);

            String json = new Gson().toJson(patientNRIC);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        } catch (SQLException e) {
            e.printStackTrace();
        }




        String action = request.getParameter("action");

        switch (action) {
//            case "pharmView":
//                String ID = request.getParameter("preorder_ID");
//                System.out.println("Servlet ID is: " + ID);
//
//                ArrayList<Map> list = new ArrayList<>();
//
//                System.out.println("pharmacistPreorder");
//
//                String sql = "SELECT d.drug_name, pd.quantity FROM PREORDERDRUGS pd, DRUGS d" +
//                        " WHERE pd.preorder_ID = '" + ID + "' and pd.drug_ID = d.drug_ID";
//
//                PreparedStatement ps = null;
//                try {
//                    ps = DBConn.getPreparedStatement(sql);
//                    ResultSet resultSet = ps.executeQuery();
//                    while (resultSet.next()) {
//                        Map<String, String> map = new HashMap<String, String>();
//                        map.put("drug_name", resultSet.getString(1));
//                        map.put("quantity", resultSet.getString(2));
//                        map.put("ID", ID);
//
//                        System.out.println(resultSet.getString(1));
//                        System.out.println(resultSet.getString(2));
//                        System.out.println("\n");
//                        list.add(map);
//                    }
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//
//                String nextJSP = "/pharmacist/preorder.jsp";
//                request.setAttribute("list", list);
//                System.out.print("\n" + "rows --> " + list);
//                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
//                try {
//                    dispatcher.forward(request, response);
//                } catch (ServletException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

//            request.setAttribute("preorder", list);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("preorderViewServlet");
//                dispatcher = getServletContext().getRequestDispatcher(nextJSP);
//                break;
            case "":

                break;

        }
    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
