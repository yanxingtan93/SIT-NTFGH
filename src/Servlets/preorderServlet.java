package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



import model.Preorder;

@WebServlet(name = "preorderServlet")
public class preorderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String NRIC = "S1234567A";
        int medID = Integer.valueOf(request.getParameter("medicationPreorder"));
        //String quantity = request.getParameter("quantity");
        int quantity = Integer.valueOf(request.getParameter("quantity"));
        String mode = request.getParameter("method");
        System.out.println(medID);
        System.out.println(mode);

        Preorder preorder = new Preorder(NRIC, mode, medID);
        preorder.addPreorder();
        int preorderid = preorder.getPreorderID();
        System.out.println(preorderid);
        preorder.addPreorderDrugs(preorderid);

//        String sql = "INSERT INTO PREORDER (user_NRIC,preorder_mode)" +
//               "VALUES ('"+NRIC+"','"+mode+"');";

        //String sql = "DECLARE 'id' int" + "INSERT INTO PREORDER (user_NRIC,preorder_mode)" + "VALUES ('"+NRIC+"','"+mode+"');" +
        //        "SET @id = SCOPE_IDENTITY()" + "INSERT INTO PREORDERDRUGS (preorder_ID, drug_ID)" + "VALUES ('id', '"+medID+");";

//
//        try {
//            Connection conn = DBConn.getConnection();
//            Statement stmt = conn.createStatement();
//            stmt.executeUpdate(sql);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
