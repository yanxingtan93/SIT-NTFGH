package Servlets;

import model.Medicine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="drugServlet")
public class drugServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       String medicineName= request.getParameter("medicineName");
       String brand = request.getParameter("brand");
       int medicineFormId = Integer.valueOf(request.getParameter("medicineForm"));
       String description = request.getParameter("drugDescription");
       String drugSideEffect = request.getParameter("drugSideEffect");
       Medicine newMedicine = new Medicine(medicineName,brand,medicineFormId,description,drugSideEffect);
       newMedicine.addNewMedicine();



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
