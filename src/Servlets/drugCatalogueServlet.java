package Servlets;

import DBUtils.DBConn;
import com.google.gson.Gson;
import model.Medicine;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


@WebServlet(name = "drugCatalogueServlet")
public class drugCatalogueServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String mode = request.getParameter("mode");
        String medicineID = request.getParameter("drugid");
        System.out.println("---> " + medicineID);

        if(mode.equals("Edit")) {
            System.out.println("In Editting mode of medEdit.jsp");

            String nextJSP = "/pharmacist/medicationEdit.jsp";
            String sql = "SELECT d.drug_id,d.drug_name,d.drug_brand,d.drug_price," +
                    "d.medicineform_ID,d.drug_description,d.drug_side_effect,m.medicineform_name FROM DRUGS d,MEDICINEFORM m " +
                    "WHERE d.drug_ID = " + medicineID.trim() + " AND d.medicineform_ID = m.medicineform_ID";


            try {
                PreparedStatement ps = DBConn.getPreparedStatement(sql);
                ResultSet resultSet = ps.executeQuery();

                ArrayList<Medicine> list = new ArrayList<Medicine>();

                while (resultSet.next()) {
                    Medicine medicineCat = new Medicine();
                    medicineCat.setId(resultSet.getInt("drug_ID"));
                    medicineCat.setMedicineName(resultSet.getString("drug_name"));
                    medicineCat.setBrand(resultSet.getString("drug_brand"));
                    medicineCat.setPrice(resultSet.getFloat("drug_price"));
                    medicineCat.setMedicineFormId(resultSet.getInt("medicineform_ID"));
                    medicineCat.setDescription(resultSet.getString("drug_description"));
                    medicineCat.setSideEffect(resultSet.getString("drug_side_effect"));
                    medicineCat.setMedicineForm(resultSet.getString("medicineform_name"));
                    list.add(medicineCat);
                }

                request.setAttribute("list", list);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
                dispatcher.forward(request, response);

            } catch (SQLException ex) {
                ex.printStackTrace();

            }
        }




        else if(mode.equals("Save")) {

            System.out.println("In Saving mode of medEdit.jsp");

            String medicineName= request.getParameter("drug_name");
            String brand = request.getParameter("drug_brand");
            String priceStr = request.getParameter("drug_price");
            float price = Float.valueOf(priceStr);
            int medicineFormId = Integer.valueOf(request.getParameter("medicineForm"));
            String description = request.getParameter("drug_desc");
            String drugSideEffect = request.getParameter("drug_sideEffects");
            Medicine newMedicine = new Medicine(medicineName,brand,price,medicineFormId,description,drugSideEffect);
            newMedicine.updateMedicine(Integer.parseInt(medicineID));


            String nextJSP = "/pharmacist/medicationOverview.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);

        }

        else if(mode.equals("Delete")) {

            System.out.println("In Delete mode of medEdit.jsp");

            Medicine.deleteMedicine(Integer.parseInt(medicineID));


            String nextJSP = "/pharmacist/medicationOverview.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);

        }




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sql = "SELECT d.drug_id,d.drug_name,d.drug_brand,d.drug_price," +
                "d.medicineform_ID,d.drug_description,d.drug_side_effect,m.medicineform_name FROM DRUGS d,MEDICINEFORM m " +
                "WHERE d.medicineform_ID = m.medicineform_ID";


        try {
            PreparedStatement ps = DBConn.getPreparedStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            int count = 0;

           // Map<String, Medicine> options = new LinkedHashMap<>();
          //  ArrayList<ArrayList<String>> list = new ArrayList<>();
            ArrayList<Medicine> list = new ArrayList<>();

            while (resultSet.next()) {

                Medicine medicineCat = new Medicine();
                medicineCat.setId(resultSet.getInt("drug_ID"));
                medicineCat.setMedicineName(resultSet.getString("drug_name"));
                medicineCat.setBrand(resultSet.getString("drug_brand"));
                medicineCat.setPrice(resultSet.getFloat("drug_price"));
                medicineCat.setMedicineFormId(resultSet.getInt("medicineform_ID"));
                medicineCat.setDescription(resultSet.getString("drug_description"));
                medicineCat.setSideEffect(resultSet.getString("drug_side_effect"));
                medicineCat.setMedicineForm(resultSet.getString("medicineform_name"));
                //System.out.println("TWo prices");
/*
                ArrayList<String> xs = new ArrayList<>();
                xs.add(resultSet.getInt("drug_ID")+"");
                xs.add(resultSet.getString("drug_name")+"");
                xs.add(resultSet.getString("drug_brand")+"");
                xs.add(resultSet.getFloat("drug_price")+"");
                xs.add(resultSet.getInt("medicineform_ID")+"");
                xs.add(resultSet.getString("drug_description")+"");
                xs.add(resultSet.getString("drug_side_effect")+"");
                xs.add(resultSet.getString("medicineform_name")+"");

*/

                list.add(medicineCat);
              //  options.put(count+"",medicineCat);
                count++;
            }

            /*
            String nextJSP = "/pharmacist/medicationOverview.jsp";
           request.setAttribute("list", list);
           request.getRequestDispatcher("/pharmacist/medicationOverview.jsp").forward(request, response);

          RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
          dispatcher.forward(request,response);

*/
            String json = new Gson().toJson(list);
            System.out.print(count+" rows --> "+json);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);



        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }




    private void doPerform(HttpServletRequest request,HttpServletResponse response) {

    }

}
