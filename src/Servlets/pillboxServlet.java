package Servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DatabaseConnector.DBConn;
import model.pillbox;


@WebServlet(name = "pillboxServlet")
public class pillboxServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("getList", getList()); // Will be available as ${products} in JSP
        RequestDispatcher rd = request.getRequestDispatcher("patient/pillboxOverview.jsp");
        rd.forward(request, response);
    }

    public static List<pillbox> getList() {
        List<pillbox> list = new ArrayList<pillbox>();

        try {
            DBConn db = new DBConn();
            Connection conn = db.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM INVENTORY WHERE drug_ID = ?");
            String id = "1";
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                pillbox p = new pillbox();
                p.setInventory_id(rs.getString(1));
                p.setDrugintake_id(rs.getString(2));
                p.setDrugphase_id(rs.getString(3));
                p.setInventory_balance(rs.getString(4));
                p.setInventory_days(rs.getString(5));
                p.setInventory_startdate(rs.getString(6));
                p.setInventory_status(rs.getString(8));
                p.setDrug_ID(rs.getString(9));
                list.add(p);
                System.out.print(list);

                // rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(8), rs.getString(9));
                //resultSet.getString(1),resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8));
                //pillbox pillbox = new pillbox(resultSet.getString(1),resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

