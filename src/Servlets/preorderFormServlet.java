package Servlets;

import DatabaseConnector.DBConn;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "preorderFormServlet")
public class preorderFormServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("application/json");
//        try {
//            String term = request.getParameter("term");
//            DataDao dataDao = new DataDao();
//            ArrayList<String> list = dataDao.getFrameWork(term);
//
//            String searchList = new Gson().toJson(list);
//            response.getWriter().write(searchList);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");
        String NRIC = null;
        HttpSession sessions = request.getSession();

        switch (mode) {
            case "getUserSelect":
                NRIC = sessions.getAttribute("userID").toString();
                break;

            case "getPatientSelect":
                NRIC = sessions.getAttribute("patientID").toString();
                break;
        }


        Map<String, String> map = new LinkedHashMap<>();
        List<Map<String, String>> druglist = new ArrayList<>();

        String sql = "SELECT d.drug_id, d.drug_name FROM DRUGS d, INVENTORY i WHERE i.user_NRIC = '"+NRIC+"' and i.drug_id = d.drug_id";


        try {
            PreparedStatement ps = DBConn.getPreparedStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Map<String, String> map1 = new LinkedHashMap<>();
                map1.put("id", rs.getString(1));
                map1.put("drugname", rs.getString(2));
                //map.put(rs.getString(1), rs.getString(2));
                druglist.add(map1);

            }
        }catch(SQLException ex){
            ex.printStackTrace();

        }

        String json = new Gson().toJson(druglist);
        System.out.print("\nform:" + json);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }
}
