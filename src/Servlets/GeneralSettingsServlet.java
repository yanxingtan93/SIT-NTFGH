package Servlets;

import DatabaseConnector.DBConn;
import DatabaseConnector.MedicineFormDao;
import DatabaseConnector.MedicineFormDaoImpl;
import com.google.gson.Gson;
import model.DrugIntake;
import model.DrugPhase;
import model.MedicineForm;

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

@WebServlet(name = "GeneralSettingsServlet")
public class GeneralSettingsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String route = request.getParameter("route");
        int settingID = 0;
        switch (route){
            case "medForm":
                String name = request.getParameter("name");
                MedicineForm medicineForm = new MedicineForm();
                medicineForm.setFormName(name);
                MedicineFormDaoImpl medicineFormDao= new MedicineFormDaoImpl();
                medicineFormDao.addNewMedicineForm(medicineForm);
                response.sendRedirect("/admin/settingsOverview.jsp");
                break;
            case "phase":
                String phase = request.getParameter("name");
                DrugPhase.addPhase(phase);
                response.sendRedirect("/admin/settingsOverview.jsp");
                break;
            case "intake":
                String intake = request.getParameter("name");
                DrugIntake.addIntake(intake);
                response.sendRedirect("/admin/settingsOverview.jsp");
                break;

            case "DeleteForm":
                settingID = Integer.parseInt(request.getParameter("settingID"));
                MedicineFormDao mfd1 = new MedicineFormDaoImpl();
                ((MedicineFormDaoImpl) mfd1).deleteForm(settingID,1);
                response.sendRedirect("/admin/settingsOverview.jsp");
                break;

            case "DeletePhase":
                settingID = Integer.parseInt(request.getParameter("settingID"));
                MedicineFormDao mfd2 = new MedicineFormDaoImpl();
                ((MedicineFormDaoImpl) mfd2).deleteForm(settingID,2);
                response.sendRedirect("/admin/settingsOverview.jsp");
                break;

            case "DeleteIntake":
                settingID = Integer.parseInt(request.getParameter("settingID"));
                MedicineFormDao mfd3 = new MedicineFormDaoImpl();
                ((MedicineFormDaoImpl) mfd3).deleteForm(settingID,3);
                response.sendRedirect("/admin/settingsOverview.jsp");
                break;

            case "SavePhase":
                String formID = request.getParameter("formID");
                String formName = request.getParameter("form_name");

                MedicineForm mf = new MedicineForm();
                mf.setFormName(formName);
                mf.setId(Integer.parseInt(formID));

                MedicineFormDao mfd4 = new MedicineFormDaoImpl();
                mfd4.updateMedicineForm(mf,2);
                response.sendRedirect("/admin/settingsOverview.jsp");
                break;


            case "SaveForm":
                String formID2 = request.getParameter("formID");
                String formName2 = request.getParameter("form_name");

                MedicineForm mf2 = new MedicineForm();
                mf2.setFormName(formName2);
                mf2.setId(Integer.parseInt(formID2));

                MedicineFormDao mfd5 = new MedicineFormDaoImpl();
                mfd5.updateMedicineForm(mf2,1);
                response.sendRedirect("/admin/settingsOverview.jsp");
                break;


            case "SaveIntake":
                String formID3 = request.getParameter("formID");
                String formName3 = request.getParameter("form_name");

                MedicineForm mf3 = new MedicineForm();
                mf3.setFormName(formName3);
                mf3.setId(Integer.parseInt(formID3));
                System.out.println(mf3.getFormName()+" -- "+mf3.getId());
                MedicineFormDao mfd6 = new MedicineFormDaoImpl();
                mfd6.updateMedicineForm(mf3,3);
                response.sendRedirect("/admin/settingsOverview.jsp");
                break;

            default:
                System.out.println("DEFAULT in generalsettings");
                break;




        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");


        switch(mode) {
            case "medForms":
                String sql = "SELECT * from MEDICINEFORM";

                try {
                    PreparedStatement ps = DBConn.getPreparedStatement(sql);
                    ResultSet resultSet = ps.executeQuery();

                    ArrayList<MedicineForm> list = new ArrayList<MedicineForm>();

                    while (resultSet.next()) {
                        MedicineForm medForm = new MedicineForm();
                        medForm.setId(resultSet.getInt("medicineform_ID"));
                        medForm.setFormName(resultSet.getString("medicineform_name"));
                        list.add(medForm);
                    }

                    String json = new Gson().toJson(list);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);


                } catch (SQLException ex) {
                    ex.printStackTrace();

                }
                break;


            case "phase":
                String sql1 = "SELECT * from DRUGPHASE";

                try {
                    PreparedStatement ps1 = DBConn.getPreparedStatement(sql1);
                    ResultSet resultSet = ps1.executeQuery();

                    ArrayList<DrugPhase> list = new ArrayList<DrugPhase>();

                    while (resultSet.next()) {
                        DrugPhase drugPhase = new DrugPhase();
                        drugPhase.setId(resultSet.getInt("drugphase_ID"));
                        drugPhase.setPhaseTerm(resultSet.getString("drugphase_term"));
                        list.add(drugPhase);
                    }

                    String json = new Gson().toJson(list);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);


                } catch (SQLException ex) {
                    ex.printStackTrace();

                }
                break;


            case "intake":
                String sql2 = "SELECT * from DRUGINTAKE";

                try {
                    PreparedStatement ps2 = DBConn.getPreparedStatement(sql2);
                    ResultSet resultSet = ps2.executeQuery();

                    ArrayList<DrugIntake> list = new ArrayList<DrugIntake>();

                    while (resultSet.next()) {
                        DrugIntake drugIntake = new DrugIntake();
                        drugIntake.setId(resultSet.getInt("drugintake_ID"));
                        drugIntake.setIntakeTerm(resultSet.getString("drugintake_term"));
                        list.add(drugIntake);
                    }

                    String json = new Gson().toJson(list);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);


                } catch (SQLException ex) {
                    ex.printStackTrace();

                }
                break;


            case "medForm":
                int type = Integer.parseInt(request.getParameter("type"));
                int id = Integer.parseInt(request.getParameter(("id")));
                MedicineFormDao mfd = new MedicineFormDaoImpl();

                String name ="";
                if(type==1){
                     name  = mfd.getMedicineFormName(id);
                }

                else if(type==2){
                    name  = mfd.getMedicinePhaseName(id);
                }
                else if(type==3){
                    name  = mfd.getMedicineIntakeName(id);
                }
                System.out.println("TYPE FORM 3: NAME: "+name);
                String json = new Gson().toJson(name);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);


                break;




        }//End of switch
    }
}
