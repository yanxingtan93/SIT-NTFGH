package Servlets;

import DatabaseConnector.DBConn;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@WebServlet(name = "patientServlet")
public class patientServlet extends HttpServlet {
    private Gson gson = new Gson();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post");
        String route = request.getRequestURL().toString().split("/patient/")[1];
        //Sort the request by url i.e. /patient/listMedication
        switch (route) {
            case "addToPillbox":
                addToPillbox(request);
                response.sendRedirect("http://localhost:8080/patient/pillboxOverview.jsp");
                break;
            case "editPillbox":
                editPillbox(request);
                response.sendRedirect("http://localhost:8080/patient/pillboxOverview.jsp");
                break;
            case "deleteFromPillbox":
                deleteFromPillbox(request.getParameter("id"));
                response.sendRedirect("http://localhost:8080/patient/pillboxOverview.jsp");
                break;
            case "recordConsumption":
                recordConsumption(request.getParameter("id"),request.getParameter("remainder"), request.getParameter("inventoryID"));
                response.sendRedirect("http://localhost:8080/patient/pillboxOverview.jsp");
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get");
        String route = request.getRequestURL().toString().split("/patient/")[1];

        //Sort the request by url i.e. /patient/listMedication
        switch (route) {
            case "getMedicationNames":
                response.getWriter().write(getMedicationNames());
                break;
            case "getMedicationForms":
                response.getWriter().write(getMedicationForms());
                break;
            case "listPillbox":
                response.getWriter().write(listPillbox());
                break;
        }
    }

    private String getMedicationNames(){
        String sql = "SELECT * FROM DRUGS";
        ArrayList<Map> list = new ArrayList<>();
        try {
            PreparedStatement ps = DBConn.getPreparedStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("drug_ID", resultSet.getString("drug_ID"));
                map.put("drug_name", resultSet.getString("drug_name"));
                list.add(map);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return gson.toJson(list);
    }

    private String getMedicationForms(){
        String sql = "SELECT * FROM MEDICINEFORM";
        ArrayList<Map> list = new ArrayList<>();
        try {
            PreparedStatement ps = DBConn.getPreparedStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("medicineform_ID", resultSet.getString("medicineform_ID"));
                map.put("medicineform_name", resultSet.getString("medicineform_name"));
                list.add(map);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return gson.toJson(list);
    }
    private String listPillbox(){
        //TODO:Modify for user NRIC
        String sql = "SELECT * FROM INVENTORY INNER JOIN DRUGS on INVENTORY.drug_ID = DRUGS.drug_ID INNER JOIN DRUGINTAKE on INVENTORY.drugintake_ID = DRUGINTAKE.drugintake_ID INNER JOIN DRUGPHASE on INVENTORY.drugphase_ID = DRUGPHASE.drugphase_ID INNER JOIN REMINDERS on INVENTORY.inventory_ID = REMINDERS.inventory_ID INNER JOIN MEDICINEFORM on INVENTORY.medicineform_ID = MEDICINEFORM.medicineform_ID WHERE consumed=0";
        /*
        String sql = "SELECT *\n" +
                "FROM\n" +
                "  (SELECT DISTINCT inventory_ID, reminder_time\n" +
                "  FROM\n" +
                "    (   SELECT inventory_ID, reminder_time, ROW_NUMBER() over(partition by inventory_ID order by reminder_time) Orden\n" +
                "        FROM REMINDERS\n" +
                "    ) A\n" +
                "  WHERE A.Orden = 1) R\n" +
                "INNER JOIN INVENTORY on INVENTORY.inventory_ID = R.inventory_ID\n" +
                "INNER JOIN DRUGS on INVENTORY.drug_ID = DRUGS.drug_ID\n" +
                "INNER JOIN DRUGINTAKE on INVENTORY.drugintake_ID = DRUGINTAKE.drugintake_ID\n" +
                "INNER JOIN DRUGPHASE on INVENTORY.drugphase_ID = DRUGPHASE.drugphase_ID";*/

        LocalDate today = LocalDate.now();
        LocalDate test;
        ArrayList<Map> todayList = new ArrayList<>();
        ArrayList<Map> pillboxList = new ArrayList<>();
        Map<String, ArrayList<Map>> dataMap = new HashMap<String, ArrayList<Map>>();
        try {
            PreparedStatement ps = DBConn.getPreparedStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Map<String, String> todayMap = new HashMap<String, String>();
                test = LocalDateTime.parse(resultSet.getString("reminder_time")).toLocalDate();
                if (today.toString().equalsIgnoreCase(test.toString())){
                    todayMap.put("inventory_ID", resultSet.getString("inventory_ID"));
                    todayMap.put("drug_brand", resultSet.getString("drug_brand"));
                    todayMap.put("drugintake_term", resultSet.getString("drugintake_term"));
                    todayMap.put("drugphase_term", resultSet.getString("drugphase_term"));
                    todayMap.put("drug_description", resultSet.getString("drug_description"));
                    todayMap.put("drug_side_effect", resultSet.getString("drug_side_effect"));

                    todayMap.put("drug_name", resultSet.getString("drug_name"));
                    todayMap.put("inventory_balance", resultSet.getString("inventory_balance"));
                    todayMap.put("dose", resultSet.getString("dose"));
                    todayMap.put("drugintake_ID", resultSet.getString("drugintake_ID"));
                    todayMap.put("frequency", resultSet.getString("frequency"));
                    todayMap.put("drugphase_ID", resultSet.getString("drugphase_ID"));
                    todayMap.put("instructions", resultSet.getString("instructions"));
                    todayMap.put("strictness", resultSet.getString("strictness"));
                    todayMap.put("inventory_startdate", resultSet.getString("inventory_startdate"));
                    todayMap.put("reminder_time", resultSet.getString("reminder_time"));
                    todayMap.put("reminder_ID", resultSet.getString("reminder_ID"));
                    todayMap.put("medicineform_name", resultSet.getString("medicineform_name"));
                    todayList.add(todayMap);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

        sql = "SELECT * FROM INVENTORY INNER JOIN DRUGS on INVENTORY.drug_ID = DRUGS.drug_ID INNER JOIN DRUGINTAKE on INVENTORY.drugintake_ID = DRUGINTAKE.drugintake_ID INNER JOIN DRUGPHASE on INVENTORY.drugphase_ID = DRUGPHASE.drugphase_ID INNER JOIN MEDICINEFORM on INVENTORY.medicineform_ID = MEDICINEFORM.medicineform_ID";
        try {
            PreparedStatement ps = DBConn.getPreparedStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Map<String, String> map = new HashMap<String, String>();
                    map.put("inventory_ID", resultSet.getString("inventory_ID"));
                    map.put("drug_brand", resultSet.getString("drug_brand"));
                    map.put("drugintake_term", resultSet.getString("drugintake_term"));
                    map.put("drugphase_term", resultSet.getString("drugphase_term"));
                    map.put("drug_description", resultSet.getString("drug_description"));
                    map.put("drug_side_effect", resultSet.getString("drug_side_effect"));

                    map.put("drug_name", resultSet.getString("drug_name"));
                    map.put("inventory_balance", resultSet.getString("inventory_balance"));
                    map.put("dose", resultSet.getString("dose"));
                    map.put("drugintake_ID", resultSet.getString("drugintake_ID"));
                    map.put("frequency", resultSet.getString("frequency"));
                    map.put("drugphase_ID", resultSet.getString("drugphase_ID"));
                    map.put("instructions", resultSet.getString("instructions"));
                    map.put("strictness", resultSet.getString("strictness"));
                    map.put("inventory_startdate", resultSet.getString("inventory_startdate"));
                    map.put("medicineform_name", resultSet.getString("medicineform_name"));
                    pillboxList.add(map);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        dataMap.put("today", todayList);
        dataMap.put("pillbox", pillboxList);
        return gson.toJson(dataMap);
    }

    //Function to translate form inputs into an array of intake datetimes
    private List<String> getIntakeTimes(HttpServletRequest request){
        List<String> intakeDateTimes = new ArrayList<String>();


        Double dose = Double.valueOf(request.getParameter("addDrugDose"));
        Double totalQuantity = Double.valueOf(request.getParameter("addDrugQuantity"));

        //Dummy Variables for testing
        //Double totalQuantity= 11.0;
        //Double dose = 2.0;

        String interval = request.getParameter("addDrugInterval");

        Map<String, Boolean> dayOfWeekMap = new HashMap<String, Boolean>();
        if (interval.equalsIgnoreCase("2")){
            System.out.println("Inside");

            if(request.getParameterMap().containsKey("addDayOfWeek1")){ dayOfWeekMap.put("MONDAY", true); }
            else { dayOfWeekMap.put("MONDAY", false); }
            if(request.getParameterMap().containsKey("addDayOfWeek2")){ dayOfWeekMap.put("TUESDAY", true); }
            else { dayOfWeekMap.put("TUESDAY", false); }
            if(request.getParameterMap().containsKey("addDayOfWeek3")){ dayOfWeekMap.put("WEDNESDAY", true); }
            else { dayOfWeekMap.put("WEDNESDAY", false); }
            if(request.getParameterMap().containsKey("addDayOfWeek4")){ dayOfWeekMap.put("THURSDAY", true); }
            else { dayOfWeekMap.put("THURSDAY", false); }
            if(request.getParameterMap().containsKey("addDayOfWeek5")){ dayOfWeekMap.put("FRIDAY", true); }
            else { dayOfWeekMap.put("FRIDAY", false); }
            if(request.getParameterMap().containsKey("addDayOfWeek6")){ dayOfWeekMap.put("SATURDAY", true); }
            else { dayOfWeekMap.put("SATURDAY", false); }
            if(request.getParameterMap().containsKey("addDayOfWeek7")){ dayOfWeekMap.put("SUNDAY", true); }
            else { dayOfWeekMap.put("SUNDAY", false); }
        }
        else {
            dayOfWeekMap.put("MONDAY", true);
            dayOfWeekMap.put("TUESDAY", true);
            dayOfWeekMap.put("WEDNESDAY", true);
            dayOfWeekMap.put("THURSDAY", true);
            dayOfWeekMap.put("FRIDAY", true);
            dayOfWeekMap.put("SATURDAY", true);
            dayOfWeekMap.put("SUNDAY", true);
        }


        //Get the rounded up number of intakes
        int numIntakes = (int)Math.ceil(totalQuantity/dose);
        String rawStartDate = request.getParameter("addDrugStartDate");

        //Freq is twice a day at 1100 and 1800
        String[] consumptionTimes = request.getParameter("addDrugFrequency").split(",");

        LocalDate startDate = LocalDate.parse(rawStartDate);
        LocalDate currentDate = startDate;
        LocalDateTime currentDateTime;
        int daysSinceStart = 0;
        for (int i = 0; i<numIntakes; i++){
            int intakeGroup = i%consumptionTimes.length;
            int hour = Integer.valueOf(consumptionTimes[intakeGroup]);
            //Go to next day
            if(intakeGroup==0 && i>0){
                daysSinceStart++;
            }

            currentDateTime = startDate.plusDays(daysSinceStart).atTime(hour, 0);

            if(dayOfWeekMap.get(currentDateTime.getDayOfWeek().toString())){
                intakeDateTimes.add(currentDateTime.toString());
            }
            //if day is not included, prevent the counter from increasing and move to next day with same time
            else{
                i--;
            }
        }
        return intakeDateTimes;
    }

    private void addToPillbox(HttpServletRequest request){
        List<String> intakeDateTimes = getIntakeTimes(request);

        String[] consumptionTimes = request.getParameter("addDrugFrequency").split(",");

        long insertID = 0;


        /*
        System.out.println(request.getParameter("addDrugName"));
        System.out.println(request.getParameter("addDrugMeals"));
        System.out.println(request.getParameter("addDrugInterval"));
        System.out.println(request.getParameter("addDrugQuantity"));
        System.out.println(request.getParameter("addDrugStartDate"));
        System.out.println(request.getParameter("addDrugDose"));
        System.out.println(request.getParameter("addDrugInstructions"));//
        System.out.println(request.getParameter("addDrugFrequency"));
        System.out.println(request.getParameter("addDrugForm"));
        */
        //Insert into INVENTORY
        String sql = "INSERT INTO INVENTORY(drug_ID, drugintake_ID,drugphase_ID,inventory_balance,inventory_status,inventory_startdate,dose,instructions,frequency,strictness,medicineform_ID) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement ps = DBConn.getPreparedStatement(sql);
            ps.setInt(1, Integer.valueOf(request.getParameter("addDrugName")));
            ps.setInt(2, Integer.valueOf(request.getParameter("addDrugMeals")));
            ps.setInt(3, Integer.valueOf(request.getParameter("addDrugInterval")));
            ps.setInt(4, Integer.valueOf(request.getParameter("addDrugQuantity")));
            ps.setBoolean(5, true);
            ps.setString(6, request.getParameter("addDrugStartDate"));
            ps.setInt(7, Integer.valueOf(request.getParameter("addDrugDose")));
            ps.setString(8, request.getParameter("addDrugInstructions"));
            ps.setInt(9, consumptionTimes.length);

            if (request.getParameter("addDrugStrictness").equalsIgnoreCase("true")){
                ps.setBoolean(10, true);
            }
            else{
                ps.setBoolean(10, false);
            }
            ps.setInt(11, Integer.valueOf(request.getParameter("addDrugForm")));

            ps.executeUpdate();
            //Get primary key to reference in REMINDERS table
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                insertID = generatedKeys.getLong(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (insertID>0){

            for (String datetime: intakeDateTimes) {
                System.out.println(datetime);

                sql = "INSERT INTO REMINDERS(inventory_ID, reminder_time) " +
                        "VALUES(?,?);";
                try {
                    PreparedStatement ps = DBConn.getPreparedStatement(sql);
                    ps.setInt(1, (int)insertID);
                    ps.setString(2, datetime);
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }


    }

    private void editPillbox(HttpServletRequest request){


        System.out.println(request.getParameter("editDrugQuantity"));//
        System.out.println(request.getParameter("editDrugDose"));//
        System.out.println(request.getParameter("editDrugMeals"));
        System.out.println(request.getParameter("editDrugFrequency"));
        System.out.println(request.getParameter("editDrugInterval"));
        System.out.println(request.getParameter("editDrugName"));

        String sql = "UPDATE INVENTORY SET inventory_balance=?,dose=?,drugintake_ID=?,frequency=?,drugphase_ID=? "+
                "WHERE inventory_ID = ?;";
        //String sql = "UPDATE INVENTORY SET inventory_balance=?,dose=?,drugintake_ID=?,frequency=?,drugphase_ID=? "+
          //      "WHERE inventory_ID = ?;";


        try {
            PreparedStatement ps = DBConn.getPreparedStatement(sql);

            ps.setInt(1, Integer.valueOf(request.getParameter("editDrugQuantity")));
            ps.setInt(2, Integer.valueOf(request.getParameter("editDrugDose")));
            ps.setInt(3, Integer.valueOf(request.getParameter("editDrugMeals")));
            ps.setInt(4, Integer.valueOf(request.getParameter("editDrugFrequency")));
            ps.setInt(5, Integer.valueOf(request.getParameter("editDrugInterval")));
            ps.setInt(6, Integer.valueOf(request.getParameter("editDrugName")));
            System.out.println(ps.toString());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteFromPillbox(String id){
        String sql = "DELETE FROM REMINDERS WHERE inventory_ID=?";
        try {
            PreparedStatement ps = DBConn.getPreparedStatement(sql);
            ps.setInt(1, Integer.valueOf(id));
            ps.executeUpdate();
            sql = "DELETE FROM INVENTORY WHERE inventory_ID=?";
            ps = DBConn.getPreparedStatement(sql);
            ps.setInt(1, Integer.valueOf(id));

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }



    private void recordConsumption(String id, String remainder, String inventory_ID){
        System.out.println(id);
        System.out.println(remainder);
        System.out.println(inventory_ID);

        String sql = "UPDATE REMINDERS SET consumed = 1 WHERE reminder_ID = ?";

        try {
            PreparedStatement ps = DBConn.getPreparedStatement(sql);
            ps.setInt(1, Integer.valueOf(id));
            ps.executeUpdate();
            sql = "UPDATE INVENTORY SET inventory_balance = ? WHERE inventory_ID = ?";
            ps = DBConn.getPreparedStatement(sql);
            ps.setInt(1, Integer.valueOf(remainder));
            ps.setInt(2, Integer.valueOf(inventory_ID));
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}