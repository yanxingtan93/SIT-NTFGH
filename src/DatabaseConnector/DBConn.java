package DatabaseConnector;

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
    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        PreparedStatement ps = null;
        String hostName = "itpgroup1.database.windows.net";
        String dbName = "SIT-NGTFH";
        String user = "dbadmin@itpgroup1";
        String password = "dbPassword!";
        String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
        Connection connection = DriverManager.getConnection(url);
        return connection;
    }


    public static PreparedStatement getPreparedStatement(String sql) throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        PreparedStatement ps = null;
        String hostName = "itpgroup1.database.windows.net";
        String dbName = "SIT-NGTFH";
        String user = "dbadmin@itpgroup1";
        String password = "dbPassword!";
        String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
        Connection connection = DriverManager.getConnection(url);
        ps = connection.prepareStatement(sql);
        return ps;
    }

    //check connections
    public static void main(String[]args) throws SQLException {
        String medName = "Paracetamol";
//        String sql = "SELECT drug_ID FROM DRUGS " +
//                "WHERE drug_name ='"+medName+"'";
        String NRIC = "S1234567A";
        String preorderID = "19";
//        String sql = "SELECT preorder_ID, preorder_mode, collection_date, status FROM PREORDER " +
//                "WHERE user_NRIC = '"+NRIC+"'" ;
//        String sql = "SELECT d.drug_name, pd.quantity FROM PREORDERDRUGS pd, DRUGS d" +
//                " WHERE pd.preorder_ID = '"+preorderID+"' and pd.drug_ID = d.drug_ID";

//        String sql = "SELECT p.preorder_ID, p.preorder_mode, p.collection_date, p.status FROM PREORDER p WHERE p.user_NRIC ='"+NRIC+"' " ;

        String sql = "SELECT d.drug_name, pd.quantity FROM PREORDERDRUGS pd, DRUGS d" +
                " WHERE pd.preorder_ID = '"+preorderID+"' and pd.drug_ID = d.drug_ID";
        PreparedStatement ps = getPreparedStatement(sql);
        ResultSet resultSet= ps.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getString(2));
//            System.out.println(resultSet.getString(3));
//            System.out.println(resultSet.getString(4));
//            System.out.println(resultSet.getString(5));
//            System.out.println(resultSet.getString(6));
            System.out.print("\n");
        }
    }




}
