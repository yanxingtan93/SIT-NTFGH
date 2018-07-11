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
        ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        return ps;
    }




}
