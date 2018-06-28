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

import DBUtils.DBConn;
import com.google.gson.Gson;
import model.Medicine;
import model.Preorder;

@WebServlet(name = "preorderServlet")
public class preorderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String NRIC = "S1234567A";
        int medID = Integer.valueOf(request.getParameter("medicationPreorder"));
        //String quantity = request.getParameter("quantity");
        int quantity = Integer.valueOf(request.getParameter("quantity"));
        String mode = request.getParameter("method");
        System.out.println(medID);
        System.out.println(mode);

        Preorder preorder = new Preorder(NRIC, mode, medID, quantity);
        preorder.addPreorder();
        int preorderid = preorder.getPreorderID();
        System.out.println(preorderid);
        preorder.addPreorderDrugs(preorderid);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");

        switch (mode) {
            case "get":
                String NRIC = "S1234567A";
                String sql = "SELECT p.preorder_mode, d.drug_name, pd.quantity FROM PREORDER p, PREORDERDRUGS pd, DRUGS d " +
                        "WHERE p.user_NRIC = '"+NRIC+"' AND p.preorder_ID = pd.preorder_ID AND pd.drug_ID = d.drug_id ";

                try {
                    PreparedStatement ps = DBConn.getPreparedStatement(sql);
                    ResultSet resultSet = ps.executeQuery();

                    ArrayList<Preorder> plist = new ArrayList<Preorder>();
                    while (resultSet.next()) {
                        //System.out.println(resultSet.getString(1)+ "" + resultSet.getString(2) + "" + resultSet.getString(3));
                        Preorder drug = new Preorder();
                        drug.setMode(resultSet.getString(1));
                        drug.setDrugname(resultSet.getString(2));
                        drug.setQuantity(resultSet.getInt(3));
                        plist.add(drug);
                    }

                    String json = new Gson().toJson(plist);
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
