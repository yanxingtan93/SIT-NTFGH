package Servlets;

import DatabaseConnector.DBConn;
import DatabaseConnector.DrugDaoImpl;
import DatabaseConnector.DrugsDao;
import com.google.gson.Gson;
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
        DrugsDao drugsDao = new DrugDaoImpl();
        String mode = request.getParameter("route");

        String medicineID = request.getParameter("drugid");


        if(mode.equals("Save")) {

            System.out.println("In Saving mode of medEdit.jsp");

            String id = request.getParameter("drugid");
            String medicineName= request.getParameter("drug_name");
            String brand = request.getParameter("drug_brand");
            int medicineFormId = Integer.valueOf(request.getParameter("medicineForm"));
            String description = request.getParameter("drug_desc");
            String drugSideEffect = request.getParameter("drug_sideEffects");
            Medicine newMedicine = new Medicine(Integer.parseInt(id),medicineName,brand,medicineFormId,description,drugSideEffect);
            drugsDao.updateDrug(newMedicine);
            response.sendRedirect("http://localhost:8080/pharmacist/medicationOverview.jsp");

        }

        else if(mode.equals("Delete")) {

            System.out.println("In Delete mode of medEdit.jsp");
           drugsDao.deleteDrug(Integer.parseInt(medicineID));
            response.sendRedirect("http://localhost:8080/pharmacist/medicationOverview.jsp");
        }




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String route  = request.getParameter("route");

        switch (route) {


            case "all":

            DrugsDao drugDao = new DrugDaoImpl();
            int count = 0;
            ArrayList<Medicine> list = drugDao.getAllDrugs();
            String json = new Gson().toJson(list);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            break;

            case "individual":

                String id = request.getParameter("id");
                DrugsDao drugDao2 = new DrugDaoImpl();
                ArrayList<Medicine> oneList = drugDao2.getAllDrugEdit(id);
                String json1 = new Gson().toJson(oneList);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json1);
                break;


        }
    }




    private void doPerform(HttpServletRequest request,HttpServletResponse response) {

    }

}
