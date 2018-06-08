package Servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.pillboxDAO;


@WebServlet(name = "pillboxServlet")
public class pillboxServlet extends HttpServlet {

    private pillboxDAO DAO;

    public pillboxServlet() {
        super();
        DAO = new pillboxDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pillboxList", DAO.getList()); // Will be available as ${products} in JSP
        RequestDispatcher rd = request.getRequestDispatcher("patient/pillboxOverview.jsp");
        rd.forward(request, response);
    }
}

//    public patient getPatient(String id){
//
//        String sql = "SELECT user_NRIC, user_DOB, user_contact, user_name, user_email, user_address FROM USERS WHERE user_NRIC = ?";
//        String nric = "S1234567A";
//
//        patient pat = null;
//
//        try {
//            Connection conn= DBConn.getConnection();
//            PreparedStatement preparedStatement = conn.prepareStatement(sql);
//            preparedStatement.setString(1, nric);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()){
//                pat = new patient();
//                pat.setNric(resultSet.getString(1));
//                pat.setDob(resultSet.getString(2));
//                pat.setContact(resultSet.getString(3));
//                pat.setName(resultSet.getString(4));
//                pat.setEmail(resultSet.getString(5));
//                pat.setAddress(resultSet.getString(6));
//
////                System.out.print(resultSet.getString(1) + " " +resultSet.getString(2));
////                System.out.print(" ");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return pat;
//    }

