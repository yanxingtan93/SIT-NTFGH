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
        String mode = request.getParameter("mode");
        String medicineID = request.getParameter("drugid");
        System.out.println("---> " + medicineID);

        if(mode.equals("Edit")) {
            System.out.println("In Editting mode of medEdit.jsp");
            String nextJSP = "/pharmacist/medicationEdit.jsp";
            ArrayList<Medicine> list = drugsDao.getAllDrugEdit(medicineID);
            request.setAttribute("list", list);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);

            }





        else if(mode.equals("Save")) {

            System.out.println("In Saving mode of medEdit.jsp");

            String medicineName= request.getParameter("drug_name");
            String brand = request.getParameter("drug_brand");
            int medicineFormId = Integer.valueOf(request.getParameter("medicineForm"));
            String description = request.getParameter("drug_desc");
            String drugSideEffect = request.getParameter("drug_sideEffects");
            Medicine newMedicine = new Medicine(medicineName,brand,medicineFormId,description,drugSideEffect);
            drugsDao.updateDrug(newMedicine);
            String nextJSP = "/pharmacist/medicationOverview.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);

        }

        else if(mode.equals("Delete")) {

            System.out.println("In Delete mode of medEdit.jsp");
           drugsDao.deleteDrug(Integer.parseInt(medicineID));
            String nextJSP = "/pharmacist/medicationOverview.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);

        }




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String route  = request.getParameter("route");
        DrugsDao drugDao = new DrugDaoImpl();
        int count = 0;
        ArrayList<Medicine> list = drugDao.getAllDrugs();
        String json = new Gson().toJson(list);
        //System.out.print(count+" rows --> "+json);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);


    }




    private void doPerform(HttpServletRequest request,HttpServletResponse response) {

    }

}
