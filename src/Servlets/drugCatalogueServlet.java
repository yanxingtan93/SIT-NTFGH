package Servlets;

import DBUtils.DBConn;
import model.Medicine;

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


@WebServlet(name = "drugCatalogueServlet")
public class drugCatalogueServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        String medicineID= request.getParameter("drug_id");
        System.out.println("---> "+medicineID);
       // String nextJSP = "/pharmacist/medicationEdit.jsp";
       // RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
       // dispatcher.forward(request,response);
      //  response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sql = "SELECT * FROM DRUGS";


        try {
            PreparedStatement ps = DBConn.getPreparedStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            ArrayList<Medicine> list = new ArrayList<Medicine>();
            // (int id, String medicineName, String brand, float price, int medicineFormId, String description, String sideEffect)ff
            while (resultSet.next()) {
                Medicine medicineCat = new Medicine();
                medicineCat.setId(resultSet.getInt("drug_ID"));
                medicineCat.setMedicineName(resultSet.getString("drug_name"));
                medicineCat.setBrand(resultSet.getString("drug_brand"));
                medicineCat.setPrice(resultSet.getFloat("drug_price"));
                medicineCat.setMedicineFormId(resultSet.getInt("medicineform_ID"));
                medicineCat.setDescription(resultSet.getString("drug_description"));
                medicineCat.setSideEffect(resultSet.getString("drug_side_effect"));
                //System.out.println("TWo prices");
                list.add(medicineCat);
            }
            String nextJSP = "/pharmacist/medicationOverview.jsp";
            request.setAttribute("list", list);
            //request.getRequestDispatcher("/pharmacist/medicationOverview.jsp").forward(request, response);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request,response);

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }




    private void doPerform(HttpServletRequest request,HttpServletResponse response) {

    }

}
