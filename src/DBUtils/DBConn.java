package DBUtils;

import java.sql.*;


public class DBConn {
    /*public Connection dbConnection() {
        String hostName = "itpgroup1.database.windows.net";
        String dbName = "SIT-NGTFH";
        String user = "dbadmin@itpgroup1";
        String password = "dbPassword!";
        String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }*/
    public static Connection getConnection() throws SQLException {
        PreparedStatement ps = null;
        String hostName = "itpgroup1.database.windows.net";
        String dbName = "SIT-NGTFH";
        String user = "dbadmin@itpgroup1";
        String password = "dbPassword!";
        String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
        Connection connection = DriverManager.getConnection(url);
        return connection;
    }

        public static void main(String[]args) throws SQLException {

            String sql = "SELECT user_NRIC, user_DOB, user_contact, user_name, user_email, user_address FROM USERS WHERE user_NRIC = ?";
            String nric = "S1234567A";

//            Connection conn= DBConn.getConnection();
//            PreparedStatement preparedStatement = conn.prepareStatement(sql);
//            preparedStatement.setString(1, nric);
//            ResultSet resultSet = preparedStatement.executeQuery();

            Connection conn = DBConn.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM INVENTORY WHERE drug_ID = 1");
            ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            System.out.print(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + " " + resultSet.getString(5) + " " + resultSet.getString(6));
            System.out.print("\n");
        }
    }



//    public static PreparedStatement getPreparedStatement(String sql) throws SQLException {
//        PreparedStatement ps = null;
//        String hostName = "itpgroup1.database.windows.net";
//        String dbName = "SIT-NGTFH";
//        String user = "dbadmin@itpgroup1";
//        String password = "dbPassword!";
//        String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
//        Connection connection = DriverManager.getConnection(url);
//        ps = connection.prepareStatement(sql);
//        return ps;
//    }



    //check connections.
//    public static void main(String[]args) throws SQLException {
//        ResultSet resultSet= getPreparedStatement("SELECT * FROM Drugs WHERE drug_brand = ?").executeQuery();
//        while (resultSet.next()){
//            System.out.print(resultSet.getString(1) + " " +resultSet.getString(2));
//            System.out.print(" ");
//        }
//    }



}
